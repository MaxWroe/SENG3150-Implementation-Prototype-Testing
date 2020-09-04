package group3.seng3150;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;
import group3.seng3150.entities.Price;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/*
Author: Chris Mather
Description: this class stores a list of flights and corresponding lists of availabilities and prices for those flights
and contains methods to get information on the flight plan
 */


public class FlightPlan implements Comparable<FlightPlan>, Cloneable{
    private List<Flight> flights;
    private List<Availability> availabilities;
    private int position;
    private List<Price> prices;
    private List<Boolean> flightSponsored;

    public FlightPlan(){
        flights = new LinkedList<>();
        availabilities = new LinkedList<>();
        prices = new LinkedList<>();
        flightSponsored = new LinkedList<>();
    }

    public FlightPlan(List<Flight> parsedFlights){
        flights = parsedFlights;
        availabilities = new LinkedList<>();
        prices = new LinkedList<>();
        flightSponsored = new LinkedList<>();
    }

    public Timestamp getDepartureDate(){
        return flights.get(0).getDepartureDate();
    }

    public Timestamp getArrivalDate(){
        return flights.get(flights.size()-1).getArrivalDate();
    }

    //returns airline names of the flights
    public List<String> getAirlines(){
        List<String> airlines = new LinkedList<String>();
        //String airlines = "";
        for (Flight flight : flights) {
            //airlines += flight.getAirlineCode();
            airlines.add(flight.getAirlineCode());
        }
        return airlines;
    }

    //sets availabilities so that availabilities stored are those from the parsed in list corresponding to the stored flights
    public void setAvailabilitiesFiltered(List<Availability> parsedAvailabilities){
        for(int i=0; i<flights.size(); i++){
            for(int j=0; j<parsedAvailabilities.size();j++) {
                if(flights.get(i).getFlightNumber().equals(parsedAvailabilities.get(j).getFlightNumber()) && flights.get(i).getDepartureDate().equals(parsedAvailabilities.get(j).getDepartureDate()) && flights.get(i).getAirlineCode().equals(parsedAvailabilities.get(j).getAirlineCode()) ){
                    availabilities.add(parsedAvailabilities.get(j));
                }
            }
        }
//        System.out.println(availabilities.size());
    }

    //returns a price based on a specific availability
    public double getPriceFromAvailability(Availability availability){
        for(int i=0; i<prices.size(); i++){
            if (prices.get(i).getFlightNumber().equals(availability.getFlightNumber()) && prices.get(i).getClassCode().equals(availability.getClassCode()) && prices.get(i).getEndDate().after(availability.getDepartureDate()) && prices.get(i).getStartDate().before(availability.getDepartureDate()) ){
                return prices.get(i).getPrice();
            }
        }
        return 0;
    }

    //returns sum of prices from all flights in the flight plans
    public int getPrice(){
//        System.out.println("get price method has started");
        int out = 0;
        if(availabilities.size()==0){
            return 0;
        }
        int tempInt;
        for(int i=0; i<flights.size(); i++){
            tempInt = availabilities.size()-1;
            for(int j=0; j<availabilities.size();j++) {
                if(flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber()) && flights.get(i).getAirlineCode().equals(availabilities.get(j).getAirlineCode()) && flights.get(i).getDepartureDate().equals(availabilities.get(j).getDepartureDate())) {
                    tempInt = j;
                    break;
                }
            }
            out += getPriceFromAvailability(availabilities.get(tempInt));

        }
//        System.out.println("get price method has completed");
        return  out;
    }

    //returns the number of seats as the lowest number of seats for an individual flights
    public double getNumberAvailableSeats(){
        double out = Double.MAX_VALUE;
        for(int i=0; i<flights.size();i++){
            for(int j=0; j<availabilities.size(); j++) {
                if (flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber())) {
                    if (availabilities.get(i).getNumberAvailableSeatsLeg1() < out) {
                        out = availabilities.get(i).getNumberAvailableSeatsLeg1();
                    }
                    if (availabilities.get(i).getNumberAvailableSeatsLeg2() != null) {
                        if (availabilities.get(i).getNumberAvailableSeatsLeg2() < out) {
                            out = availabilities.get(i).getNumberAvailableSeatsLeg2();
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
        tempLong = tempLong - flights.get(0).getDepartureDate().getTime();
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

    public List<Boolean> getFlightSponsored() {
        return flightSponsored;
    }

    public void setFlightSponsored(List<Boolean> flightSponsored) {
        this.flightSponsored = flightSponsored;
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

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public FlightPlan clone(){
        return new FlightPlan( flights);
    }

    public FlightPlan cloneTo(int i){
        LinkedList<Flight> tempFlights = new LinkedList<Flight>();
        int c = flights.size();
        if(i>c){
            i = c;
        }

        for(int j=0; j<i; j++){
//            tempFlights.add(flights.get(j).clone());
            tempFlights.add(flights.get(j));
        }
        return  new FlightPlan(tempFlights);
    }

    public void addFlights(FlightPlan parsedFlightPlan){
        flights.addAll(parsedFlightPlan.getFlights());
    }

    public String toString(){
        String out = "";
        for(Flight currentFlight : flights){
            out += "Flight: " + currentFlight.getDepartureCode() + " " + currentFlight.getDepartureDate() ;
            out += " to " + currentFlight.getDestination() + " " + currentFlight.getArrivalDate();
            out += ", ";
        }
        return out;
    }

    public int compareTo(FlightPlan flightPlan){
        if(getDurationTotal() > flightPlan.getDurationTotal())
        {
            return 1;
        }
        else if(getDurationTotal() < flightPlan.getDurationTotal()){
            return -1;
        }
        return 0;
    }

}
