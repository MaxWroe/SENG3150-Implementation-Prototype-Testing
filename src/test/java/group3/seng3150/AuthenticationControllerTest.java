package group3.seng3150;

import group3.seng3150.entities.UserAccount;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.Filter;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class AuthenticationControllerTest implements MethodSecurityTests {
    @Rule
    public ErrorCollector collector = new ErrorCollector();
    @Autowired
    Filter springSecurityFilterChain;
    @Autowired
    WebApplicationContext ctx;
    @Autowired
    EntityManager em;
    @Autowired
    AuthenticationController authenticationController;
    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(springSecurityFilterChain).build();
    }

    @Test()
    public void testPasswordValidation() throws Exception {
        String[] passwords = {"", "a", "ab", "abc", "abcd", "abcde", "abcdef", "abcdefg", "abcdefgh", "abcdefgh1", "1A" + String.valueOf((char) 2).repeat(8),
                "1Abcdefgh"};

        for (String p : passwords) {
            try {
                MvcResult httpResult = mockMvc.perform(post("/register")
                        .param("email", "u" + p)
                        .param("password", p)
                        .param("dateOfBirth", "1970-01-01"))
                        .andReturn();
            } catch (Exception e) {
                collector.addError(e);
            }

            UserAccount userAcc;

            String email = "'" + "u" + p + "'";

            try {
                userAcc = em.createQuery("SELECT u FROM UserAccount u WHERE u.email=" + email, UserAccount.class).getSingleResult();
                collector.checkThat("User was registered with improperly validated password '" + p + "'", userAcc, equalTo(null));
            } catch (NoResultException ignored) {

            }
        }
    }

    @Test
    public void testUsersWithSameEmail() throws Exception {
        String email = "jacthebat@gmail.com";
        String firstName = "'jacthebat'";

        mockMvc.perform(post("/register")
                .param("email", email)
                .param("firstName", firstName)
                .param("dateOfBirth", "1970-01-01"))
                .andReturn();

        UserAccount userAcc;

        try {
            userAcc = em.createQuery("SELECT u FROM UserAccount u WHERE u.firstName=" + firstName, UserAccount.class).getSingleResult();
            collector.checkThat("User was not registered ", userAcc, notNullValue());
        } catch (NoResultException ignored) {

        }

        MvcResult httpResult = mockMvc.perform(post("/register")
                .param("email", email)
                .param("dateOfBirth", "1970-01-01"))
                .andReturn();

        collector.checkThat("User was registered again", Objects.requireNonNull(httpResult.getModelAndView()).getModel().get("message"), equalTo("Registration unsuccessful. An Account using " + email + " already exists, please use a unique email address or login to the existing account."));

    }

    @Test
    public void testMappings() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /login
        httpResult = mockMvc.perform(get("/login"))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /register
        httpResult = mockMvc.perform(get("/register"))
                .andReturn();
        assertEquals("Register was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /register
        httpResult = mockMvc.perform(post("/register").param("dateOfBirth", "2000-09-09"))
                .andReturn();
        assertTrue("Register was unsuccessfully loaded ",
                Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login")
                        || Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register"));
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser()
    public void testMappings_Customer() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /login
        httpResult = mockMvc.perform(get("/login")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /register
        httpResult = mockMvc.perform(get("/register")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /register
        httpResult = mockMvc.perform(post("/register").param("dateOfBirth", "2000-09-09")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        assertTrue("Register was unsuccessfully loaded ",
                Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login")
                        || Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register"));
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser()
    public void testMappings_Agent() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /login
        httpResult = mockMvc.perform(get("/login")
                .with(user("user").roles("AGENT")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /register
        httpResult = mockMvc.perform(get("/register")
                .with(user("user").roles("AGENT")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /register
        httpResult = mockMvc.perform(post("/register").param("dateOfBirth", "2000-09-09")
                .with(user("user").roles("AGENT")))
                .andReturn();
        assertTrue("Register was unsuccessfully loaded ",
                Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login")
                        || Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register"));
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser()
    public void testMappings_FlightPub() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /login
        httpResult = mockMvc.perform(get("/login")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /register
        httpResult = mockMvc.perform(get("/register")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /register
        httpResult = mockMvc.perform(post("/register").param("dateOfBirth", "2000-09-09")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        assertTrue("Register was unsuccessfully loaded ",
                Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login")
                        || Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register"));
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }

    @Test
    @WithMockUser()
    public void testMappings_Admin() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /login
        httpResult = mockMvc.perform(get("/login")
                .with(user("user").roles("ADMIN")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // GET /register
        httpResult = mockMvc.perform(get("/register")
                .with(user("user").roles("ADMIN")))
                .andReturn();
        assertEquals("Login was unsuccessfully loaded ", Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register");
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);

        // POST /register
        httpResult = mockMvc.perform(post("/register").param("dateOfBirth", "2000-09-09")
                .with(user("user").roles("ADMIN")))
                .andReturn();
        assertTrue("Register was unsuccessfully loaded ",
                Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/login")
                        || Objects.equals(Objects.requireNonNull(httpResult.getModelAndView()).getViewName(), "General/register"));
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status < 300);
    }
}