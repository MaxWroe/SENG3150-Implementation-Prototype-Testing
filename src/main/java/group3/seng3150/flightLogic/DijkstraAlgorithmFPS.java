package group3.seng3150.flightLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.*;

public class DijkstraAlgorithmFPS {

    public DijkstraAlgorithmFPS(){}

    public FlightPlan getShortestPathDuration(DijkstraGraph parsedGraph, String departureLocation, String arrivalLocation, Timestamp startingTime){
        FlightPlan flightPlan = new FlightPlan();

        DijkstraGraph flightsGraph = calculateShortestPathFromSource(parsedGraph, parsedGraph.getNodes().get(departureLocation), startingTime);

        DijkstraNode destinationNode = parsedGraph.getNodes().get(arrivalLocation);
        DijkstraNode currentNode = new DijkstraNode("blank");
        Set<DijkstraNode> processedNodes = flightsGraph.getNodesAsSet();
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

    //runs Dijkstra's algorithm in a sent in graph from the source node sent in
    private static DijkstraGraph calculateShortestPathFromSource(DijkstraGraph graph, DijkstraNode source, Timestamp startingTime) {
        source.setDistance(0);

        Set<DijkstraNode> settledNodes = new HashSet<>();
        Set<DijkstraNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            DijkstraNode currentNode = getLowestDistanceNode(unsettledNodes, startingTime);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<DijkstraNode, List<Flight>> adjacencyPair: currentNode.getAdjacentNodesFlights().entrySet())

            {
                DijkstraNode adjacentNode = adjacencyPair.getKey();
                Timestamp currentTime;
                int tempSPFSize = adjacentNode.getShortestPathFlights().size();
                if(tempSPFSize>0){
                    currentTime = adjacentNode.getShortestPathFlights().get(tempSPFSize-1).getArrivalDate();
                }
                else{
                    currentTime = startingTime;
                }
                long edgeWeight = currentNode.getShortestDurationToNode(adjacentNode, currentTime);
                Flight currentEdge = currentNode.getEarliestFlightToNode(adjacentNode, currentTime);
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentEdge, currentNode, startingTime);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static DijkstraNode getLowestDistanceNode(Set<DijkstraNode> unsettledNodes, Timestamp startingTime) {
        DijkstraNode lowestDistanceNode = null;
        long lowestDistance = Long.MAX_VALUE;
        for (DijkstraNode node: unsettledNodes) {
//            node.setShortestDurations(startingTime);
            long nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(DijkstraNode evaluationNode, long edgeWeight, Flight currentEdge,  DijkstraNode sourceNode, Timestamp startingTime) {
        long sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            List<DijkstraNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);

            List<Flight> shortestPathFlights = new LinkedList<>(sourceNode.getShortestPathFlights());
            shortestPathFlights.add(currentEdge);
//            if(sourceNode.getAdjacentNodesFlightShortest().get(evaluationNode) != null && sourceNode.getAdjacentNodesFlightShortest().get(evaluationNode).getDepartureDate().after(startingTime)) {
//                shortestPathFlights.add(sourceNode.getAdjacentNodesFlightShortest().get(evaluationNode));
//            }
            evaluationNode.setShortestPathFlights(shortestPathFlights);
        }
    }

}
