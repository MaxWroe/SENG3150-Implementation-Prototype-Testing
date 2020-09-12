package group3.seng3150.flightLogic;

import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.*;

/*
Author: Chris Mather
Description: class that stores a node and information about its directed edges as flights to other nodes
and has actions to return and set information for those edges
*/

public class DijkstraNode {
    private String name;
    private long distance;
    //the hash map links destinations to the flights coming from this node to the destination
    private Map<DijkstraNode, List<Flight>> adjacentNodesFlights;
    private List<Flight> shortestPathFlights;
    private List<DijkstraNode> shortestPath;

    public DijkstraNode(String Name){
        this.name = Name;
        distance = Long.MAX_VALUE;
        adjacentNodesFlights = new HashMap<>();
        shortestPathFlights = new LinkedList<>();
        shortestPath = new LinkedList<>();
    }

    public void resetShortestVariables(){
        shortestPathFlights = new LinkedList<>();
        shortestPath = new LinkedList<>();
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
        }
        return removedEdge;
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

    public List<Flight> getAdjacentNodesFlightsShortestList(){
        List<Flight> outFlights = new LinkedList<Flight>();
        return outFlights;
    }

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
