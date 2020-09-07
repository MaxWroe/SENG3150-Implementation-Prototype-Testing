package group3.seng3150.flightLogic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import group3.seng3150.FlightPlan;
import group3.seng3150.entities.*;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightPlanSearchFunctions {

    public FlightPlanSearchFunctions(){}

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

    public List<FlightPlan> setFlightPlansAvailabilities(List<FlightPlan> flightPlans, List<Availability> availabilities){
        List<FlightPlan> flightPlanList = flightPlans;
        if(availabilities.size()>0) {
            for (int i = 0; i < flightPlanList.size(); i++) {
                flightPlanList.get(i).setAvailabilitiesFiltered(availabilities);
            }
        }
        return flightPlanList;
    }

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

    public List<FlightPlan> filterNumberFlightsMaxSize(List<FlightPlan> parsedFlightPlans, int n){
        for (int i=0; i<parsedFlightPlans.size(); i++){
            if(parsedFlightPlans.get(i).getFlights().size()>n){
                parsedFlightPlans.remove(i);
                i--;
            }
        }
        return parsedFlightPlans;
    }

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
