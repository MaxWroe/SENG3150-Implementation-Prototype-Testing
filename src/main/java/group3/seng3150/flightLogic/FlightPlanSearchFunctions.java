package group3.seng3150.flightLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.Airport;
import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;
import group3.seng3150.entities.Price;

import javax.persistence.EntityManager;
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
            if(contains = true){
//                System.out.println("adding flight: " + flights.get(i).toString());
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
//        System.out.println("running removeDuplicateFlightPlans");
        List<FlightPlan> uniqueFlightPlans = new LinkedList<FlightPlan>();
        boolean existsIn;
        for(int i=0; i<parsedFlightPlans.size(); i++){
//            System.out.println("running loop iteration of parsedFlightPlans: " + i);
            existsIn = false;
            for(int j=0; j<uniqueFlightPlans.size(); j++){
//                System.out.println("flight plan 1: " + parsedFlightPlans.get(i).toString() + "flight plan 2: " + uniqueFlightPlans.get(j).toString());
                if (parsedFlightPlans.get(i).getFlights().equals(uniqueFlightPlans.get(j).getFlights())){
//                    System.out.println("running loop iteration of uniqueFlightPlans: " + j);

                    existsIn = true;
                }
            }
            if(existsIn==false){
                uniqueFlightPlans.add(parsedFlightPlans.get(i));
//                System.out.println("new flight plan added to list: " + uniqueFlightPlans.get(uniqueFlightPlans.size()-1).toString());
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
        FlightPlanSearchSQL sqlSearcher = new FlightPlanSearchSQL();
        for(int i=0; i<flightPlans.size(); i++){
            currentPrices = new LinkedList<>();
            for(int j=0; j<flightPlans.get(i).getAvailabilities().size(); j++){
                currentPrices.add(sqlSearcher.retrievePrice(flightPlans.get(i).getAvailabilities().get(j), em));
            }
            flightPlans.get(i).setPrices(currentPrices);
        }
        return flightPlans;
    }

    public String getFlightNumbersSQLField(List<Flight> flights){
        String flightNumberString = "('" + flights.get(0).getFlightNumber() + "'";
        for(int i=1; i<flights.size(); i++){
            flightNumberString += ", '" + flights.get(i).getFlightNumber() + "'";
        }
        flightNumberString += ")";
        return flightNumberString;
    }

}
