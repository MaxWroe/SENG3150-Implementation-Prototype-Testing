package group3.seng3150.flightLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.Availability;
import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.*;

/*
Author: Chris Mather
Description: this class processes a list of flights and returns information about it
 */

public class FlightPlanSearch {
    private ArrayList<String> airports;

    public FlightPlanSearch(){
        airports = new ArrayList<>();
        setAirports();
    }

    public FlightPlanSearch(LinkedList<String> parsedAirports){
        airports = new ArrayList<>();
        setAirports(parsedAirports);
    }

    //finds and returns a list of flight plans from sent in flights that match the criteria
    public List<FlightPlan> createFlightPlans(List<Flight> flights, String departureLocation, String destination, boolean stopOverNeeded, String startingTimeString, List<Availability> parsedAvailabilities){
        Timestamp startingTime = Timestamp.valueOf(startingTimeString);
        List<FlightPlan> flightPlans = new LinkedList<>();
        List<Flight> filteredFlights = filterByAvailabilities(flights, parsedAvailabilities);
        //if a stop over is needed and sent in flights has flights will run Dijkstra's algorithm on the flights and returns the found flight plans
        if(stopOverNeeded && filteredFlights.size()>0){
            for(int i=0; i<12; i++) {
                flightPlans.add(getShortestPathDuration(filteredFlights, departureLocation, destination, startingTime));
                startingTime.setTime(startingTime.getTime()+(2*3600000)); //adds two hours onto the starting time
            }
        }
        else if(filteredFlights.size()>10){

        }
        //simply compiles sent in list of flights into flight plans
        else{
            for(int i=0; i<filteredFlights.size(); i++){
                flightPlans.add(new FlightPlan());
                flightPlans.get(i).add(filteredFlights.get(i));
            }
        }

        //sets availabilities
        if(flightPlans.get(0)!= null) {
            flightPlans = SetFlightPlansAvailabilities(flightPlans, parsedAvailabilities);
        }
        flightPlans = removeDuplicateFlightPlans(flightPlans);
        return flightPlans;
    }

    //method that sets availabilities of a snet in flight plans
    private List<FlightPlan> SetFlightPlansAvailabilities(List<FlightPlan> flightPlans, List<Availability> availabilities){
        List<FlightPlan> flightPlanList = flightPlans;
        if(availabilities.size()>0) {
            for (int i = 0; i < flightPlanList.size(); i++) {
                flightPlanList.get(i).setAvailabilitiesFiltered(availabilities);
            }
        }
        return flightPlanList;
    }

    //removes flights from the lsit that do not have an availability in the sent in list
    private List<Flight> filterByAvailabilities(List<Flight> flights, List<Availability> availabilities){
        List<Flight> filteredFlights = new LinkedList<>();
        boolean contains = false;
        for(int i=0; i<flights.size(); i++){
            contains = false;
            for (int j=0; j<availabilities.size(); j++)
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

    //returns a flight plan that matches sent in criteria, if non exist returns an empty flight plan
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

    //runs Dijsktras in a sent in graph from the source node sent in
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

    private List<FlightPlan> removeDuplicateFlightPlans(List<FlightPlan> parsedFlightPlans){
        List<FlightPlan> uniqueFlightPlans = new LinkedList<FlightPlan>();
        boolean existsIn;
        for(int i=0; i<parsedFlightPlans.size(); i++){
            existsIn = false;
            for(int j=0; j<uniqueFlightPlans.size(); j++){
                if (parsedFlightPlans.get(i).equals(uniqueFlightPlans.get(j))){
                    existsIn = true;
                }
            }
            if(existsIn==false){
                uniqueFlightPlans.add(parsedFlightPlans.get(i));
            }
        }
        return  uniqueFlightPlans;
    }

    private void setAirports(LinkedList<String> parsedAirports){
        for (int i=0; i<parsedAirports.size(); i++){
         airports.add(parsedAirports.get(i));
        }
    }

    //sets list of airports to this static list
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
