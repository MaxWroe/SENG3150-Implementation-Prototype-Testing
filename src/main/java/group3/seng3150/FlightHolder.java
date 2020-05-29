package group3.seng3150;

import group3.seng3150.entities.Flight;

import java.util.LinkedList;
import java.util.List;

public class FlightHolder {
    private  List<Flight> flights;
    private FlightsSort sorter;

    public FlightHolder(){
        flights = new LinkedList<>();
        sorter = new FlightsSort();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
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
}
