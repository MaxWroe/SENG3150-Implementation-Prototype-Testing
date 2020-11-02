package group3.seng3150;


import group3.seng3150.entities.EntityManagerCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class SpringFrameworkTest {

    //Contexts
    @Autowired WebApplicationContext webApplicationContext;

    //Entity Managers
    @Autowired EntityManagerCreator entityManagerCreator;
    @Autowired EntityManager entityManager;

    //Controllers
    @Autowired AccountController accountController;
    @Autowired AuthenticationController authenticationController;
    @Autowired BookingsController bookingsController;
    @Autowired DefaultController defaultController;
    @Autowired FlightController flightController;
    @Autowired FlightPubStaffController flightPubStaffController;
    @Autowired travelAgentController travelAgentController;

    //Misc Beans
    @Autowired FlightHolder flightHolder;
    @Autowired createBooking createBooking;

    @Before
    public void setup(){
    }

    @Test
    public void testAutowiring(){
        //Context tests
        assertNotNull("WebApplicationContext was not autowired correctly", webApplicationContext);
        assertNotNull("Servlet context was not properly configured", webApplicationContext.getServletContext());

        //Entity Manager
        assertNotNull("Entity Manager Creator was not autowired correctly", entityManagerCreator);
        assertNotNull("Entity Manager  was not autowired correctly", entityManager);

        //Controllers
        assertNotNull("Account Controller was not autowired correctly", accountController);
        assertNotNull("Authentication Controller was not autowired correctly", authenticationController);
        assertNotNull("Bookings Controller was not autowired correctly", bookingsController);
        assertNotNull("Default Controller was not autowired correctly", defaultController);
        assertNotNull("Flight Controller was not autowired correctly", flightController);
        assertNotNull("FlightPub Staff Controller was not autowired correctly", flightPubStaffController);
        assertNotNull("Travel Agent Controller was not autowired correctly", travelAgentController);

        //Misc Beans
        assertNotNull("Flight Holder was not autowired correctly", flightHolder);
        assertNotNull("Create Booking was not autowired correctly", createBooking);
    }

    @Test
    public void testEntities(){
    }
}
