package group3.seng3150;

import group3.seng3150.entities.Flight;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightHolder {
    private  List<Flight> flights;
    private FlightsSort sorter;
    private List<FlightPlan> flightPlans;
    private FlightPlanSearch searcher;

    public FlightHolder(){
        flights = new ArrayList<>();
        sorter = new FlightsSort();
        flightPlans = new ArrayList<>();
        searcher = new FlightPlanSearch();
    }



    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public List<Flight> sortFlights(String sortMethod){
        flights = sorter.sort(flights, sortMethod);
        return flights;
    }

    public int getSize(){
        return flights.size();
    }

    public List<FlightPlan> getFlightPlanOptions(List<Flight> rawFlights){
        flightPlans = searcher.createFlightPlans(rawFlights);
        return flightPlans;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<FlightPlan> getFlightPlans() {
        return flightPlans;
    }

    public void setFlightPlans(List<FlightPlan> flightPlans) {
        this.flightPlans = flightPlans;
    }
}
