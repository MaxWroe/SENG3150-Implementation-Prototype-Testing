package group3.seng3150;

import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.*;
//search options duration, price


public class FlightPlanSearch {
    private ArrayList<String> airports;

    public FlightPlanSearch(){
        airports = new ArrayList<>();
        setAirports();
    }

    public List<FlightPlan> createFlightPlans(List<Flight> flights, String departureLocation, String destination, boolean stopOverNeeded, String startingTimeString, List<Availability> parsedAvailabilities){
        Timestamp startingTime = Timestamp.valueOf(startingTimeString);
        List<FlightPlan> flightPlans = new LinkedList<>();
        List<Flight> filteredFlights = filterByAvailabilities(flights, parsedAvailabilities);
        System.out.println("parsed in flights: " + flights.size() + " stop over needed: " + stopOverNeeded);
        if(stopOverNeeded && filteredFlights.size()>0){
            flightPlans.add(getShortestPathDuration(filteredFlights, departureLocation, destination, startingTime));
        }
        else{
            for(int i=0; i<filteredFlights.size(); i++){
                flightPlans.add(new FlightPlan());
                flightPlans.get(i).add(filteredFlights.get(i));
            }
        }

        return flightPlans;
    }

    private List<Flight> filterByAvailabilities(List<Flight> flights, List<Availability> availabilities){
        List<Flight> filteredFlights = new LinkedList<>();
        boolean contains = false;
        for(int i=0; i<flights.size(); i++){
            contains = false;
            for (int j=0; j<flights.size(); j++)
            {
                if(flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber())){
                    contains = true;
                }
            }
            if(contains = false){
                flights.remove(i);
                i--;
            }
        }
        return flights;
    }

    private FlightPlan getShortestPathDuration(List<Flight> flights, String departureLocation, String arrivalLocation, Timestamp startingTime){
        FlightPlan flightPlan = new FlightPlan();
        ArrayList<DijkstraNode> airportFlightNodes = new ArrayList<>();
        for(int i=0; i<airports.size(); i++){
            airportFlightNodes.add(new DijkstraNode(airports.get(i)));
        }
        for(int i=0; i<flights.size(); i++){
            airportFlightNodes.get(airports.indexOf(flights.get(i).getDepartureCode())).addDestination(airportFlightNodes.get(airports.indexOf(flights.get(i).getDestination())), flights.get(i));
        }

        DijkstraGraph flightsGraph = new DijkstraGraph();
        for(int i=0; i<airportFlightNodes.size(); i++){
           flightsGraph.addNode(airportFlightNodes.get(i));
        }

        flightsGraph = calculateShortestPathFromSource(flightsGraph, airportFlightNodes.get(airports.indexOf(departureLocation)), startingTime);

        DijkstraNode destinationNode = airportFlightNodes.get(airports.indexOf(arrivalLocation));
        DijkstraNode currentNode = new DijkstraNode("blank");
        Set<DijkstraNode> processedNodes = flightsGraph.getNodes();
        Iterator<DijkstraNode> iterator = processedNodes.iterator();
        List<Flight> shortestPath = new LinkedList<>();
        while(iterator.hasNext()){
            currentNode = iterator.next();
            if(currentNode.getName().equals(destinationNode.getName())){
                shortestPath = currentNode.getShortestPathFlights();
            }
        }

        flightPlan.setFlights(shortestPath);
        if(flightPlan.getFlights().size()==0){
            return null;
        }
        else {
            return flightPlan;
        }
    }

    private static DijkstraGraph calculateShortestPathFromSource(DijkstraGraph graph, DijkstraNode source, Timestamp startingTime) {
        source.setDistance(0);

        Set<DijkstraNode> settledNodes = new HashSet<>();
        Set<DijkstraNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            DijkstraNode currentNode = getLowestDistanceNode(unsettledNodes, startingTime);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< DijkstraNode, List<Flight>> adjacencyPair: currentNode.getAdjacentNodesFlights().entrySet())

            {
                DijkstraNode adjacentNode = adjacencyPair.getKey();
                long edgeWeight = currentNode.getShortestDurationToNode(adjacentNode, startingTime);
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static DijkstraNode getLowestDistanceNode(Set < DijkstraNode > unsettledNodes, Timestamp startingTime) {
        DijkstraNode lowestDistanceNode = null;
        long lowestDistance = Long.MAX_VALUE;
        for (DijkstraNode node: unsettledNodes) {
            node.setShortestDurations(startingTime);
            long nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(DijkstraNode evaluationNode, long edgeWeight, DijkstraNode sourceNode) {
        long sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<DijkstraNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            LinkedList<Flight> shortestPathFlights = new LinkedList<>(sourceNode.getShortestPathFlights());
            shortestPath.add(sourceNode);
            shortestPathFlights.add(sourceNode.getAdjacentNodesFlightShortest().get(evaluationNode));
            evaluationNode.setShortestPath(shortestPath);
            evaluationNode.setShortestPathFlights(shortestPathFlights);
        }
    }



    private void setAirports(){
        airports.add("ADL");
        airports.add("AMS");
        airports.add("ATL");
        airports.add("BKK");
        airports.add("BNE");
        airports.add("CBR");
        airports.add("CDG");
        airports.add("CNS");
        airports.add("DOH");
        airports.add("DRW");
        airports.add("DXB");
        airports.add("FCO");
        airports.add("GIG");
        airports.add("HBA");
        airports.add("HEL");
        airports.add("HKG");
        airports.add("HNL");
        airports.add("JFK");
        airports.add("JNB");
        airports.add("KUL");
        airports.add("LAX");
        airports.add("LGA");
        airports.add("LGW");
        airports.add("LHR");
        airports.add("MAD");
        airports.add("MEL");
        airports.add("MIA");
        airports.add("MUC");
        airports.add("NRT");
        airports.add("OOL");
        airports.add("ORD");
        airports.add("ORY");
        airports.add("PER");
        airports.add("SFO");
        airports.add("SIN");
        airports.add("SYD");
        airports.add("VIE");
        airports.add("YYZ");
    }

}
