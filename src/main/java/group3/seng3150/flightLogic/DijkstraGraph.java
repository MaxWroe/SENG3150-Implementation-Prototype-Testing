package group3.seng3150.flightLogic;
import group3.seng3150.entities.Flight;

import java.util.*;

/*
Author: Chris Mather
Description: class that holds a graph where a node is a representation of an airport and an edge a collection of flights between two airports, with methods to construct and deconstruct the graph
*/

public class DijkstraGraph {

    private HashMap<String, DijkstraNode> nodes;

    public DijkstraGraph() {
        nodes = new HashMap<String, DijkstraNode>();
    }

    //if sent in label does not have a related node in nodes then sent in node is added to map of nodes
    public void addNode(String label, DijkstraNode node) {
        if (!nodes.containsKey(label)) {
            nodes.put(label, node);
        }
    }

    //runs reset Shortest Variables  method of each node
    public void resetShortestPaths(){
        for(String node : nodes.keySet()){
            nodes.get(node).resetShortestVariables();
        }
    }



    //removes and returns the edge that goes from the departure location to the arrival location
    public List<Flight> removeEdge(String departureLocation, String arrivalLocation) {
        if (nodes.containsKey(departureLocation)) {
            List<Flight> removedEdge = new LinkedList<Flight>();
            removedEdge.addAll(nodes.get(departureLocation).removeEdge(arrivalLocation));
            if (removedEdge.size() > 0) {
                return removedEdge;
            }
        }
        return null;
    }

    //the mapping maps the edge to the name of the node that the edge departs from
    //removes the node that represents the sent in location and removes and returns all the edges that came from and go to that node
    public Map<String, List<Flight>> removeNode(String locationToRemove) {
        Map<String, List<Flight>> removedEdges = new HashMap<>();
        if (nodes.containsKey(locationToRemove)) {
            DijkstraNode node = nodes.remove(locationToRemove);
            removedEdges.put(locationToRemove, node.getAllFlightsFromNode());

            for(DijkstraNode currentNode : nodes.values()){
                removedEdges.put(currentNode.getName(), currentNode.removeEdge(locationToRemove));
            }
        }

        return removedEdges;
    }

    //adds edge to an existing node that represents the departure location of the flight and if it does not exist creates a new node for the edge
    public void addEdge(Flight parsedFlight, String departureLocation) {
        if(!nodes.containsKey(departureLocation)){
            nodes.put(departureLocation, new DijkstraNode(departureLocation));
        }
        nodes.get(departureLocation).addDestination(nodes.get(parsedFlight.getDestination()), parsedFlight);

    }

    //string is the departure location of a flight
    public void addEdges(Map<String, List<Flight>> parsedEdges) {
        List<String> keys = new LinkedList<>(parsedEdges.keySet());
        for (String currentKey : keys){

            List<Flight> tempFlightList = parsedEdges.get(currentKey);

            for(Flight currentFlight : tempFlightList){
                addEdge(currentFlight, currentKey);
            }

        }

    }

    public HashMap<String, DijkstraNode> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<String, DijkstraNode> nodes) {
        this.nodes = nodes;
    }

}
