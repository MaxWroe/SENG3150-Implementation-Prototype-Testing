/**
 * This class deals with testing the search functionality of the Group 3 Flightpub implementation
 */
package group3.seng3150;

import group3.seng3150.dao.IUserAccountDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class FlightControllerTest {

    @Autowired
    AccountController cont;
    @Autowired
    WebApplicationContext ctx;
    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }


    @Test
    /**
     * This test conducts a database search that is known to return no results, check that no crash occurs and that
     * the returned object is empty - UPDATE TO WORK WITH IN MEMORY DB
     */
    public void searchNoResults() throws Exception {

        //Get the search result from the database
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/search")
                .param("departureLocation","LAX")
                .param("arrivalLocation","JFK")
                .param("departureDate","2020-11-06")
                .param("returnDate","2020-11-06")
                .param("classCode","ECO")
                .param("departureDateRange","0")
                .param("returnDateRange","0")
                .param("adults","1")
                .param("children","0"))
                .andReturn();


        ModelAndView result = mvcResult.getModelAndView();
        Map<String, Object> model = result.getModel();



        //Now check the number of flights from the model, it should be empty
        FlightHolder resultFlights = (FlightHolder) model.get("flights");
        assertEquals("Flights were returned by the method",0, resultFlights.getFlightPlansDepartingSize());
    }

    //Test that
    @Test
    /**
     * This test conducts a database search for a flight involving one stop over, Check to ensure that the correct
     * flight is returned by the function and that the system does not crash
     */
    public void searchOneStopOverResults() throws Exception {

        //Get the search result from the database
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/search")
                .param("departureLocation","LAX")
                .param("arrivalLocation","JFK")
                .param("departureDate","2020-11-06")
                .param("returnDate","2020-11-06")
                .param("classCode","ECO")
                .param("departureDateRange","0")
                .param("returnDateRange","0")
                .param("adults","1")
                .param("children","0"))
                .andReturn();


        ModelAndView result = mvcResult.getModelAndView();
        Map<String, Object> model = result.getModel();


        //Have a fun time constructing the correct FlightPlan object that should be returned if the function was
        //correct .........
        FlightHolder expectedResult = new FlightHolder();

        //Now check the number of flights from the model, it should be empty
        FlightHolder resultFlights = (FlightHolder) model.get("flights");
        assertEquals("Flights were returned by the method",expectedResult, resultFlights);
    }

    /**Check that for a provided search, there are no flights returned by the search function **/

    /**Check that for a provided search, there are no flights returned that have insufficient availabilities **/


}