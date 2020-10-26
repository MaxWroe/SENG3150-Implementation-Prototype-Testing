package group3.seng3150.flightLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.*;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Author: Chris Mather
Description: this class provides functions for manipulating list of flight plans and lists of flights in both filtering the lists and modifying the data contained within
*/

public class FlightPlanSearchFunctions {

    public FlightPlanSearchFunctions(){}

    //removes flights that do not have a corresponding availability in the sent in list
    public List<Flight> filterByAvailabilities(List<Flight> flights, List<Availability> availabilities){
        List<Flight> filteredFlights = new LinkedList<>();
        boolean contains = false;
        for(int i=0; i<flights.size(); i++){
            contains = false;
            for (int j=0; j<availabilities.size(); j++)
            {
                if(flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber()) && flights.get(i).getDepartureDate().equals(availabilities.get(j).getDepartureDate()) && flights.get(i).getAirlineCode().equals(availabilities.get(j).getAirlineCode())){
                    contains = true;
                    break;
                }
            }
            if(contains == true){
                filteredFlights.add(flights.remove(i));
                i--;
            }
        }
        return filteredFlights;
    }

    //build a Dijkstra graph where the edges are list of flights that go from one airport to another as a directed edges and the nodes are airports
    public DijkstraGraph buildGraph(List<Flight> flights, List<Airport> airports){
        ArrayList<DijkstraNode> airportFlightNodes = new ArrayList<>();
        ArrayList<String> airportsString = new ArrayList<>();
        DijkstraGraph flightsGraph = new DijkstraGraph();
        for(int i=0; i<airports.size(); i++){
            airportFlightNodes.add(new DijkstraNode(airports.get(i).getDestinationCode()));
            airportsString.add(airports.get(i).getDestinationCode());
        }
        for(int i=0; i<flights.size(); i++){
            airportFlightNodes.get(airportsString.indexOf(flights.get(i).getDepartureCode())).addDestination(airportFlightNodes.get(airportsString.indexOf(flights.get(i).getDestination())), flights.get(i));
        }

        for(int i=0; i<airportFlightNodes.size(); i++){
            flightsGraph.addNode(airportFlightNodes.get(i).getName(), airportFlightNodes.get(i));
        }
        return flightsGraph;
    }

    //removes duplicates of flight plans from the sent in list which is any two flight plans that have the same flights
    public List<FlightPlan> removeDuplicateFlightPlans(List<FlightPlan> parsedFlightPlans){
        List<FlightPlan> uniqueFlightPlans = new LinkedList<FlightPlan>();
        boolean existsIn;
        for(int i=0; i<parsedFlightPlans.size(); i++){
            existsIn = false;
            for(int j=0; j<uniqueFlightPlans.size(); j++){
                if (parsedFlightPlans.get(i).getFlights().equals(uniqueFlightPlans.get(j).getFlights())){

                    existsIn = true;
                }
            }
            if(existsIn==false){
                uniqueFlightPlans.add(parsedFlightPlans.get(i));
            }
        }
        return  uniqueFlightPlans;
    }

    //sets the availabilities variables of sent in flight plans to availabilities corresponding to Flights in the flight plans
    public List<FlightPlan> setFlightPlansAvailabilities(List<FlightPlan> flightPlans, List<Availability> availabilities){
        List<FlightPlan> flightPlanList = flightPlans;
        if(availabilities.size()>0) {
            for (int i = 0; i < flightPlanList.size(); i++) {
                flightPlanList.get(i).setAvailabilitiesFiltered(availabilities);
            }
        }
        return flightPlanList;
    }

    //sets the prices variables of sent in FlightPlans to prices corresponding to their flights from the database
    public List<FlightPlan> setPrices(List<FlightPlan> flightPlans, EntityManager em){
        List<Price> currentPrices;
        Price currentPrice = new Price();
        FlightPlanSearchSQL sqlSearcher = new FlightPlanSearchSQL();
        for(int i=0; i<flightPlans.size(); i++){
            currentPrices = new LinkedList<>();
            for(int j=0; j<flightPlans.get(i).getAvailabilities().size(); j++){
                currentPrice = sqlSearcher.retrievePrice(flightPlans.get(i).getAvailabilities().get(j), em);
                if(currentPrice != null){
                    currentPrices.add(currentPrice);
                }
            }
            flightPlans.get(i).setPrices(currentPrices);
        }
        return flightPlans;
    }

    //removes and flights from the list that departs or arrives at an airport during a COVID Restriction period
    public List<Flight> filterFlightsCOVID(List<Flight> parsedFlights, List<Airport> parsedAirports) {
        for (int i = 0; i < parsedAirports.size(); i++) {
            if (parsedAirports.get(i).getShutdownStartDate() != null && parsedAirports.get(i).getShutdownEndDate()!=null) {
                for (int j = 0; j < parsedFlights.size(); j++) {
                    if (parsedAirports.get(i).getDestinationCode().equals(parsedFlights.get(j).getDepartureCode())) {
                        if (parsedAirports.get(i).getShutdownStartDate().before(parsedFlights.get(j).getDepartureDate()) && parsedAirports.get(i).getShutdownEndDate().after(parsedFlights.get(j).getDepartureDate())) {
                            parsedFlights.remove(j);
                            j--;
                        }
                    }
                    else if (parsedAirports.get(i).getDestinationCode().equals(parsedFlights.get(j).getDestination())) {
                        if (parsedAirports.get(i).getShutdownStartDate().before(parsedFlights.get(j).getArrivalDate()) && parsedAirports.get(i).getShutdownEndDate().after(parsedFlights.get(j).getArrivalDate())) {
                            parsedFlights.remove(j);
                            j--;
                        }
                    }
                    else if (parsedAirports.get(i).getDestinationCode().equals(parsedFlights.get(j).getStopOverCode())) {
                        if (parsedAirports.get(i).getShutdownStartDate().before(parsedFlights.get(j).getDepartureTimeStopOver()) && parsedAirports.get(i).getShutdownEndDate().after(parsedFlights.get(j).getDepartureTimeStopOver())) {
                            parsedFlights.remove(j);
                            j--;
                        } else if (parsedAirports.get(i).getShutdownStartDate().before(parsedFlights.get(j).getArrivalStopOverTime()) && parsedAirports.get(i).getShutdownEndDate().after(parsedFlights.get(j).getArrivalStopOverTime())) {
                            parsedFlights.remove(j);
                            j--;
                        }
                    }

                }
            }

        }
        if (parsedFlights.size() == 0) {
            return null;
        }
        return parsedFlights;
    }

    //sets the flight Sponsored variables of sent in flight plans to sponsored status corresponding to their flights airlines from the database
    public List<FlightPlan> setSponsoredAirlines(List<FlightPlan> parsedFlightPlans, EntityManager em){
        List<Boolean> flightsSponsored;
        Airline currentAirline;
        FlightPlanSearchSQL sqlSearcher = new FlightPlanSearchSQL();
        for(int i=0; i<parsedFlightPlans.size(); i++){
            flightsSponsored = new LinkedList<>();
            for(int j=0; j<parsedFlightPlans.get(i).getFlights().size(); j++){
                flightsSponsored.add(false);
                currentAirline = sqlSearcher.retrieveAirline(parsedFlightPlans.get(i).getFlights().get(j), em);
                if(currentAirline != null && currentAirline.getSponsored() != null && currentAirline.getSponsored() == 1){
                    flightsSponsored.set(j, true);
                }
            }
            parsedFlightPlans.get(i).setFlightSponsored(flightsSponsored);
        }
        return parsedFlightPlans;
    }

    //removes any flight plans that have a list of flight larger than the sent in n
    public List<FlightPlan> filterNumberFlightsMaxSize(List<FlightPlan> parsedFlightPlans, int n){
        for (int i=0; i<parsedFlightPlans.size(); i++){
            if(parsedFlightPlans.get(i).getFlights().size()>n){
                parsedFlightPlans.remove(i);
                i--;
            }
        }
        return parsedFlightPlans;
    }

    //removes and flight plans that have a departure date after the end time
    public List<FlightPlan> filterFlightsDepartureDate(List<FlightPlan> parsedFlightPlans, Timestamp endTime){
        for(int i=0; i<parsedFlightPlans.size(); i++) {
            if(parsedFlightPlans.get(i).getDepartureDate().after(endTime)){
                parsedFlightPlans.remove(i);
                i--;
            }
        }
        return parsedFlightPlans;
    }

}
