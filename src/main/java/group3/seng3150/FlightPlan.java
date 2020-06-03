package group3.seng3150;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FlightPlan {
    private List<Flight> flights;
    private List<Availability> availabilities;

    private int position;

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }

    public FlightPlan(){
        flights = new LinkedList<>();
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

//    public int getPrice(){
//        int out = 0;
//        for(int i=0; i<flights.size();i++){
//            out += flights.get(i).getPrice();
//        }
//        return  out;
//    }
//
//    public int getNumberAvailableSeats(){
//        int out = Integer.parseInt(flights.get(0).getNumberAvailableSeatsLeg1());
//        for(int i=0; i<flights.size();i++){
//            if(Integer.parseInt(flights.get(i).getNumberAvailableSeatsLeg1())<out){
//                out = Integer.parseInt(flights.get(i).getNumberAvailableSeatsLeg1());
//            }
//            if(flights.get(i).getStopOverCode()!=null){
//                if(Integer.parseInt(flights.get(i).getNumberAvailableSeatsLeg2())<out){
//                    out = Integer.parseInt(flights.get(i).getNumberAvailableSeatsLeg2());
//                }
//            }
//        }
//        return  out;
//    }

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
