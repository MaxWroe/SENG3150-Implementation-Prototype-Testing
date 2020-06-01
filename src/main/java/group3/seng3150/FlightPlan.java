package group3.seng3150;

import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FlightPlan {
    private List<Flight> flights;

    public FlightPlan(){
        flights = new LinkedList<>();
    }

    public Timestamp getDepartureDate(){
        return flights.get(0).getDepartureDate();
    }

    public int getPrice(){
        int out = 0;
        for(int i=0; i<flights.size();i++){
            out += flights.get(i).getPrice();
        }
        return  out;
    }

//    public int getNumberAvailableSeats(){
//        int out = flights.get(0).getNumberAvailableSeatsLeg1();
//        for(int i=0; i<flights.size();i++){
//            if(flights.get(i).getNumberAvailableSeatsLeg1()<out){
//                out = flights.get(i).getNumberAvailableSeatsLeg1();
//            }
//            if(flights.get(i).getStopOverCode()!=null){
//                if(flights.get(i).getNumberAvailableSeatsLeg2()<out){
//                    out = flights.get(i).getNumberAvailableSeatsLeg2();
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
}
