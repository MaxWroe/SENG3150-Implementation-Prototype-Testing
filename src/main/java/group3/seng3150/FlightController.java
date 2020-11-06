/*
Author: Chris Mather
Description: this class takes the search call from the JSPs and uses the Flight Plan Search to find flights and stores retrieved lists into the flight holder bean
*/

package group3.seng3150;

import group3.seng3150.flightLogic.FlightPlanSearch;
import group3.seng3150.entities.Flight;
import group3.seng3150.entities.Airport;
import group3.seng3150.entities.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class FlightController{
    private FlightHolder flightPlans;
    private EntityManager em;

    @Autowired
    public FlightController(FlightHolder flightPlans, EntityManager em) {
        this.flightPlans = flightPlans;
        this.em =em;
    }

    @PostMapping("/search")
    public ModelAndView search(
            @RequestParam(name="departureLocation", defaultValue="") String departureLocation,
            @RequestParam(name="arrivalLocation", defaultValue="") String arrivalLocation,
            @RequestParam(name="departureDate", defaultValue="") String departureDate,
            @RequestParam(name="returnDate", defaultValue="") String returnDate,
            @RequestParam(name="classCode", defaultValue="") String classCode,
            @RequestParam(name="departureDateRange", defaultValue= "0") int departureDateRange,
            @RequestParam(name="returnDateRange", defaultValue= "0") int returnDateRange,
            @RequestParam(name="adults", defaultValue = "0") int adults,
            @RequestParam(name="children", defaultValue = "0") int children,
    HttpSession session)
    {

        //dates need to be strictly of '2015-09-24 09:50:00' format
        ModelAndView view = new ModelAndView("search");


        FlightPlanSearch searcher = new FlightPlanSearch(em);
        int numberPeople = adults + children;

        List<FlightPlan> departureFlightPlans = searcher.searchFlightPlans(departureLocation, arrivalLocation, departureDate, classCode, departureDateRange, numberPeople, em);

        //sets variables in the flight holder bean
        flightPlans.setFlightPlansDeparting(departureFlightPlans);
        flightPlans.sortFlightPlansDeparting("timeascending");

        //only runs if a return list is desired
        if (!returnDate.equals("")) {
            List<FlightPlan> returnFlightPlans = searcher.searchFlightPlans(arrivalLocation, departureLocation, returnDate, classCode, returnDateRange, numberPeople, em);
            flightPlans.setFlightPlansReturning(returnFlightPlans);
            flightPlans.sortFlightPlansReturning("timeascending");
        }


        //sets the flightholder beans as objects of view
        view.addObject("flights", flightPlans);
        return view;
    }

}
