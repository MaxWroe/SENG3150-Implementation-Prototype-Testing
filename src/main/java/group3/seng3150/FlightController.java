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
import java.util.List;

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
            @RequestParam(name="children", defaultValue = "") int children,
    HttpSession session)
    {

        //dates need to be strictly of '2015-09-24 09:50:00' format
        ModelAndView view = new ModelAndView("search");
        String flightNumberString = "";
        FlightPlanSearch searcher = new FlightPlanSearch();
        int numberPeople = adults + children;
        String departureTimeStart = departureDate + " 00:00:01";
        String departureTimeEnd = departureDate + " 23:59:59";
        List<Flight> retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureCode='" + departureLocation + "'" +
                " AND f.destination='" + arrivalLocation + "'" +
                " AND f.departureDate>='" + departureTimeStart + "'" +
                " AND f.departureDate<='" + departureTimeEnd + "'", Flight.class).getResultList();

        if(retrievedFlights.size()>0) {
            flightNumberString = "('" + retrievedFlights.get(0).getFlightNumber() + "'";
            for(int i=1; i<retrievedFlights.size(); i++){
                flightNumberString += ", '" + retrievedFlights.get(i).getFlightNumber() + "'"; }
            flightNumberString += ")";
            List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                " AND a.departureDate>='" + departureTimeStart + "'" +
                " AND a.departureDate<='" + departureTimeEnd + "'" +
                " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
            List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, false, departureTimeStart, retrievedAvailabilities);
            departureFlights.setFlightPlans(departureFlightPlans);
            departureFlights.sortFlightPlans("departureTimeAscending");
            departureFlights.setAllPrices(em);
        }
        else{
            retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureDate>='" + departureTimeStart + "'" +
                " AND f.departureDate<='" + departureTimeEnd + "'", Flight.class).getResultList();
            if(retrievedFlights.size()>0) {
                    flightNumberString = "('" + retrievedFlights.get(0).getFlightNumber() + "'";
                for(int i=1; i<retrievedFlights.size(); i++){
                    flightNumberString += ", '" + retrievedFlights.get(i).getFlightNumber() + "'"; }
                flightNumberString += ")";
                List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                    " AND a.departureDate>='" + departureTimeStart + "'" +
                    " AND a.departureDate<='" + departureTimeEnd + "'" +
                    " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                    " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                    " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                List<FlightPlan> departureFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, true, departureTimeStart, retrievedAvailabilities);
                departureFlights.setFlightPlans(departureFlightPlans);
                departureFlights.sortFlightPlans("departureTimeAscending");
                departureFlights.setAllPrices(em);
            }
        }

        if (type.equals("return")) {
            flightNumberString = "";
            String returnTimeStart = returnDate + " 00:00:01";
            String returnTimeEnd = returnDate + "23:59:59";
            retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.departureCode='" + arrivalLocation + "'" +
                " AND f.destination='" + departureLocation + "'" +
                " AND f.arrivalDate>='" + returnTimeStart + "'" +
                " AND f.arrivalDate<='" + returnTimeEnd + "'", Flight.class).getResultList();

            if(retrievedFlights.size()>0) {
                flightNumberString = "('" + retrievedFlights.get(0).getFlightNumber() + "'";
                for(int i=1; i<retrievedFlights.size(); i++){
                    flightNumberString += ", '" + retrievedFlights.get(i).getFlightNumber() + "'"; }
                flightNumberString += ")";
                List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                    " AND a.departureDate>='" + departureTimeStart + "'" +
                    " AND a.departureDate<='" + departureTimeEnd + "'" +
                    " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                    " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                    " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                List<FlightPlan> returnFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, false, returnTimeStart, retrievedAvailabilities);
                returnFlights.setFlightPlans(returnFlightPlans);
                returnFlights.sortFlightPlans("departureTimeAscending");
                returnFlights.setAllPrices(em);
            }
            else{
                retrievedFlights = em.createQuery( "SELECT f From Flight f WHERE f.arrivalDate>='" + returnTimeStart + "'" +
                    " AND f.arrivalDate<='" + returnTimeEnd + "'", Flight.class).getResultList();
                if(retrievedFlights.size()>0) {
                    flightNumberString = "('" + retrievedFlights.get(0).getFlightNumber() + "'";
                    for (int i = 1; i < retrievedFlights.size(); i++) {
                        flightNumberString += ", '" + retrievedFlights.get(i).getFlightNumber() + "'";
                    }
                    flightNumberString += ")";
                    List<Availability> retrievedAvailabilities = em.createQuery("SELECT a from Availability a WHERE a.flightNumber IN " + flightNumberString +
                            " AND a.departureDate>='" + departureTimeStart + "'" +
                            " AND a.departureDate<='" + departureTimeEnd + "'" +
                            " AND a.numberAvailableSeatsLeg1>=" + numberPeople +
                            " AND (a.numberAvailableSeatsLeg2>=" + numberPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                            " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
                    List<FlightPlan> returnFlightPlans = searcher.createFlightPlans(retrievedFlights, departureLocation, arrivalLocation, true, returnTimeStart, retrievedAvailabilities);
                    returnFlights.setFlightPlans(returnFlightPlans);
                    returnFlights.sortFlightPlans("departureTimeAscending");
                    returnFlights.setAllPrices(em);
                }
            }
        }

        System.out.println(departureFlights.getFlightPlans().size());
//        session.setAttribute("flightHolder", departureFlights);
        view.addObject("departureFlights", departureFlights);
        view.addObject("returnFlights", returnFlights);
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
        departureFlights.sortFlightPlans(sortby+sortMethod);
        view.addObject("departureFlights", departureFlights);
        if(returnFlights.getFlightPlans().size()>0) {
            returnFlights.sortFlightPlans(sortby + sortMethod);
            view.addObject("returnFlights", returnFlights);
        }
        return view;
    }

}
