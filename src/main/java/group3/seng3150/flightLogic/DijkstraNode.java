package group3.seng3150.flightLogic;

import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.*;

/*
Author: Chris Mather
Description: class that stores a node and information about its directed edges as flights to other nodes
and has actions to return and set information for htose edges
 */

public class DijkstraNode {
    private String name;
    private long distance = Long.MAX_VALUE;
    //the hash map links destinations to the flights coming from this node to the destination
    private Map<DijkstraNode, List<Flight>> adjacentNodesFlights = new HashMap<>();
    private List<Flight> shortestPathFlights = new LinkedList<>();
    private List<DijkstraNode> shortestPath = new LinkedList<>();
//    private Map<DijkstraNode, Flight> adjacentNodesFlightShortest = new HashMap<>();

    public DijkstraNode(String Name){
        this.name = Name;
    }

    public void resetShortestVariables(){
//        adjacentNodesFlightShortest = new HashMap<>();
        shortestPathFlights = new LinkedList<>();
    }

    //adds sent in flight to connect to sent in node
    public void addDestination(DijkstraNode destination, Flight flight){
        if(!adjacentNodesFlights.containsKey(destination)){
            List<Flight> tempList = new LinkedList<>();
            tempList.add(flight);
            adjacentNodesFlights.put(destination, tempList);
        }
        else {
            List<Flight> tempList = adjacentNodesFlights.remove(destination);
            tempList.add(flight);
            adjacentNodesFlights.put(destination, tempList);
        }
    }

    public List<Flight> removeEdge(String destination){
        List<Flight> removedEdge = new LinkedList<Flight>();
        if(adjacentNodesFlights.containsKey(destination)){
            removedEdge = adjacentNodesFlights.remove(destination);
//            adjacentNodesFlightShortest.remove(destination);
        }
        return removedEdge;
    }

    //sets shortest durations of this node to all other adjacent nodes
//    public void setShortestDurations(Timestamp startingTime){
//        if(shortestPathFlights.size()>0){
//            for (Map.Entry<DijkstraNode, List<Flight>> adjacencyPair : adjacentNodesFlights.entrySet()) {
//                shortestDuration(adjacencyPair.getKey(), shortestPathFlights.getLast(), startingTime);
//            }
//        }
//        else{
//            for (Map.Entry<DijkstraNode, List<Flight>> adjacencyPair : adjacentNodesFlights.entrySet()) {
//                shortestDuration(adjacencyPair.getKey(), null, startingTime);
//            }
//        }
//    }

    //sets shortest of this node to the source node being run on the graph
//    private void shortestDuration(DijkstraNode destination, Flight previousFlight, Timestamp parsedStartingTime){
//        List<Flight> tempList = adjacentNodesFlights.get(destination);
//        long out = Long.MAX_VALUE;
//        boolean flightExists = false;
//        int counter = 0;
//        Timestamp startingTime = parsedStartingTime;
////        System.out.println("starting time Sent In: " + startingTime.toString());
//        if(previousFlight != null) {
//            startingTime = previousFlight.getArrivalDate();
//        }
//        for(int i=0; i<tempList.size(); i++){
//            if(tempList.get(i).getDepartureDate().after(startingTime)){
////                System.out.println("add flight to short paths after time: " + tempList.get(i).getDepartureDate().toString() + " this is after: " + startingTime.toString());
//                if(tempList.get(i).getArrivalDate().getTime() < out){
////                    System.out.println("add Flight to flight shortest: " + tempList.get(i).toString());
//                    counter = i;
//                    flightExists = true;
//                }
//            }
//        }
//        if(flightExists==true) {
//            adjacentNodesFlightShortest.put(destination, tempList.get(counter));
//        }
//
//    }

    //returns shortest duration of this node to sent in node
    public long getShortestDurationToNode(DijkstraNode destination, Timestamp startingTime){
        long currentWeight = Long.MAX_VALUE;
        if(adjacentNodesFlights.get(destination) != null) {
            for (Flight currentFlight : adjacentNodesFlights.get(destination)) {
                if (currentFlight.getDepartureDate().after(startingTime) && currentFlight.getArrivalDate().getTime() - startingTime.getTime() < currentWeight) {
                    currentWeight = (currentFlight.getArrivalDate().getTime() - startingTime.getTime());
                }

            }
        }
        return currentWeight;
    }

    public Flight getEarliestFlightToNode(DijkstraNode destination, Timestamp parsedTime){
        Flight currentFlight = adjacentNodesFlights.get(destination).get(adjacentNodesFlights.get(destination).size()-1);

        for(int i=0; i<adjacentNodesFlights.get(destination).size(); i++){
            if(adjacentNodesFlights.get(destination).get(i).getDepartureDate().after(parsedTime) && adjacentNodesFlights.get(destination).get(i).getDepartureDate().before(currentFlight.getDepartureDate())){
                currentFlight = adjacentNodesFlights.get(destination).get(i);
            }
        }
        return currentFlight;

    }

    public long getFlightDuration(Flight parsedFlight){

        long duration = parsedFlight.getDuration();

        if(parsedFlight.getDurationSecondLeg() != null){
            duration += parsedFlight.getDurationSecondLeg();
        }

        return duration;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<DijkstraNode, List<Flight>> getAdjacentNodesFlights() {
        return adjacentNodesFlights;
    }

    public void setAdjacentNodesFlights(Map<DijkstraNode, List<Flight>> adjacentNodesFlights) {
        this.adjacentNodesFlights = adjacentNodesFlights;
    }

    public List<DijkstraNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<DijkstraNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Flight> getShortestPathFlights() {
        return shortestPathFlights;
    }

    public void setShortestPathFlights(List<Flight> shortestPathFlights) {
        this.shortestPathFlights = shortestPathFlights;
    }

//    public Map<DijkstraNode, Flight> getAdjacentNodesFlightShortest() {
//        return adjacentNodesFlightShortest;
//    }

    public List<Flight> getAdjacentNodesFlightsShortestList(){
        List<Flight> outFlights = new LinkedList<Flight>();
//        outFlights.addAll(adjacentNodesFlightShortest.values());
        return outFlights;
    }

//    public void setAdjacentNodesFlightShortest(Map<DijkstraNode, Flight> adjacentNodesFlightShortest) {
//        this.adjacentNodesFlightShortest = adjacentNodesFlightShortest;
//    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public List<Flight> getAllFlightsFromNode(){
        List<Flight> flights = new LinkedList<>();
        for(List<Flight> adjacentFlightsList : adjacentNodesFlights.values()){
            for (Flight currentFlight : adjacentFlightsList){
                flights.add(currentFlight);
            }
        }
        return flights;
    }

}
