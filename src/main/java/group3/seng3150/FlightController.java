package group3.seng3150;

import group3.seng3150.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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

        FlightPlanSearch searcher = new FlightPlanSearch();
        int numberPeople = adults + children;
        String departureTimeStart = departureDate + " 00:00:01";
        String departureTimeEnd = departureDate + " 23:59:59";
        List<Flight> retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureCode='" + departureLocation + "'" +
                " AND f.destination='" + arrivalLocation + "'" +
                " AND f.departureDate>='" + departureTimeStart + "'" +
                " AND f.departureDate<='" + departureTimeEnd + "'" +
                " AND f.numberAvailableSeatsLeg1>=" + numberPeople +
                " AND (f.numberAvailableSeatsLeg2>=" + numberPeople + " OR f.numberAvailableSeatsLeg2='null')" +
                " AND f.classCode='" + classCode + "'"
                , Flight.class).getResultList();
//        departureFlights.setFlights(retrievedFlights);
//        departureFlights.sortFlights("departureTimeAscending");

        if(retrievedFlights.size()>0) {
            List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, false);
            departureFlights.setFlightPlans(departureFlightPlans);
            departureFlights.sortFlightPlans("departureTimeAscending");
        }
        else{
            retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureCode='" + departureLocation + "'" +
                            " AND f.departureDate>='" + departureTimeStart + "'" +
                            " AND f.departureDate<='" + departureTimeEnd + "'" +
                            " AND f.numberAvailableSeatsLeg1>=" + numberPeople +
                            " AND (f.numberAvailableSeatsLeg2>=" + numberPeople + " OR f.numberAvailableSeatsLeg2='null')" +
                            " AND f.classCode='" + classCode + "'"
                    , Flight.class).getResultList();
            List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, true);
            departureFlights.setFlightPlans(departureFlightPlans);
            departureFlights.sortFlightPlans("departureTimeAscending");
        }

        if (type.equals("return")) {
            String returnTimeStart = returnDate + " 00:00:01";
            String returnTimeEnd = returnDate + "23:59:59";
               retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureCode='" + departureLocation + "'" +
                       " AND f.destination='" + arrivalLocation + "'" +
                       " AND f.arrivalDate>='" + returnTimeStart + "'" +
                       " AND f.arrivalDate<='" + returnTimeEnd + "'" +
                       " AND f.numberAvailableSeatsLeg1>=" + numberPeople +
                       " AND (f.numberAvailableSeatsLeg2>=" + numberPeople + " OR f.numberAvailableSeatsLeg2=null)" +
                       " AND f.classCode='" + classCode + "'"
                       , Flight.class).getResultList();
//               returnFlights.setFlights(retrievedFlights);
//               returnFlights.sortFlights("departureTimeAscending");
            if(retrievedFlights.size()>0) {
                List<FlightPlan> returnFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, false);
                returnFlights.setFlightPlans(returnFlightPlans);
                returnFlights.sortFlightPlans("departureTimeAscending");
            }
            else{
                retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.destination='" + arrivalLocation + "'" +
                                " AND f.arrivalDate>='" + returnTimeStart + "'" +
                                " AND f.arrivalDate<='" + returnTimeEnd + "'" +
                                " AND f.numberAvailableSeatsLeg1>=" + numberPeople +
                                " AND (f.numberAvailableSeatsLeg2>=" + numberPeople + " OR f.numberAvailableSeatsLeg2=null)" +
                                " AND f.classCode='" + classCode + "'"
                        , Flight.class).getResultList();
                List<FlightPlan> returnFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, true);
                returnFlights.setFlightPlans(returnFlightPlans);
                returnFlights.sortFlightPlans("departureTimeAscending");
            }
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
        departureFlights.sortFlightPlans(sortby+sortMethod);
        view.addObject("departureFlights", departureFlights);
        if(returnFlights.getSize()>0) {
            returnFlights.sortFlightPlans(sortby + sortMethod);
            view.addObject("returnFlights", returnFlights);
        }
        return view;
    }

}
