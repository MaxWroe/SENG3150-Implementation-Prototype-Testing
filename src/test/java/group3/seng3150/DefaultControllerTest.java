package group3.seng3150;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class DefaultControllerTest implements MethodSecurityTests{
    @Autowired DefaultController cont;
    @Autowired WebApplicationContext ctx;

    @Autowired EntityManager em;

    MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    @WithAnonymousUser
    public void testMappings() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /
        httpResult = mockMvc.perform(get("/"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /HOME
        httpResult = mockMvc.perform(get("/home"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /login
        httpResult = mockMvc.perform(get("/login"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /login
        httpResult = mockMvc.perform(post("/login").param("email", "bobrox@gmail.com"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /logout
        httpResult = mockMvc.perform(get("/logout"))
                .andReturn();
        assertEquals("Logout was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "Users/logout");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /faqs
        httpResult = mockMvc.perform(get("/faqs"))
                .andReturn();
        assertEquals("FAQ was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/faqs");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /accessDenied
        httpResult = mockMvc.perform(get("/accessDenied"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/accessDenied");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /travelRecommendations
        httpResult = mockMvc.perform(get("/travelRecommendations"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "travelRecommendations");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser("bobrox@gmail.com")
    public void testMappings_Customer() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /
        httpResult = mockMvc.perform(get("/"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /HOME
        httpResult = mockMvc.perform(get("/home"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /login
        httpResult = mockMvc.perform(get("/login"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /login
        httpResult = mockMvc.perform(post("/login").param("email", "bobrox@gmail.com"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /logout
        httpResult = mockMvc.perform(get("/logout"))
                .andReturn();
        assertEquals("Logout was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "Users/logout");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /faqs
        httpResult = mockMvc.perform(get("/faqs"))
                .andReturn();
        assertEquals("FAQ was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/faqs");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /accessDenied
        httpResult = mockMvc.perform(get("/accessDenied"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/accessDenied");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /travelRecommendations
        httpResult = mockMvc.perform(get("/travelRecommendations"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "travelRecommendations");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser("julieiscool@hotmail.com")
    public void testMappings_Agent() throws Exception{
        MvcResult httpResult;
        int status;

        // GET /
        httpResult = mockMvc.perform(get("/"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /HOME
        httpResult = mockMvc.perform(get("/home"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /login
        httpResult = mockMvc.perform(get("/login"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /login
        httpResult = mockMvc.perform(post("/login").param("email", "bobrox@gmail.com"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /logout
        httpResult = mockMvc.perform(get("/logout"))
                .andReturn();
        assertEquals("Logout was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "Users/logout");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /faqs
        httpResult = mockMvc.perform(get("/faqs"))
                .andReturn();
        assertEquals("FAQ was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/faqs");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /accessDenied
        httpResult = mockMvc.perform(get("/accessDenied"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/accessDenied");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /travelRecommendations
        httpResult = mockMvc.perform(get("/travelRecommendations"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "travelRecommendations");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser("Kaylasweet@gmail.com")
    public void testMappings_FlightPub() throws Exception{
        MvcResult httpResult;
        int status;

        // GET /
        httpResult = mockMvc.perform(get("/"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /HOME
        httpResult = mockMvc.perform(get("/home"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /login
        httpResult = mockMvc.perform(get("/login"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /login
        httpResult = mockMvc.perform(post("/login").param("email", "bobrox@gmail.com"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /logout
        httpResult = mockMvc.perform(get("/logout"))
                .andReturn();
        assertEquals("Logout was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "Users/logout");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /faqs
        httpResult = mockMvc.perform(get("/faqs"))
                .andReturn();
        assertEquals("FAQ was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/faqs");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /accessDenied
        httpResult = mockMvc.perform(get("/accessDenied"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/accessDenied");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /travelRecommendations
        httpResult = mockMvc.perform(get("/travelRecommendations"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "travelRecommendations");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser("jacthebat@gmail.com")
    public void testMappings_Admin() throws Exception{
        MvcResult httpResult;
        int status;

        // GET /
        httpResult = mockMvc.perform(get("/"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /HOME
        httpResult = mockMvc.perform(get("/home"))
                .andReturn();
        assertEquals("Home was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /login
        httpResult = mockMvc.perform(get("/login"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /login
        httpResult = mockMvc.perform(post("/login").param("email", "bobrox@gmail.com"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "home");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /logout
        httpResult = mockMvc.perform(get("/logout"))
                .andReturn();
        assertEquals("Logout was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "Users/logout");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /faqs
        httpResult = mockMvc.perform(get("/faqs"))
                .andReturn();
        assertEquals("FAQ was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/faqs");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /accessDenied
        httpResult = mockMvc.perform(get("/accessDenied"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "/accessDenied");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /travelRecommendations
        httpResult = mockMvc.perform(get("/travelRecommendations"))
                .andReturn();
        assertEquals("Access denied was unsuccessfully loaded " , Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "travelRecommendations");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

}