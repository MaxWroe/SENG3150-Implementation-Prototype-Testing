package group3.seng3150.flightLogic;
import group3.seng3150.entities.Flight;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/*
Author: Chris Mather
Description: class that stores nodes for running Dijkstras on
 */

public class DijkstraGraph {

    private HashMap<String,DijkstraNode> nodes;

    public DijkstraGraph(){
        nodes = new HashMap<String,DijkstraNode>();
    }

    public void addNode(String label, DijkstraNode node)
    {
        if(!nodes.containsKey(label)) {
            nodes.put(label, node);
        }
    }

    public HashMap<String,DijkstraNode> getNodes() {
        return nodes;
    }

    public Set<DijkstraNode> getNodesAsSet(){
        Set<DijkstraNode> nodeSet = new HashSet<DijkstraNode>();
        nodeSet.addAll(nodes.values());
        return nodeSet;
    }


    public void setNodes(HashMap<String,DijkstraNode> nodes) {
        this.nodes = nodes;
    }

    public Flight removeEdge(String departureLocation, String arrivalLocation){
        if(nodes.containsKey(departureLocation)){
            Flight removedEdge = nodes.get(departureLocation).removeDestination(arrivalLocation);
            if(removedEdge != null){
                return removedEdge;
            }
        }
        return  null;
    }

    public List<Flight> removeNode(String location){
        LinkedList<Flight> edges = new LinkedList<Flight>();
        if(nodes.containsKey(location)){
            DijkstraNode node = nodes.remove(location);
            edges.addAll(node.getAdjacentNodesFlightsShortestList());
            edges.addAll(removeEdgesToNode(location));
        }

        return edges;
    }

    public List<Flight> removeEdgesToNode(String location){
        List<Flight> edges = new LinkedList<Flight>();
        for(DijkstraNode node : nodes.values()){
            if(node.getAdjacentNodesFlights().containsKey(location)){
                edges.add(node.removeDestination(location));
            }
        }
        return  edges;
    }

    public void addEdge(Flight parsedFlight){
        nodes.get(parsedFlight.getDepartureCode()).addDestination(nodes.get(parsedFlight.getDestination()), parsedFlight);
    }

    public void addEdges(List<Flight> parsedFlights){
        for(Flight edge : parsedFlights){
            addEdge(edge);
        }
    }

}
