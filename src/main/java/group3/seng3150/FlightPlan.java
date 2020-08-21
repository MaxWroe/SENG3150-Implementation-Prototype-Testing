package group3.seng3150;

import group3.seng3150.flightLogic.PriceFinder;
import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;
import group3.seng3150.entities.Price;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
Author: Chris Mather
Description: this class stores a list of flights and corresponding lists of availabilities and prices for those flights
and contains methods to get information on the flight plan
 */


public class FlightPlan implements Comparable<FlightPlan> {
    private List<Flight> flights;
    private List<Availability> availabilities;
    private int position;
    private List<Price> prices;


    public FlightPlan(){
        flights = new LinkedList<>();
        availabilities = new LinkedList<>();
        prices = new LinkedList<>();
    }

    public FlightPlan(List<Flight> parsedFlights){
        flights = parsedFlights;
        availabilities = new LinkedList<>();
        prices = new LinkedList<>();
    }

    public Timestamp getDepartureDate(){
        return flights.get(0).getDepartureDate();
    }

    public Timestamp getArrivalDate(){
        return flights.get(flights.size()-1).getArrivalDate();
    }

    //returns airline names of the flights
    public String getAirlines(){
        String airlines = "";
        for (Flight flight : flights) {
            airlines += flight.getAirlineCode();
        }
        return airlines;
    }

    //sets availabilities so that availabilities stored are those from the parsed in list corresponding to the stored flights
    public void setAvailabilitiesFiltered(List<Availability> parsedAvailabilities){
        for(int i=0; i<flights.size(); i++){
            for(int j=0; j<parsedAvailabilities.size();j++) {
                if(flights.get(i).getFlightNumber().equals(parsedAvailabilities.get(j).getFlightNumber())){
                    availabilities.add(parsedAvailabilities.get(j));
                }
            }
        }
//        System.out.println(availabilities.size());
    }

    //sets prices to those corresponding to stored availabilities
    public void setPrices(EntityManager em){
        PriceFinder priceFinder = new PriceFinder(em);
        for(int i=0; i<availabilities.size(); i++){
            List<Price> tempList = priceFinder.getPrice(0,availabilities.get(i));
            for (int j=0; j<tempList.size(); j++){
                prices.add(tempList.get(j));
            }
        }
    }

    //returns a price based on a specific availability
    public int getPriceFromAvailability(Availability availability){
        for(int i=0; i<prices.size(); i++){
            if (prices.get(i).getFlightNumber().equals(availability.getFlightNumber())  && prices.get(i).getClassCode().equals(availability.getClassCode())){
                return prices.get(i).getPrice();
            }
        }
        return 0;
    }

    //returns sum of prices from all flights in the flight plans
    public int getPrice(){
        int out = 0;
        if(availabilities.size()==0){
            return 0;
        }
        int tempInt;
        for(int i=0; i<flights.size(); i++){
            tempInt = availabilities.size()-1;
            for(int j=0; j<availabilities.size();j++) {
                if(flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber()) && j<tempInt) {
                    tempInt = j;
                }
            }
            out += getPriceFromAvailability(availabilities.get(tempInt));

        }
        return  out;
    }

    //returns the number of seats as the lowest number of seats for an individual flights
    public int getNumberAvailableSeats(){
        int out = 100;
        for(int i=0; i<flights.size();i++){
            for(int j=0; j<availabilities.size(); j++) {
                if (flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber())) {
                    if (Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg1()) < out) {
                        out = Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg1());
                    }
                    if (availabilities.get(i).getNumberAvailableSeatsLeg2() != null) {
                        if (Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg2()) < out) {
                            out = Integer.parseInt(availabilities.get(i).getNumberAvailableSeatsLeg2());
                        }
                    }
                    break;
                }
            }
        }
        return  out;
    }

    //returns total number of locations that a flight plan has so 2 + every stop over location
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

    public int getDurationTotal(){
        if(flights.size()==0){
            return Integer.MAX_VALUE;
        }
        long tempLong = 0;
        tempLong = flights.get(flights.size()-1).getArrivalDate().getTime();
        tempLong = tempLong - flights.get(flights.size()-1).getDepartureDate().getTime();
        int out = (int)tempLong/(1000*60);
        return out;
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

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }

    public int getNumberOfFlights(){
        return flights.size();
    }

    public FlightPlan copyTo(int i){
        LinkedList<Flight> cloneFlights = new LinkedList<Flight>();
        if(i>flights.size()){
            i=flights.size();
        }
        for(int j=0; j<i; j++){
            Flight tempFlight = flights.get(j);
            cloneFlights.add(tempFlight);
        }
        FlightPlan outFlightPlan = new FlightPlan(cloneFlights);
        return outFlightPlan;
    }

    public void addFlights(FlightPlan parsedFlightPlan){
        flights.addAll(parsedFlightPlan.getFlights());
    }

    public String toString(){
        String out = "";
        for(Flight currentFlight : flights){
            out += "Flight: " + currentFlight.getDepartureCode() + " to " + currentFlight.getDestination() + ", ";
        }
        return out;
    }

    public int compareTo(FlightPlan flightPlan){
        if(getDurationTotal() < flightPlan.getDurationTotal())
        {
            return 1;
        }
        else if(getDurationTotal() > flightPlan.getDurationTotal()){
            return -1;
        }
        return 0;
    }
}
