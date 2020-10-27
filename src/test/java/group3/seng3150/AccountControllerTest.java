package group3.seng3150;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class AccountControllerTest {

    @Autowired AccountController cont;
    @Autowired WebApplicationContext ctx;
    @Autowired EntityManager em;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

//    @Test
//    public void testGetAccountDetails() {
//        Random rand = new Random();
//        assertTrue(rand.nextBoolean());
//    }

    @Test
    public void testWAC(){
        assertNotNull(ctx);
        System.err.println(ctx.toString());
    }

    @Test
    public void testCont(){
        assertNotNull(cont);
        System.err.println(cont);
    }

    @Test
    public void testEM(){
        assertEquals(em, cont.getEm());
    }

    @Test
    @WithMockUser(username = "bobrox@gmail.com")
    public void testGetMappings() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        MvcResult httpResult;
        ModelAndView result;

        mockMvc.perform(post("/appLogin").param("email", "bobrox@gmail.com").param("password", "bobrox"))
                .andReturn();

        //display customer support
        httpResult = mockMvc.perform(get("/customerSupport"))
                .andReturn();
        result = httpResult.getModelAndView();

        assertNotNull(result.getView());
        assertEquals(result.getView(), new ModelAndView("Users/customerSupport").getView());
    }
}