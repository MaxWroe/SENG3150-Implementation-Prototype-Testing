package group3.seng3150;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class FlightPubStaffControllerTest implements MethodSecurityTests{
    @Autowired DefaultController cont;
    @Autowired WebApplicationContext ctx;

    @Autowired Filter springSecurityFilterChain;

    @Rule
    public ErrorCollector collector = new ErrorCollector();


    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(springSecurityFilterChain).build();
    }

    @Test
    public void testMappings() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /manageAirline
        httpResult = mockMvc.perform(get("/manageAirline"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /manageAirline", status, equalTo(403));

        // POST /manageAirline/unsponsor
        httpResult = mockMvc.perform(post("/manageAirline/unsponsor")
                .param("airlineName1", "blah")
                .param("airlineSponsored1", "0"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /manageAirline/unsponsor", status, equalTo(403));

        // POST /manageAirline/sponsor
        httpResult = mockMvc.perform(post("/manageAirline/sponsor")
                .param("airlineName", "blah")
                .param("airlineSponsored", "0"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /manageAirline/sponsor", status, equalTo(403));

        // GET /manageAirport
        httpResult = mockMvc.perform(get("/manageAirport"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /manageAirport", status, equalTo(403));

        // POST /manageAirport/restrict
        httpResult = mockMvc.perform(post("/manageAirport/restrict")
                .param("airlineName2", "blah")
                .param("shutdownStartDate", "2020-12-12")
                .param("shutdownEndDate", "2020-12-12"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /manageAirline/restrict", status, equalTo(403));

        // GET /manageUsers
        httpResult = mockMvc.perform(get("/manageUsers"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /manageUsers", status, equalTo(403));

        // POST /manageUsers/remove
        httpResult = mockMvc.perform(post("/manageUsers/remove"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /manageUsers/remove", status, equalTo(403));

        // GET /addUsers
        httpResult = mockMvc.perform(get("/addUsers"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /addUsers", status, equalTo(403));

        // POST /addUsers/add
        httpResult = mockMvc.perform(post("/addUsers/add")
                .param("dateOfBirth", "1970-01-01")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("gender", "male")
                .param("email", "email@email.com")
                .param("phone",  "0123456789")
                .param("userType", "Personal")
                .param("userRole", "ADMIN"))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403 for unregistered access of /addUsers/add", status, equalTo(403));
    }

    @Test
    @WithMockUser()
    public void testMappings_Customer() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /manageAirline
        httpResult = mockMvc.perform(get("/manageAirline")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirline/unsponsor
        httpResult = mockMvc.perform(post("/manageAirline/unsponsor")
                .param("airlineName1", "blah")
                .param("airlineSponsored1", "0")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirline/sponsor
        httpResult = mockMvc.perform(post("/manageAirline/sponsor")
                .param("airlineName", "blah")
                .param("airlineSponsored", "0")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /manageAirport
        httpResult = mockMvc.perform(get("/manageAirport")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirport/restrict
        httpResult = mockMvc.perform(post("/manageAirport/restrict")
                .param("airlineName2", "blah")
                .param("shutdownStartDate", "2020-12-12")
                .param("shutdownEndDate", "2020-12-12")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /manageUsers
        httpResult = mockMvc.perform(get("/manageUsers")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageUsers/remove
        httpResult = mockMvc.perform(post("/manageUsers/remove")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /addUsers
        httpResult = mockMvc.perform(get("/addUsers")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /addUsers/add
        httpResult = mockMvc.perform(post("/addUsers/add")
                .param("dateOfBirth", "1970-01-01")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("gender", "male")
                .param("email", "email@email.com")
                .param("phone",  "0123456789")
                .param("userType", "Personal")
                .param("userRole", "ADMIN")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));
    }

    @Test
    @WithMockUser()
    public void testMappings_Agent() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /manageAirline
        httpResult = mockMvc.perform(get("/manageAirline")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirline/unsponsor
        httpResult = mockMvc.perform(post("/manageAirline/unsponsor")
                .param("airlineName1", "blah")
                .param("airlineSponsored1", "0")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirline/sponsor
        httpResult = mockMvc.perform(post("/manageAirline/sponsor")
                .param("airlineName", "blah")
                .param("airlineSponsored", "0")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /manageAirport
        httpResult = mockMvc.perform(get("/manageAirport")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirport/restrict
        httpResult = mockMvc.perform(post("/manageAirport/restrict")
                .param("airlineName2", "blah")
                .param("shutdownStartDate", "2020-12-12")
                .param("shutdownEndDate", "2020-12-12")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /manageUsers
        httpResult = mockMvc.perform(get("/manageUsers")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageUsers/remove
        httpResult = mockMvc.perform(post("/manageUsers/remove")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /addUsers
        httpResult = mockMvc.perform(get("/addUsers")
                .with(user("user").roles("AGENT")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /addUsers/add
        httpResult = mockMvc.perform(post("/addUsers/add")
                .param("dateOfBirth", "1970-01-01")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("gender", "male")
                .param("email", "email@email.com")
                .param("phone",  "0123456789")
                .param("userType", "Personal")
                .param("userRole", "ADMIN")
                .with(user("user").roles("CUSTOMER")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));
    }

    @Test
    @WithMockUser()
    public void testMappings_FlightPub() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /manageAirline
        httpResult = mockMvc.perform(get("/manageAirline")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirline/unsponsor
        httpResult = mockMvc.perform(post("/manageAirline/unsponsor")
                .param("airlineName1", "blah")
                .param("airlineSponsored1", "0")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirline/sponsor
        httpResult = mockMvc.perform(post("/manageAirline/sponsor")
                .param("airlineName", "blah")
                .param("airlineSponsored", "0")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /manageAirport
        httpResult = mockMvc.perform(get("/manageAirport")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageAirport/restrict
        httpResult = mockMvc.perform(post("/manageAirport/restrict")
                .param("airlineName2", "blah")
                .param("shutdownStartDate", "2020-12-12")
                .param("shutdownEndDate", "2020-12-12")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /manageUsers
        httpResult = mockMvc.perform(get("/manageUsers")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /manageUsers/remove
        httpResult = mockMvc.perform(post("/manageUsers/remove")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // GET /addUsers
        httpResult = mockMvc.perform(get("/addUsers")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));

        // POST /addUsers/add
        httpResult = mockMvc.perform(post("/addUsers/add")
                .param("dateOfBirth", "1970-01-01")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("gender", "male")
                .param("email", "email@email.com")
                .param("phone",  "0123456789")
                .param("userType", "Personal")
                .param("userRole", "ADMIN")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        collector.checkThat("Status code is not 403", status, equalTo(403));
    }

    @Test
    @WithMockUser()
    public void testMappings_Admin() throws Exception {
        MvcResult httpResult;
        int status;

        // GET /manageAirline
        httpResult = mockMvc.perform(get("/manageAirline")
                .with(user("user").roles("ADMIN")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // POST /manageAirline/unsponsor
        httpResult = mockMvc.perform(post("/manageAirline/unsponsor")
                .param("airlineName1", "blah")
                .param("airlineSponsored1", "0")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // POST /manageAirline/sponsor
        httpResult = mockMvc.perform(post("/manageAirline/sponsor")
                .param("airlineName", "blah")
                .param("airlineSponsored", "0")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // GET /manageAirport
        httpResult = mockMvc.perform(get("/manageAirport")
                .with(user("user").roles("ADMIN")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // POST /manageAirport/restrict
        httpResult = mockMvc.perform(post("/manageAirport/restrict")
                .param("airlineName2", "blah")
                .param("shutdownStartDate", "2020-12-12")
                .param("shutdownEndDate", "2020-12-12")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // GET /manageUsers
        httpResult = mockMvc.perform(get("/manageUsers")
                .with(user("user").roles("ADMIN")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // POST /manageUsers/remove
        httpResult = mockMvc.perform(post("/manageUsers/remove")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // GET /addUsers
        httpResult = mockMvc.perform(get("/addUsers").with(user("user").roles("ADMIN")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);

        // POST /addUsers/add
        httpResult = mockMvc.perform(post("/addUsers/add")
                .param("dateOfBirth", "1970-01-01")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("gender", "male")
                .param("email", "email@email.com")
                .param("phone",  "0123456789")
                .param("userType", "Personal")
                .param("userRole", "ADMIN")
                .with(user("user").roles("FLIGHTPUB")))
                .andReturn();
        status = httpResult.getResponse().getStatus();
        assertTrue("Status code is not 2**", status >= 200 && status <= 300);
    }
}