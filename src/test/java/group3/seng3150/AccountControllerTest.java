package group3.seng3150;

import group3.seng3150.dao.IUserAccountDAO;
import group3.seng3150.entities.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class AccountControllerTest {

    @Autowired AccountController cont;
    @Autowired WebApplicationContext ctx;
    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    //This test checks that get account details returns the correct details of a given user to the view, first test case
    public void testDisplayAccountDetails() throws Exception {
        MvcResult httpResult = mockMvc.perform(get("/accountDetails")
                .with(user("bobrox@gmail.com").roles("CUSTOMER")))
                .andReturn();

        ModelAndView result = httpResult.getModelAndView();

        //Test view name
        String viewName = result.getViewName();
        assertEquals("View name set correctly", viewName, "Users/accountDetails");

        Map<String, Object> model = result.getModel();

        assertNotNull("First name is null", model.get("firstName"));
        assertNotNull("lastName", model.get("lastName"));
        assertNotNull("email", model.get("email"));
        assertNotNull("userType", model.get("userType"));
        assertNotNull("dateOfBirth", model.get("dateOfBirth"));
        assertNotNull("citizenship", model.get("citizenship"));
        assertNotNull("gender", model.get("gender"));
        assertNotNull("phone", model.get("phone"));
    }


}