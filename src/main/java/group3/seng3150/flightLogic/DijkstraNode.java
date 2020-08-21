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
    private long distance = Integer.MAX_VALUE;
    private Map<DijkstraNode, List<Flight>> adjacentNodesFlights = new HashMap<>();
    private LinkedList<Flight> shortestPathFlights = new LinkedList<>();
    private LinkedList<DijkstraNode> shortestPath = new LinkedList<>();
    private Map<DijkstraNode, Flight> adjacentNodesFlightShortest = new HashMap<>();

    public DijkstraNode(String Name){
        this.name = Name;
    }

    //adds sent in flight to connect to sent in node
    public void addDestination(DijkstraNode destination, Flight flight){
        if(!adjacentNodesFlights.containsKey(destination)){
            ArrayList<Flight> tempList = new ArrayList<>();
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
            adjacentNodesFlightShortest.remove(destination);
        }
        return removedEdge;
    }

    //sets shortest durations of this node to all other adjacent nodes
    public void setShortestDurations(Timestamp startingTime){
        if(shortestPathFlights.size()>0){
            for (Map.Entry<DijkstraNode, List<Flight>> adjacencyPair : adjacentNodesFlights.entrySet()) {
                shortestDuration(adjacencyPair.getKey(), shortestPathFlights.getLast(), startingTime);
            }
        }
        else{
            for (Map.Entry<DijkstraNode, List<Flight>> adjacencyPair : adjacentNodesFlights.entrySet()) {
                shortestDuration(adjacencyPair.getKey(), null, startingTime);
            }
        }
    }

    //sets shortest of this node to the source node being run on the graph
    private void shortestDuration(DijkstraNode destination, Flight previousFlight, Timestamp parsedStartingTime){
        List<Flight> tempList = adjacentNodesFlights.get(destination);
        long out = Long.MAX_VALUE;
        int counter = 0;
        Timestamp startingTime = parsedStartingTime;
        if(previousFlight != null) {
            startingTime = previousFlight.getArrivalDate();
        }
        for(int i=0; i<tempList.size(); i++){
            if(tempList.get(i).getDepartureDate().after(startingTime)){
                if(tempList.get(i).getArrivalDate().getTime()<out){
                    counter = i;
                }
            }
        }
        adjacentNodesFlightShortest.put(destination, tempList.get(counter));
    }

    //returns shortest duration of this node to sent in node
    public long getShortestDurationToNode(DijkstraNode destination, Timestamp startingTime){
        if (adjacentNodesFlightShortest.containsKey(destination)) {
            if (shortestPathFlights.size()>0) {
                return adjacentNodesFlightShortest.get(destination).getDepartureDate().getTime() - shortestPathFlights.getLast().getArrivalDate().getTime();
            }
            else {
                return adjacentNodesFlightShortest.get(destination).getDepartureDate().getTime() - startingTime.getTime();
            }
        }
        else {
            return Long.MAX_VALUE;
        }
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

    public LinkedList<DijkstraNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<DijkstraNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Flight> getShortestPathFlights() {
        return shortestPathFlights;
    }

    public void setShortestPathFlights(LinkedList<Flight> shortestPathFlights) {
        this.shortestPathFlights = shortestPathFlights;
    }

    public Map<DijkstraNode, Flight> getAdjacentNodesFlightShortest() {
        return adjacentNodesFlightShortest;
    }

    public List<Flight> getAdjacentNodesFlightsShortestList(){
        List<Flight> outFlights = new LinkedList<Flight>();
        outFlights.addAll(adjacentNodesFlightShortest.values());
        return outFlights;
    }

    public void setAdjacentNodesFlightShortest(Map<DijkstraNode, Flight> adjacentNodesFlightShortest) {
        this.adjacentNodesFlightShortest = adjacentNodesFlightShortest;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

}
