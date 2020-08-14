package group3.seng3150;

import group3.seng3150.flightLogic.FlightPlanSearch;
import group3.seng3150.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class FlightController{
    private FlightHolder flightPlans;
    private EntityManager em;

    @Autowired
    public FlightController(FlightHolder flightPlans, EntityManager em) {
        this.flightPlans = flightPlans;
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
            @RequestParam(name="type") String type,
            @RequestParam(name="adults", defaultValue = "") int adults,
            @RequestParam(name="children", defaultValue = "") int children,
    HttpSession session)
    {

        //dates need to be strictly of '2015-09-24 09:50:00' format
        ModelAndView view = new ModelAndView("search");
        String flightNumberString = "";
        List<Airport> airports = em.createQuery("SELECT a From Airport a", Airport.class).getResultList();
        LinkedList<String> airportDestinationCodes = new LinkedList<String>();
        for(int i=0; i<airports.size(); i++){
            airportDestinationCodes.add(airports.get(i).getDestinationCode());
        }
        FlightPlanSearch searcher = new FlightPlanSearch(airportDestinationCodes);
        int numberPeople = adults + children;
        String departureTimeStart = departureDate + " 00:00:01";
        String departureTimeEnd = departureDate + " 23:59:59";


        //this returns a lst of flights going directly from the departure locaiton to the arrival locaiton
        List<Flight> retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureCode='" + departureLocation + "'" +
                " AND f.destination='" + arrivalLocation + "'" +
                " AND f.departureDate>='" + departureTimeStart + "'" +
                " AND f.departureDate<='" + departureTimeEnd + "'", Flight.class).getResultList();

        if(retrievedFlights.size()>0) {
            //this generates a string of all of the flight numbers in retrieved flights
            flightNumberString = "('" + retrievedFlights.get(0).getFlightNumber() + "'";
            for(int i=1; i<retrievedFlights.size(); i++){
                flightNumberString += ", '" + retrievedFlights.get(i).getFlightNumber() + "'"; }
            flightNumberString += ")";
            //this returns a list of all of the availabilities that are linked to the retrieved flights
            List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                " AND a.departureDate>='" + departureTimeStart + "'" +
                " AND a.departureDate<='" + departureTimeEnd + "'" +
                " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
            //creates flight plans based on list of retrieved flights
            List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, false, departureTimeStart, retrievedAvailabilities);
            //sets variables retrieved to the flight holder bean
            flightPlans.setFlightPlansDeparting(departureFlightPlans);
            flightPlans.sortFlightPlansDeparting("departureTimeAscending");
        }
        else{
            //this returns all flights that depart on the departure date
            retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureDate>='" + departureTimeStart + "'" +
                " AND f.departureDate<='" + departureTimeEnd + "'", Flight.class).getResultList();
            if(retrievedFlights.size()>0) {
                //creates a string of all of the flight numbers in retrieved flights
                    flightNumberString = "('" + retrievedFlights.get(0).getFlightNumber() + "'";
                for(int i=1; i<retrievedFlights.size(); i++){
                    flightNumberString += ", '" + retrievedFlights.get(i).getFlightNumber() + "'"; }
                flightNumberString += ")";
                //returns a list of availabilities linked ot retireved flights, in htis case flights all departing on departure date
                List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                    " AND a.departureDate>='" + departureTimeStart + "'" +
                    " AND a.departureDate<='" + departureTimeEnd + "'" +
                    " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                    " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                    " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                //generates flight plans based on sent in flights
                List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, true, departureTimeStart, retrievedAvailabilities);
                //sets variables in the flight holder bean
                flightPlans.setFlightPlansDeparting(departureFlightPlans);
                flightPlans.sortFlightPlansDeparting("departureTimeAscending");
            }
        }

        //only runs if a return list is desired
        if (type.equals("return")) {
            em.clear();
            flightNumberString = "";
            String returnTimeStart = returnDate + " 00:00:01";
            String returnTimeEnd = returnDate + " 23:59:59";
            //returns a list of flights that all arrive on the date sent in that depart from the arrival location and arrive at departure location
            List<Flight> retrievedFlightsR = em.createQuery( "SELECT f From Flight f WHERE f.departureCode='" + arrivalLocation + "'" +
                " AND f.destination='" + departureLocation + "'" +
                " AND f.arrivalDate>='" + returnTimeStart + "'" +
                " AND f.arrivalDate<='" + returnTimeEnd + "'", Flight.class).getResultList();

            if(retrievedFlightsR.size()>0) {
                //generates a string of all the flight numbers in retrieved flights
                flightNumberString = "('" + retrievedFlightsR.get(0).getFlightNumber() + "'";
                for(int i=1; i<retrievedFlightsR.size(); i++){
                    flightNumberString += ", '" + retrievedFlightsR.get(i).getFlightNumber() + "'"; }
                flightNumberString += ")";
                //this returns a list of availabilities linked to retrieved flights
                List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                    " AND a.departureDate>='" + returnTimeStart + "'" +
                    " AND a.departureDate<='" + returnTimeEnd + "'" +
                    " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                    " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                    " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                //creates flight plans based on retrieved flights
                //sets variables for flight holder bean
                List<FlightPlan> returnFlightPlans = searcher.createFlightPlans(retrievedFlightsR, arrivalLocation, departureLocation, false, returnTimeStart, retrievedAvailabilities);
                flightPlans.setFlightPlansReturning(returnFlightPlans);
                flightPlans.sortFlightPlansReturning("departureTimeAscending");

            }
            else{
                //returns a list of all flights that arrive on return date
                retrievedFlightsR = em.createQuery( "SELECT f From Flight f WHERE f.arrivalDate>='" + returnTimeStart + "'" +
                    " AND f.arrivalDate<='" + returnTimeEnd + "'", Flight.class).getResultList();
                if(retrievedFlightsR.size()>0) {
                    //generates a string of all of the flight numbers in retrieved flights
                    flightNumberString = "('" + retrievedFlightsR.get(0).getFlightNumber() + "'";
                    for (int i = 1; i < retrievedFlightsR.size(); i++) {
                        flightNumberString += ", '" + retrievedFlightsR.get(i).getFlightNumber() + "'";
                    }
                    flightNumberString += ")";
                    //returns availabilities linked to retrieved flights
                    List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                            " AND a.departureDate>='" + returnTimeStart + "'" +
                            " AND a.departureDate<='" + returnTimeEnd + "'" +
                            " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                            " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                            " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                    //generates a list of flight plans based on retrieved flights
                    //sets variables for flightholder bean
                    List<FlightPlan> returnFlightPlans = searcher.createFlightPlans(retrievedFlightsR, arrivalLocation, departureLocation, true, returnTimeStart, retrievedAvailabilities);
                    flightPlans.setFlightPlansReturning(returnFlightPlans);
                    flightPlans.sortFlightPlansReturning("departureTimeAscending");

                }
            }
        }

        flightPlans.setAllPrices(em);
//        System.out.println(departureFlights.getFlightPlans().size());
//        session.setAttribute("flightHolder", departureFlights);
        //sets the two flightholder beans as objects of view
        view.addObject("flights", flightPlans);
        return view;
    }

    @GetMapping
    @RequestMapping("/sort")
    public ModelAndView search(
            @RequestParam(name="sortby", defaultValue="") String sortby,
            @RequestParam(name="sortMethod", defaultValue="") String sortMethod,
            HttpSession session
    ){
        ModelAndView view = new ModelAndView("sort");
        //sorts flights in the flightHolder bean to desired sorting method
        flightPlans.sortFlightPlansDeparting(sortby+sortMethod);
        flightPlans.sortFlightPlansReturning(sortby+sortMethod);
        view.addObject("flights", flightPlans);

        return view;
    }

}
