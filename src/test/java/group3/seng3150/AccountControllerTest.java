package group3.seng3150;

import group3.seng3150.dao.IUserAccountDAO;
import group3.seng3150.entities.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class AccountControllerTest {

    @Autowired AccountController cont;
    @Autowired WebApplicationContext ctx;
    @Autowired IUserAccountDAO dao;
    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        BDDMockito.reset(dao);
    }

    @Test
    @WithMockUser(username = "max.wroe@outlook.com")
    //This test checks that get account details returns the correct details of a given user to the view, first test case
    public void testDisplayAccountDetails() throws Exception {

        UserAccount testAccount = new UserAccount("Max","Wroe","max.wroe@outlook.com",0,"Aus",0,"98 Everton St Hamilton","Dijana Jokovic","Dijana Jokovic",431176489);

        Mockito.when(dao.getAccountFromEmail("max.wroe@outlook.com")).thenReturn(testAccount);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/accountDetails")).andReturn();
        ModelAndView result = mvcResult.getModelAndView();
        Map<String, Object> model = result.getModel();

        assertEquals("The first name is not the expected first name",model.get("firstName"),testAccount.getFirstName());
        assertEquals(model.get("lastName"),testAccount.getLastName());
        assertEquals(model.get("email"),testAccount.getEmail());
    }

}