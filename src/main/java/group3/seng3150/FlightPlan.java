package group3.seng3150;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FlightPlan {
    private List<Flight> flights;
    private List<Availability> availabilities;
    private EntityManager em;
    private PriceFinder priceFinder;

    public FlightPlan(EntityManager em){
        this.em = em;
        flights = new LinkedList<>();
        priceFinder = new PriceFinder(em);
    }

    public Timestamp getDepartureDate(){
        return flights.get(0).getDepartureDate();
    }

    public Timestamp getArrivalDate(){
        return flights.get(flights.size()).getArrivalDate();
    }

    public String getAirlines(){
        String airlines = "";
        for (Flight flight : flights) {
            airlines += flight.getAirlineCode();
        }
        return airlines;
    }

    public int getPrice(){

        int out = 0;
        int tempInt = availabilities.size();
        for(int i=0; i<flights.size(); i++){
            for(int j=0; j<availabilities.size();j++) {
                if(flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber()) && j<tempInt) {
                    tempInt = j;
                }
            }
            out += priceFinder.getPrice(0,availabilities.get(tempInt));
            tempInt = availabilities.size();
        }
        return  out;
    }

    public int getNumberAvailableSeats(){
        int out = Integer.parseInt(availabilities.get(0).getNumberAvailableSeatsLeg1());
        for(int i=0; i<flights.size();i++){
            if(Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg1())<out){
                out = Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg1());
            }
            if(availabilities.get(i).getNumberAvailableSeatsLeg2()!=null){
                if(Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg2())<out){
                    out = Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg2());
                }
            }
        }
        return  out;
    }

    public int getNumberStopOvers(){
        int out = -1;
        for (int i=0; i<flights.size(); i++){
            if(flights.get(i).getStopOverCode()!=null){
                out++;
            }
            out++;
        }
        return  out;
    }

    public void addToStart(Flight newFlight){
        flights.add(0,newFlight);
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void add(Flight newFlight){
        flights.add(newFlight);
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}
