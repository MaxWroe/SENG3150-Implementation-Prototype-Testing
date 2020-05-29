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
@RequestMapping("/search")
public class FlightController{
    private FlightHolder departureFlights;
    private FlightHolder returnFlights;
    private EntityManager em;

    @Autowired
    public FlightController(FlightHolder departureFlights, FlightHolder returnFlights, EntityManager em) {
        this.departureFlights = departureFlights;
        this.returnFlights = returnFlights;
        this.em =em;
    }

    @GetMapping
    @RequestMapping("")
    public ModelAndView search(
            @RequestParam(name="departureLocation", defaultValue="") String departureLocation,
            @RequestParam(name="arrivalLocation", defaultValue="") String arrivalLocation,
            @RequestParam(name="departureDate", defaultValue="") String departureDate,
            @RequestParam(name="returnDate", defaultValue="") String returnDate,
            @RequestParam(name="classCode", defaultValue="") String classCode,
            @RequestParam(name="type", defaultValue = "oneway") String type,
            @RequestParam(name="adults", defaultValue = "") int adults,
            @RequestParam(name="children", defaultValue = "") int children)
    {

        //dates need to be strictly of '2015-09-24 09:50:00' format
        ModelAndView view = new ModelAndView("search");

            int numberPeople = adults + children;
            String departureTimeStart = departureDate += " 00:00:01";
            String departureTimeEnd = departureDate += " 23:59:59";
            List<Flight> retrievedFlights = em.createQuery("SELECT f FROM Flight f WHERE f.departureLocation=" + departureLocation +
                    " AND SELECT f FROM Flight f WHERE f.arrivalLocation=" + arrivalLocation +
                    " AND SELECT f FROM Flight f WHERE f.departureTime>=departureTimeStart" +
                    " AND SELECT f FROM Flight f WHERE f.departureTime<=departureTimeEnd" +
                    " AND SELECT f FROM Flight f WHERE f.numberAvailableSeatsLeg1>=" + numberPeople +
                    " AND SELECT f FROM Flight f WHERE f.numberAvailableSeatsLeg2>=" + numberPeople +
                    " OR SELECT f FROM Flight f WHERE f.numberAvailableSeatsLeg2==null" +
                    " AND SELECT f FROM Flight f WHERE f.classCode=" + classCode, Flight.class).getResultList();
            departureFlights.setFlights(retrievedFlights);
            departureFlights.sortFlights("departureTimeAscending");

           if (type.equals("return")) {
            String returnTimeStart = returnDate += " 00:00:01";
            String returnTimeEnd = returnDate += "23:59:59";
            retrievedFlights = em.createQuery("SELECT f FROM Flight f WHERE f.departureLocation=" + departureLocation +
                    " AND SELECT f FROM Flight f WHERE f.arrivalLocation=" + arrivalLocation +
                    " AND SELECT f FROM Flight f WHERE f.departureTime>=returnTimeStart" +
                    " AND SELECT f FROM Flight f WHERE f.departureTime<=returnTimeEnd" +
                    " AND SELECT f FROM Flight f WHERE f.numberAvailableSeatsLeg1>=numberPeople" +
                    " AND SELECT f FROM Flight f WHERE f.numberAvailableSeatsLeg2>=" + numberPeople+ " OR f.numberAvailableSeatsLeg2==null" +
                    " AND SELECT f FROM Flight f WHERE f.classCode=" + classCode, Flight.class).getResultList();
               returnFlights.setFlights(retrievedFlights);
               returnFlights.sortFlights("departureTimeAscending");
            }

        view.addObject("departureFlights", departureFlights);
        view.addObject("returnFlights", returnFlights);
        return view;
    }

    @GetMapping
    @RequestMapping("/sort")
    public ModelAndView search(
            @RequestParam(name="sortby", defaultValue="") String sortby,
            @RequestParam(name="sortMethod", defaultValue="") String sortMethod
    ){
        ModelAndView view = new ModelAndView("sort");
        departureFlights.sortFlights(sortby+sortMethod);
        view.addObject("departureFlights", departureFlights);
        if(returnFlights.getSize()>0) {
            returnFlights.sortFlights(sortby + sortMethod);
            view.addObject("returnFlights", returnFlights);
        }
        return view;
    }

}
