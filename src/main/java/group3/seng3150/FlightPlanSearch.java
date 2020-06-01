package group3.seng3150;

import group3.seng3150.entities.Flight;

import java.util.*;
//search options duration, price


public class FlightPlanSearch {
    private ArrayList<String> airports;

    public FlightPlanSearch(){
        airports = new ArrayList<>();
        setAirports();
    }

    public List<FlightPlan> createFlightPlans(List<Flight> flights){
        List<FlightPlan> flightPlans = new LinkedList<>();

        return flightPlans;
    }
    public FlightPlan getShortestPathDuration(List<Flight> flights, String departureLocation){
        FlightPlan flightPlan = new FlightPlan();
        ArrayList<DijkstraNode> airportFlightNodes = new ArrayList<>();
        for(int i=0; i<airports.size(); i++){
            airportFlightNodes.add(new DijkstraNode(airports.get(i)));
        }
        for(int i=0; i<flights.size(); i++){
            airportFlightNodes.get(airports.indexOf(flights.get(i).getDepartureCode())) .addDestination(airportFlightNodes.get(airports.indexOf(flights.get(i).getDestination())) ,flights.get(i).getDuration());
        }

        DijkstraGraph flightsGraph = new DijkstraGraph();
        for(int i=0; i<airports.size(); i++){
           flightsGraph.addNode(airportFlightNodes.get(i));
        }

        flightsGraph = calculateShortestPathFromSource(flightsGraph, airportFlightNodes.get(airports.indexOf(departureLocation)));


        return flightPlan;
    }

    public static DijkstraGraph calculateShortestPathFromSource(DijkstraGraph graph, DijkstraNode source) {
        source.setDistance(0);

        Set<DijkstraNode> settledNodes = new HashSet<>();
        Set<DijkstraNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            DijkstraNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< DijkstraNode, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet())
            {
                DijkstraNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static DijkstraNode getLowestDistanceNode(Set < DijkstraNode > unsettledNodes) {
        DijkstraNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (DijkstraNode node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(DijkstraNode evaluationNode,
                                                 Integer edgeWeigh, DijkstraNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<DijkstraNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
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
