package group3.seng3150;

import group3.seng3150.flightLogic.FlightPlanSearch;
import group3.seng3150.entities.Flight;
import group3.seng3150.entities.Airport;
import group3.seng3150.entities.Availability;
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
            @RequestParam(name="type", defaultValue="") String type,
            @RequestParam(name="depart-range", defaultValue="") String departRange,
            @RequestParam(name="departureDateRange", defaultValue="") String departureDateRange,
            @RequestParam(name="adults", defaultValue = "") int adults,
            @RequestParam(name="children", defaultValue = "") int children,
    HttpSession session)
    {

        //dates need to be strictly of '2015-09-24 09:50:00' format
        ModelAndView view = new ModelAndView("search");
        String flightNumberString = "";
        List<Airport> airports = em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
        LinkedList<String> airportDestinationCodes = new LinkedList<String>();
        for(int i=0; i<airports.size(); i++){
            airportDestinationCodes.add(airports.get(i).getDestinationCode());
        }
        FlightPlanSearch searcher = new FlightPlanSearch(airportDestinationCodes);
        int numberPeople = adults + children;
        String departureTimeStart = departureDate + " 00:00:01";
        String departureTimeEnd = departureDate + " 23:59:59";
        List<Flight> retrievedFlights = new LinkedList<>();

        if(departRange.equals("on")){
            departureTimeEnd = departureDateRange + " 23:59:59";
        }
        System.out.println("getting dates from range: " + departureTimeStart + " to " + departureTimeEnd);
        retrievedFlights = em.createQuery( "SELECT f FROM Flight f WHERE f.departureDate>='" + departureTimeStart + "'" +
                " AND f.departureDate<='" + departureTimeEnd + "'", Flight.class).getResultList();

//        retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureDate>='2020-12-13 00:00:01' AND f.departureDate<='2020-12-13 23:59:59'", Flight.class).getResultList();

//        for(int j=0; j<retrievedFlights.size(); j++){
//            System.out.println("flight: " + retrievedFlights.get(j).toString());
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureDate>='2020-12-13 00:00:01' AND f.departureDate<='2020-12-14 23:59:59'", Flight.class).getResultList();

//        for(int j=0; j<retrievedFlights.size(); j++){
//            System.out.println("flight: " + retrievedFlights.get(j).toString());
//        }

        if(retrievedFlights.size()>0) {
            //creates a string of all of the flight numbers in retrieved flights
            flightNumberString = "('" + retrievedFlights.get(0).getFlightNumber() + "'";
            for(int i=1; i<retrievedFlights.size(); i++){
                flightNumberString += ", '" + retrievedFlights.get(i).getFlightNumber() + "'";
            }
            flightNumberString += ")";

//            for(int j=0; j<retrievedFlights.size(); j++){
//                System.out.println("flight: " + retrievedFlights.get(j).toString());
//            }

            //returns a list of availabilities linked ot retrieved flights, in htis case flights all departing on departure date
            List<Availability> retrievedAvailabilities = em.createQuery("SELECT a FROM Availability a WHERE a.flightNumber IN " + flightNumberString +
                    " AND a.departureDate>='" + departureTimeStart + "'" +
                    " AND a.departureDate<='" + departureTimeEnd + "'" +
                    " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                    " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                    " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                //generates flight plans based on sent in flights
            List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, departureTimeStart, departureTimeEnd, retrievedAvailabilities);

            //sets variables in the flight holder bean
            flightPlans.setFlightPlansDeparting(departureFlightPlans);
            flightPlans.sortFlightPlansDeparting("timeascending");
//            System.out.println("sort of flight plans complete");
        }

        //only runs if a return list is desired
        if (type.equals("return")) {
            em.clear();
            flightNumberString = "";
            String returnTimeStart = returnDate + " 00:00:01";
            String returnTimeEnd = returnDate + " 23:59:59";

            //returns a list of all flights that arrive on return date
            List <Flight> retrievedFlightsR = em.createQuery( "SELECT f FROM Flight f WHERE f.arrivalDate>='" + returnTimeStart + "'" +
                    " AND f.arrivalDate<='" + returnTimeEnd + "'", Flight.class).getResultList();
            if(retrievedFlightsR.size()>0) {
                    //generates a string of all of the flight numbers in retrieved flights
                flightNumberString = "('" + retrievedFlightsR.get(0).getFlightNumber() + "'";
                for (int i = 1; i < retrievedFlightsR.size(); i++) {
                    flightNumberString += ", '" + retrievedFlightsR.get(i).getFlightNumber() + "'";
                }
                flightNumberString += ")";
                    //returns availabilities linked to retrieved flights
                List<Availability> retrievedAvailabilities = em.createQuery("SELECT a FROM Availability a WHERE a.flightNumber IN " + flightNumberString +
                        " AND a.departureDate>='" + returnTimeStart + "'" +
                        " AND a.departureDate<='" + returnTimeEnd + "'" +
                        " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                        " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                        " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                    //generates a list of flight plans based on retrieved flights
                    //sets variables for flightholder bean
                List<FlightPlan> returnFlightPlans = searcher.createFlightPlans(retrievedFlightsR, arrivalLocation, departureLocation, returnTimeStart, returnTimeEnd, retrievedAvailabilities);
                flightPlans.setFlightPlansReturning(returnFlightPlans);
                flightPlans.sortFlightPlansReturning("timeascending");
            }
        }

        System.out.println("number of flight plans Departing: " + flightPlans.getFlightPlansDeparting().size());
        flightPlans.setAllPrices(em);

        //sets the flightholder beans as objects of view
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
