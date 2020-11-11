/**
 * This class manages the testing of the flight sort class in accordance with the Group 3 requirements document.
 *
 * Author : Max Wroe
 * Date : 10/11/2020
 */

package group3.seng3150.flightLogic;

import group3.seng3150.AccountController;
import group3.seng3150.FlightPlan;
import group3.seng3150.dao.IUserAccountDAO;
import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;
import group3.seng3150.entities.Price;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightsSortTest {

    @Autowired
    WebApplicationContext ctx;
    @Autowired
    MockMvc mockMvc;
    ArrayList<FlightPlan> inputFlights;



    @Before
    public void setup() {
        //Create the unsorted list of flightPlans that will be used as input
        inputFlights = new ArrayList();
        //Populate the list with dummy flightplans
        //FlightPlan1 - one leg
        FlightPlan thisFlightPlan = new FlightPlan();
        thisFlightPlan.addFlight(
                new Flight("ED123","ED", Timestamp.valueOf("2007-09-23 10:10:10.0"),"JFK",null,null,null,"LAX",Timestamp.valueOf("2007-09-24 10:10:10.0"),1440,null)
        );
        thisFlightPlan.addPrice(new Price("ED","ED123","ECO","A",Timestamp.valueOf("2001-09-23 10:10:10.0"),Timestamp.valueOf("2020-09-23 10:10:10.0"),1000.00,1000.00,null));
        thisFlightPlan.addAvailability(new Availability("ED123","ED",Timestamp.valueOf("2007-09-23 10:10:10.0"),"A","ECO",100,null));
        inputFlights.add(thisFlightPlan);

        //FlightPlan2 - one leg
        thisFlightPlan = new FlightPlan();
        thisFlightPlan.addFlight(
                new Flight("JK356","JK", Timestamp.valueOf("2007-09-23 10:10:10.0"),"JFK",null,null,null,"LAX",Timestamp.valueOf("2007-09-24 10:10:10.0"),1000,null)
        );
        thisFlightPlan.addPrice(new Price("JK","JK356","ECO","A",Timestamp.valueOf("2001-09-23 10:10:10.0"),Timestamp.valueOf("2020-09-23 10:10:10.0"),2000.00,2000.00,null));
        thisFlightPlan.addAvailability(new Availability("JK356","ED",Timestamp.valueOf("2007-09-23 10:10:10.0"),"A","ECO",232,null));
        inputFlights.add(thisFlightPlan);

        //FlightPlan3 - one leg
        thisFlightPlan = new FlightPlan();
        thisFlightPlan.addFlight(
                new Flight("MW222","MW", Timestamp.valueOf("2007-09-23 10:10:10.0"),"JFK",null,null,null,"LAX",Timestamp.valueOf("2007-09-24 10:10:10.0"),900,null)
        );
        thisFlightPlan.addPrice(new Price("MW","MW222","ECO","A",Timestamp.valueOf("2001-09-23 10:10:10.0"),Timestamp.valueOf("2020-09-23 10:10:10.0"),435.00,435.00,null));
        thisFlightPlan.addAvailability(new Availability("MW222","MW",Timestamp.valueOf("2007-09-23 10:10:10.0"),"A","ECO",232,null));
        inputFlights.add(thisFlightPlan);

        //FlightPlan4 - one 'flight' with stopover
        thisFlightPlan = new FlightPlan();
        thisFlightPlan.addFlight(
                new Flight("GW231","GW", Timestamp.valueOf("2007-09-23 10:10:10.0"),"JFK","BBW",Timestamp.valueOf("2007-09-23 11:10:10.0"),Timestamp.valueOf("2007-09-23 11:40:10.0"),"LAX",Timestamp.valueOf("2007-09-24 10:10:10.0"),900,null)
        );
        thisFlightPlan.addPrice(new Price("GW","GW231","ECO","A",Timestamp.valueOf("2001-09-23 10:10:10.0"),Timestamp.valueOf("2020-09-23 10:10:10.0"),1200.00,800.00,400.00));
        thisFlightPlan.addAvailability(new Availability("GW231","GW",Timestamp.valueOf("2007-09-23 10:10:10.0"),"A","ECO",232,111));
        inputFlights.add(thisFlightPlan);

        //FlightPlan5 two multilegs
        thisFlightPlan = new FlightPlan();
        thisFlightPlan.addFlight(
                new Flight("WA213","WA", Timestamp.valueOf("2007-09-23 10:10:10.0"),"JFK",null,null,null,"BBW",Timestamp.valueOf("2007-09-23 11:10:10.0"),120,null)
        );
        thisFlightPlan.addFlight(
                new Flight("UT232","UT", Timestamp.valueOf("2007-10-23 11:20:10.0"),"BBW",null,null,null,"LAX",Timestamp.valueOf("2007-09-24 10:10:10.0"),600,null)
        );
        thisFlightPlan.addPrice(new Price("WA","WA213","ECO","A",Timestamp.valueOf("2001-09-23 10:10:10.0"),Timestamp.valueOf("2020-09-23 10:10:10.0"),200.00,200.00,null));
        thisFlightPlan.addPrice(new Price("UT","UT232","ECO","A",Timestamp.valueOf("2001-09-23 10:10:10.0"),Timestamp.valueOf("2020-09-23 10:10:10.0"),1232.00,1232.00,null));
        thisFlightPlan.addAvailability(new Availability("WA213","WA",Timestamp.valueOf("2007-09-23 10:10:10.0"),"A","ECO",232,null));
        thisFlightPlan.addAvailability(new Availability("UT232","UT",Timestamp.valueOf("2007-10-23 11:20:10.0"),"A","ECO",121,null));
        inputFlights.add(thisFlightPlan);
    }

    //@Before create the list of test flights, copuld use the same list to test searches, (mock dbo to return them)

    @Test
    public void testSortByPriceAscending()
    {
        FlightsSort testSorter = new FlightsSort();
        List<FlightPlan> sortedResult = testSorter.sortFlightPlan(inputFlights,"priceascending");


        //Check that the sorted result is in the correct order
        assertEquals(inputFlights.get(2),sortedResult.get(0)); //Flight 3 should have come last since it has the lowest price
        assertEquals(inputFlights.get(0),sortedResult.get(1)); //Flight 1 should come second last....
        assertEquals(inputFlights.get(3),sortedResult.get(2)); //Flight 3 should come third last....
        assertEquals(inputFlights.get(4),sortedResult.get(3)); //Flight 4 should come fourth last....
        assertEquals(inputFlights.get(1),sortedResult.get(4)); //Flight 2 should come first....
    }

    @Test
    public void testSortByPriceDescending()
    {
        FlightsSort testSorter = new FlightsSort();
        List<FlightPlan> sortedResult = testSorter.sortFlightPlan(inputFlights,"pricedescending");


        //Check that the sorted result is in the correct order
        assertEquals(inputFlights.get(2),sortedResult.get(4));
        assertEquals(inputFlights.get(0),sortedResult.get(3));
        assertEquals(inputFlights.get(3),sortedResult.get(2));
        assertEquals(inputFlights.get(4),sortedResult.get(1));
        assertEquals(inputFlights.get(1),sortedResult.get(0));
    }

    @Test
    public void testSortByTimeAscending()
    {
        FlightsSort testSorter = new FlightsSort();
        List<FlightPlan> sortedResult = testSorter.sortFlightPlan(inputFlights,"timeascending");


        //Check that the sorted result is in the correct order
        assertEquals(inputFlights.get(4),sortedResult.get(0));
        assertEquals(inputFlights.get(3),sortedResult.get(1));
        assertEquals(inputFlights.get(2),sortedResult.get(2));
        assertEquals(inputFlights.get(1),sortedResult.get(3));
        assertEquals(inputFlights.get(0),sortedResult.get(4));
    }

    @Test
    public void testSortByTimeDescending()
    {
        FlightsSort testSorter = new FlightsSort();
        List<FlightPlan> sortedResult = testSorter.sortFlightPlan(inputFlights,"timedescending");


        //Check that the sorted result is in the correct order
        assertEquals(inputFlights.get(4),sortedResult.get(0));
        assertEquals(inputFlights.get(3),sortedResult.get(1));
        assertEquals(inputFlights.get(2),sortedResult.get(2));
        assertEquals(inputFlights.get(1),sortedResult.get(3));
        assertEquals(inputFlights.get(0),sortedResult.get(4));
    }

}