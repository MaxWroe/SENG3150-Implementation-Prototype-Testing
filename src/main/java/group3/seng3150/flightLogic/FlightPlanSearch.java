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
    private ArrayList<Airport> airports;
    private FlightPlanSearchFunctions searchFunctions;
    private DijkstraAlgorithmFPS dijkstraSearch;
    private YensAlgorithmFPS yensSearch;
    private FlightPlanSearchSQL sqlSearch;

    public FlightPlanSearch(){
        airports = new ArrayList<>();
        searchFunctions = new FlightPlanSearchFunctions();
        dijkstraSearch = new DijkstraAlgorithmFPS();
        yensSearch = new YensAlgorithmFPS();
        sqlSearch = new FlightPlanSearchSQL();
    }

    public FlightPlanSearch(EntityManager em){
        searchFunctions = new FlightPlanSearchFunctions();
        dijkstraSearch = new DijkstraAlgorithmFPS();
        yensSearch = new YensAlgorithmFPS();
        sqlSearch = new FlightPlanSearchSQL();
        airports = new ArrayList<>();
        setAirports(em);
    }

    //finds and returns a list of flight plans from sent in flights that match the criteria
    public List<FlightPlan> searchFlightPlans(String departureLocation, String destination, String departureDate, String classCode, int departureDateRange, int numberOfPeople, EntityManager em){
        List<FlightPlan> flightPlans = new LinkedList<>();

        String timeStartString = departureDate + " 00:00:01";
        String timeEndString = departureDate + " 23:59:59";
        Timestamp timeStart = Timestamp.valueOf(timeStartString);
        Timestamp timeEnd = Timestamp.valueOf(timeEndString);

        timeEnd.setTime(timeEnd.getTime() + (24*60*60*1000)*departureDateRange);

        for(int i=0; i<2; i++) {
            List<Flight> flights = sqlSearch.retrieveFlights(timeStart, timeEnd, em);
            flights = searchFunctions.filterFlightsCOVID(flights, airports);
            String flightNumbersString = searchFunctions.getFlightNumbersSQLField(flights);
            List<Availability> availabilities = sqlSearch.retrieveAvailabilities(timeStart, timeEnd, numberOfPeople, classCode, flightNumbersString, em);
            if (availabilities.size() > 0) {
                flights = searchFunctions.filterByAvailabilities(flights, availabilities);


                flightPlans = buildFlightPlansYens(flights, departureLocation, destination, timeStart, timeEnd);
                flightPlans = searchFunctions.setFlightPlansAvailabilities(flightPlans, availabilities);
                flightPlans = searchFunctions.setPrices(flightPlans, em);
                flightPlans = searchFunctions.setSponsoredAirlines(flightPlans, em);
            }
            if(flightPlans.size()>0){
                return flightPlans;
            }
            else{
                timeEnd.setTime(timeEnd.getTime() + (24*60*60*1000));
            }
        }
        return flightPlans;
    }


    public FlightPlan getSingleFlightPlan(String departureLocation, String destination, String departureDate, String classCode, int departureDateRange,  int numberOfPeople, EntityManager em){
        FlightPlan flightPlan = null;

        String timeStartString = departureDate + " 00:00:01";
        String timeEndString = departureDate + " 23:59:59";
        Timestamp timeStart = Timestamp.valueOf(timeStartString);
        Timestamp timeEnd = Timestamp.valueOf(timeEndString);

        if(departureDateRange > 0){
            timeEnd.setTime(timeEnd.getTime() + (24*60*60*1000)*departureDateRange);
        }

        List<Flight> flights = sqlSearch.retrieveFlights(timeStart, timeEnd, em);
        flights = searchFunctions.filterFlightsCOVID(flights, airports);
        String flightNumbersString = searchFunctions.getFlightNumbersSQLField(flights);

        List<Availability> availabilities = sqlSearch.retrieveAvailabilities(timeStart, timeEnd, numberOfPeople, classCode, flightNumbersString, em);
        if(availabilities.size()>0) {
            flights = searchFunctions.filterByAvailabilities(flights, availabilities);

            flightPlan = dijkstraSearch.getShortestPathDuration(searchFunctions.buildGraph(flights, airports), departureLocation, destination, timeStart);
            if(flightPlan != null) {
                List<FlightPlan> flightPlans = new LinkedList<>();
                flightPlans.add(flightPlan);
                flightPlans = searchFunctions.setFlightPlansAvailabilities(flightPlans, availabilities);
                flightPlans = searchFunctions.setPrices(flightPlans, em);
                flightPlans = searchFunctions.setSponsoredAirlines(flightPlans, em);
                flightPlan = flightPlans.get(0);
            }
        }
        return flightPlan;
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

    private void setAirports(EntityManager em) {
        List<Airport> retrievedAirports = sqlSearch.retrieveAirports(em);
        if(retrievedAirports!= null && retrievedAirports.size()>0){
            airports.addAll(retrievedAirports);
        }
    }
}
