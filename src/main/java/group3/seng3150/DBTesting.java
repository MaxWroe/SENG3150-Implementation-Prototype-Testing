package group3.seng3150;


import group3.seng3150.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.List;

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
        List<Flight> flights = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber='AF5028' AND f.ticketCode='B' AND " +
                "f.classCode='BUS' AND f.departureDate<'2015-09-24 09:50:00'", Flight.class).getResultList();

        //used as a stop point in debugger
        System.out.print("stop");
        return new ModelAndView("index");
    }

}
