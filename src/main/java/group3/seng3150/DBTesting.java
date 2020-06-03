package group3.seng3150;

import group3.seng3150.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.List;
import java.sql.Date;

//This class is purely for testing db stuff

@Controller
@RequestMapping("DBTesting")
public class DBTesting {

    private EntityManager em;

    public DBTesting(EntityManager em){

        this.em = em;
    }

    @GetMapping
    public ModelAndView index(){
        List<Country> countries = em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
        List<Airport> airports = em.createQuery("SELECT d FROM Airport d", Airport.class).getResultList();
        List<Plane> planes = em.createQuery("SELECT p FROM Plane p", Plane.class).getResultList();
        List<UserAccount> users = em.createQuery("SELECT u FROM UserAccount u", UserAccount.class).getResultList();
        List<Availability> Availability =  em.createQuery("SELECT f FROM Availability f WHERE f.departureDate > '2010-11-16 21:00:00'", Availability.class).getResultList();
        List<Flight> flights = em.createQuery("SELECT f FROM Flight f WHERE f.departureDate > '2010-11-16 21:00:00'", Flight.class).getResultList();
        List<Booking> bookings = em.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();

        //this is an example of how to fill the database
        //start a transaction
        em.getTransaction().begin();

        //create an entity and fill the data
        UserAccount test = new UserAccount();
        test.setFirstName("testinsert");
        test.setLastName("hopethisworks");
        test.setEmail("testing@test.test");
        test.setPassword("thisisatest");
        Date d = Date.valueOf("2015-09-24");
        test.setDateOfBirth(d);
        test.setCitizenship("Australian");
        test.setGender(0);
        test.setUserType(2);

        //merge the entity and commit the transaction
        em.persist(test);
        em.getTransaction().commit();


        List<UserAccount> newUsers = em.createQuery("SELECT u FROM UserAccount u", UserAccount.class).getResultList();

        //used as a stop point in debugger
        System.out.print("stop");
        return new ModelAndView("index");


    }

}
