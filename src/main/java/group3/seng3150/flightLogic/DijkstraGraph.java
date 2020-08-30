package group3.seng3150.flightLogic;
import group3.seng3150.entities.Flight;

import java.util.*;

/*
Author: Chris Mather
Description: class that stores nodes for running Dijkstras on
 */

public class DijkstraGraph {

    private HashMap<String, DijkstraNode> nodes;

    public DijkstraGraph() {
        nodes = new HashMap<String, DijkstraNode>();
    }

    public void addNode(String label, DijkstraNode node) {
        if (!nodes.containsKey(label)) {
            nodes.put(label, node);
        }
    }

    public HashMap<String, DijkstraNode> getNodes() {
        return nodes;
    }

    public Set<DijkstraNode> getNodesAsSet() {
        Set<DijkstraNode> nodeSet = new HashSet<DijkstraNode>();
        nodeSet.addAll(nodes.values());
        return nodeSet;
    }

    public void resetShortestPaths(){
        for(String node : nodes.keySet()){
            nodes.get(node).resetShortestVariables();
        }
    }

    public void setNodes(HashMap<String, DijkstraNode> nodes) {
        this.nodes = nodes;
    }

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


    public void addEdge(Flight parsedFlight, String departureLocation) {
        if(!nodes.containsKey(departureLocation)){
            nodes.put(departureLocation, new DijkstraNode(departureLocation));
        }
        nodes.get(departureLocation).addDestination(nodes.get(parsedFlight.getDestination()), parsedFlight);

    }

    //string is the departure location of a flight
    public void addEdges(Map<String, List<Flight>> parsedEdges) {
//        System.out.println("creating class 2");
        List<String> keys = new LinkedList<>(parsedEdges.keySet());
        for (String currentKey : keys){

            List<Flight> tempFlightList = parsedEdges.get(currentKey);

            for(Flight currentFlight : tempFlightList){
                addEdge(currentFlight, currentKey);
            }

        }

    }

}
