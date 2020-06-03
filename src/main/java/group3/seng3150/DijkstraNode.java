package group3.seng3150;

import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.*;

public class DijkstraNode {
    private String name;
//    private List<DijkstraNode> shortestPath = new LinkedList<>();
    private long distance = Integer.MAX_VALUE;
//    private Map<DijkstraNode, Integer> adjacentNodes = new HashMap<>();
    private Map<DijkstraNode, List<Flight>> adjacentNodesFlights = new HashMap<>();
    private LinkedList<Flight> shortestPathFlights = new LinkedList<>();
    private LinkedList<DijkstraNode> shortestPath = new LinkedList<>();
    private Map<DijkstraNode, Flight> adjacentNodesFlightShortest = new HashMap<>();

    public DijkstraNode(String Name){
        this.name = Name;
    }

    public void addDestination(DijkstraNode destination, Flight flight){
//        adjacentNodes.put(destination,distance);
        if(!adjacentNodesFlights.containsKey(destination)){
            ArrayList<Flight> tempList = new ArrayList<>();
            adjacentNodesFlights.put(destination, tempList);
        }
        else {
            List<Flight> tempList = adjacentNodesFlights.remove(destination);
            tempList.add(flight);
            adjacentNodesFlights.put(destination, tempList);
        }
    }

    public void setShortestDurations(){
//        if(shortestPathFlights.size()>0) {
//            Flight previousFlight = shortestPathFlights.get(shortestPathFlights.size() - 1);
//        }
        if(shortestPathFlights.size()>0){
            for (Map.Entry<DijkstraNode, List<Flight>> adjacencyPair : adjacentNodesFlights.entrySet()) {
                shortestDuration(adjacencyPair.getKey(), shortestPathFlights.getLast());
            }
        }
        else{
            for (Map.Entry<DijkstraNode, List<Flight>> adjacencyPair : adjacentNodesFlights.entrySet()) {
                shortestDuration(adjacencyPair.getKey(), null);
            }
        }
    }

    public long shortestDuration(DijkstraNode destination, Flight previousFlight){
        List<Flight> tempList = adjacentNodesFlights.get(destination);
        long out = Long.MAX_VALUE;
        int counter = 0;
        if(previousFlight != null){
        Timestamp startingTime = previousFlight.getArrivalDate();
        long rawStartingTime = previousFlight.getArrivalDate().getTime();
            for(int i=0; i<tempList.size(); i++){
                if(tempList.get(i).getDepartureDate().after(startingTime)){
                    if(tempList.get(i).getArrivalDate().getTime()<out){
                        out = tempList.get(i).getArrivalDate().getTime();
                        counter = i;
                    }
                }
            }
        }
        else{
            for(int i=0; i<tempList.size(); i++){
                if(tempList.get(i).getArrivalDate().getTime()<out){
                    out = tempList.get(i).getArrivalDate().getTime();
                    counter = i;
                }
            }
        }

        adjacentNodesFlightShortest.put(destination, tempList.get(counter));
        return out;

    }

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

//    public List<DijkstraNode> getShortestPath() {
//        return shortestPath;
//    }
//
//    public void setShortestPath(List<DijkstraNode> shortestPath) {
//        this.shortestPath = shortestPath;
//    }

    public Map<DijkstraNode, List<Flight>> getAdjacentNodesFlights() {
        return adjacentNodesFlights;
    }

    public void setAdjacentNodesFlights(Map<DijkstraNode, List<Flight>> adjacentNodesFlights) {
        this.adjacentNodesFlights = adjacentNodesFlights;
    }

//    public List<Flight> getShortestPathFlights() {
//        return shortestPathFlights;
//    }
//
//    public void setShortestPathFlights(List<Flight> shortestPathFlights) {
//        this.shortestPathFlights = shortestPathFlights;
//    }


    public LinkedList<DijkstraNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<DijkstraNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setDistance(long distance) {
        this.distance = distance;
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

    public void setAdjacentNodesFlightShortest(Map<DijkstraNode, Flight> adjacentNodesFlightShortest) {
        this.adjacentNodesFlightShortest = adjacentNodesFlightShortest;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

//    public Map<DijkstraNode, Integer> getAdjacentNodes() {
//        return adjacentNodes;
//    }
//
//    public void setAdjacentNodes(Map<DijkstraNode, Integer> adjacentNodes) {
//        this.adjacentNodes = adjacentNodes;
//    }
}
