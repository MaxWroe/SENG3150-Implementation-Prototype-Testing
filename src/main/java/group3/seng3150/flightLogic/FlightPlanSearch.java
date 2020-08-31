package group3.seng3150.flightLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.Airport;
import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;
import group3.seng3150.entities.Price;
import org.springframework.security.core.parameters.P;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.*;

/*
Author: Chris Mather
Description: this class processes a list of flights and returns information about it
 */

//current methods
/*
public List<FlightPlan> createFlightPlans(List<Flight> flights, String departureLocation, String destination, String startingTimeString, String endingTimeString, List<Availability> parsedAvailabilities)
private List<FlightPlan> SetFlightPlansAvailabilities(List<FlightPlan> flightPlans, List<Availability> availabilities)

private List<Flight> filterByAvailabilities(List<Flight> flights, List<Availability> availabilities)
private DijkstraGraph buildGraph(List<Flight> flights)
private List<FlightPlan> removeDuplicateFlightPlans(List<FlightPlan> parsedFlightPlans)
private void setAirports(List<String> parsedAirports)
 */

public class FlightPlanSearch {
    private ArrayList<String> airports;
    private FlightPlanSearchFunctions searchFunctions;
    private DijkstraAlgorithmFPS dijkstraSearch;
    private YensAlgorithmFPS yensSearch;

    public FlightPlanSearch(){
        airports = new ArrayList<>();
        searchFunctions = new FlightPlanSearchFunctions();
        dijkstraSearch = new DijkstraAlgorithmFPS();
        yensSearch = new YensAlgorithmFPS();
    }

    public FlightPlanSearch(EntityManager em){
        airports = new ArrayList<>();
        setAirports(em);
        searchFunctions = new FlightPlanSearchFunctions();
        dijkstraSearch = new DijkstraAlgorithmFPS();
        yensSearch = new YensAlgorithmFPS();
    }

    //finds and returns a list of flight plans from sent in flights that match the criteria
    public List<FlightPlan> searchFlightPlans(String departureLocation, String destination, String departureDate, String classCode, int departureDateRange, int numberOfPeople, EntityManager em){
        List<FlightPlan> flightPlans = new LinkedList<>();

        String timeStartString = departureDate + " 00:00:01";
        String timeEndString = departureDate + " 23:59:59";
        Timestamp timeStart = Timestamp.valueOf(timeStartString);
        Timestamp timeEnd = Timestamp.valueOf(timeEndString);
        if(departureDateRange > 0){
            timeEnd.setTime(timeEnd.getTime() + (24*60*60*1000)*departureDateRange);
        }

        List<Flight> flights = retrieveFlights(timeStart,timeEnd,em);
        String flightNumbersString = searchFunctions.getFlightNumbersSQLField(flights);

        List<Availability> availabilities = retrieveAvailabilities(timeStart, timeEnd, numberOfPeople, classCode,flightNumbersString ,em);
        flights = searchFunctions.filterByAvailabilities(flights, availabilities);

        flightPlans = buildFlightPlansYens(flights, departureLocation, destination, timeStart, timeEnd);
        flightPlans = searchFunctions.SetFlightPlansAvailabilities(flightPlans, availabilities);

        return flightPlans;
    }



    private List<FlightPlan> buildFlightPlansYens(List<Flight> flights, String departureLocation, String destination, Timestamp startingTime, Timestamp endingTime) {
        List<FlightPlan> flightPlans = new LinkedList<>();
        Timestamp inputTime = startingTime;
        int numberOfCycles = 7;

        if(flights.size()>0){
            for(int i=0; i<numberOfCycles; i++) {
                DijkstraGraph graph = searchFunctions.buildGraph(flights, airports);
                flightPlans.addAll(yensSearch.getKShortestPaths(graph, departureLocation, destination, inputTime, 10));
                inputTime = new Timestamp(startingTime.getTime()+((endingTime.getTime() - startingTime.getTime())/numberOfCycles)*(i+1));
//                System.out.println("date for removal: " + inputTime);
                for(int j=0; j<flights.size(); j++){
                    if(flights.get(j).getDepartureDate().before(inputTime)){
//                        System.out.println("removing flight: " + filteredFlights.get(j).toString());
                        flights.remove(j);
                        j--;
                    }
                }

            }
        }
        System.out.println("number of flight plans produced by Yenns: " + flightPlans.size());

        //sets availabilities and removes duplicates
        if(flightPlans.size()>0) {
            flightPlans = searchFunctions.removeDuplicateFlightPlans(flightPlans);
        }
        System.out.println("flight plan search complete");
        return flightPlans;
    }

    private List<Flight> retrieveFlights(Timestamp StartTime, Timestamp endTime, EntityManager em){
        List<Flight> flights = new LinkedList<>();
        flights = em.createQuery( "SELECT f FROM Flight f WHERE f.departureDate>='" + StartTime + "'" +
                " AND f.departureDate<='" + endTime + "'", Flight.class).getResultList();
        return flights;
    }

    private List<Availability> retrieveAvailabilities(Timestamp StartTime, Timestamp endTime, int numberOfPeople, String classCode, String flightNumbers, EntityManager em){
        List<Availability> availabilities = new LinkedList<>();
        availabilities = em.createQuery("SELECT a FROM Availability a WHERE a.flightNumber IN " + flightNumbers +
                " AND a.departureDate>='" + StartTime + "'" +
                " AND a.departureDate<='" + endTime + "'" +
                " AND a.numberAvailableSeatsLeg1>=" + numberOfPeople +
                " AND (a.numberAvailableSeatsLeg2>=" + numberOfPeople + " OR a.numberAvailableSeatsLeg2='null')" +
                " AND a.classCode='" + classCode + "'", Availability.class).getResultList();
        return availabilities;
    }

    private void setAirports(EntityManager em){
        List<Airport> airportsRetrieved = em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
        for(int i=0; i<airports.size(); i++){
            airports.add(airportsRetrieved.get(i).getDestinationCode());
        }

    }

    //method that sets availabilities of a sent in flight plans




}
