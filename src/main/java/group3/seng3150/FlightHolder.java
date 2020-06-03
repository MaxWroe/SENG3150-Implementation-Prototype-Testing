package group3.seng3150;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightHolder {
    private List<Flight> flights;
    private FlightsSort sorter;
    private List<FlightPlan> flightPlans;
    private List<Availability> availabilities;

    public FlightHolder(){
        flights = new ArrayList<>();
        sorter = new FlightsSort();
        flightPlans = new ArrayList<>();
    }



    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public List<Flight> sortFlights(String sortMethod){
        flights = sorter.sortFlight(flights, sortMethod);
        return flights;
    }

    public List<FlightPlan> sortFlightPlans(String sortMethod){
        flightPlans = sorter.sortFlightPlan(flightPlans, sortMethod);
        return flightPlans;
    }

    public int getSize(){
        return flights.size();
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

    public FlightsSort getSorter() {
        return sorter;
    }

    public void setSorter(FlightsSort sorter) {
        this.sorter = sorter;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}
