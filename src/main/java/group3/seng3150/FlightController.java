package group3.seng3150;

import group3.seng3150.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.LinkedList;

@Controller
@RequestMapping("search")
public class FlightController{
    private FlightHolder flights;
    private EntityManager em;

    @Autowired
    public FlightController(EntityManager em){this.em =em;}

    @GetMapping
    @RequestMapping("search")
    public ModelAndView search(
            @RequestParam(name="departureLocation", defaultValue="") String departureLocation,
            @RequestParam(name="arrivalLocation", defaultValue="") String arrivalLocation,
            @RequestParam(name="departureDate", defaultValue="") String departureDate,
            @RequestParam(name="returnDate", defaultValue="") String returnDate,
            @RequestParam(name="classType", defaultValue="") String classType) {

        //dates need to be strictly of '2015-09-24 09:50:00' format
        ModelAndView view = new ModelAndView("search");

            String departureTimeStart = departureDate += " 00:00:01";
            String departureTimeEnd = departureDate += " 23:59:59";
            List<Flight> retrievedDepartureFlights = em.createQuery("SELECT f FROM Flight f WHERE f.departureLocation=" + departureLocation +
                    " AND SELECT f FROM Flight f WHERE f.arrivalLocation=" + arrivalLocation +
                    " AND SELECT f FROM Flight f WHERE f.departureTime>=departureTimeStart" +
                    " AND SELECT f FROM Flight f WHERE f.departureTime<=departureTimeEnd" +
                    " AND SELECT f FROM Flight f WHERE f.classCode=" + classType, Flight.class).getResultList();
            flights.setFlights(retrievedDepartureFlights);
            flights.sortFlights("departureTimeAscending");


        //   if (returnDate!=null) {
//            String returnTimeStart = returnDate += " 00:00:01";
//            String returnTimeEnd = returnDate += "23:59:59";
//            List<Flight> retrievedReturnFlights = em.createQuery("SELECT f FROM Flight f WHERE f.departureLocation=" + departureLocation +
//                    " AND SELECT f FROM Flight f WHERE f.arrivalLocation=" + arrivalLocation +
//                    " AND SELECT f FROM Flight f WHERE f.departureTime>=returnTimeStart" +
//                    " AND SELECT f FROM Flight f WHERE f.departureTime<=returnTimeEnd" +
//                    " AND SELECT f FROM Flight f WHERE f.classCode=" + classType, Flight.class).getResultList();
//            flights.setFlights(retrievedReturnFlights);
        //    }


        view.addObject("flights", flights);
        return view;
    }
}
