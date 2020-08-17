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
    public List<FlightPlan> createFlightPlans(List<Flight> flights, String departureLocation, String destination, String startingTimeString, List<Availability> parsedAvailabilities){
        System.out.println("number of flights sent to flightPlanSearch: " + flights.size());
        Timestamp startingTime = Timestamp.valueOf(startingTimeString);
        List<FlightPlan> flightPlans = new LinkedList<>();
        List<Flight> filteredFlights = filterByAvailabilities(flights, parsedAvailabilities);
        DijkstraGraph graph = buildGraph(filteredFlights);




        if(filteredFlights.size()>0){
//            for(int i=0; i<3; i++) {
//                System.out.println("running iteration of search" + i);
//                startingTime.setTime(startingTime.getTime()+(8*3600000)); //adds two hours onto the starting time
//                flightPlans.add(getShortestPathDuration(graph, departureLocation, destination, startingTime));
                flightPlans.addAll(getKShortestPaths(graph, departureLocation, destination, startingTime, 10));
//            }
        }
        System.out.println("number of flight plans produced by Yenns: " + flightPlans.size());
        //sets availabilities
        if(flightPlans.get(0)!= null) {
            flightPlans = SetFlightPlansAvailabilities(flightPlans, parsedAvailabilities);
            flightPlans = removeDuplicateFlightPlans(flightPlans);
        }
        System.out.println("flight plan search complete");
        return flightPlans;
    }

    //method that sets availabilities of a snet in flight plans
    private List<FlightPlan> SetFlightPlansAvailabilities(List<FlightPlan> flightPlans, List<Availability> availabilities){
        List<FlightPlan> flightPlanList = flightPlans;
        if(availabilities.size()>0) {
            for (int i = 0; i < flightPlanList.size(); i++) {

                flightPlanList.get(i).setAvailabilitiesFiltered(availabilities);
//                System.out.println("Ran loop of setFlightPlanAvailabilities: " + i);
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

    private DijkstraGraph buildGraph(List<Flight> flights){
        ArrayList<DijkstraNode> airportFlightNodes = new ArrayList<>();
        DijkstraGraph flightsGraph = new DijkstraGraph();
        for(int i=0; i<airports.size(); i++){
            airportFlightNodes.add(new DijkstraNode(airports.get(i)));
        }
        for(int i=0; i<flights.size(); i++){
            airportFlightNodes.get(airports.indexOf(flights.get(i).getDepartureCode())).addDestination(airportFlightNodes.get(airports.indexOf(flights.get(i).getDestination())), flights.get(i));
        }

        for(int i=0; i<airportFlightNodes.size(); i++){
            flightsGraph.addNode(airportFlightNodes.get(i).getName(), airportFlightNodes.get(i));
        }
        return flightsGraph;
    }


    //returns a flight plan that matches sent in criteria, if non exist returns an empty flight plan
    private FlightPlan getShortestPathDuration(DijkstraGraph parsedGraph, String departureLocation, String arrivalLocation, Timestamp startingTime){
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

    public List<FlightPlan> getKShortestPaths(DijkstraGraph graph, String departureLocation, String arrivalLocation,Timestamp startingTime , int k){
        ArrayList<FlightPlan> kShortestPaths = new ArrayList<FlightPlan>();
        Queue<FlightPlan> candidates = new LinkedList<FlightPlan>();

        try{

            FlightPlan kthPath = getShortestPathDuration(graph, departureLocation, arrivalLocation, startingTime);
            kShortestPaths.add(kthPath);

            for(int i=1; i<k; i++){

                FlightPlan previousPath = kShortestPaths.get(i-1);

                for(int j = 0; j<previousPath.getNumberOfFlights(); j++) {

                    LinkedList<Flight> removedEdges = new LinkedList<Flight>();

                    String spurNode = previousPath.getFlights().get(j).getDepartureCode();

                    FlightPlan rootPath = previousPath.copyTo(j);

                    for (FlightPlan p : kShortestPaths) {
                        FlightPlan stub = p.copyTo(j);

                        if (rootPath.getFlights().equals(stub.getFlights())) {
                            Flight rootEdge = p.getFlights().get(j);
                            graph.removeEdge(rootEdge.getDepartureCode(), rootEdge.getDestination());
                            removedEdges.add(rootEdge);
                        }
                    }


                    for (Flight rootEdgePath : rootPath.getFlights()) {
                        String rootNode = rootEdgePath.getDepartureCode();
                        if (!rootNode.equals(spurNode)) {
                            removedEdges.addAll(graph.removeNode(rootNode));
                        }
                    }

                    FlightPlan spurPath = getShortestPathDuration(graph, spurNode, arrivalLocation, startingTime);

                    if (spurPath != null) {
                        FlightPlan totalPath = new FlightPlan(rootPath.getFlights());
                        totalPath.addFlights(totalPath);

                        if (!candidates.contains(totalPath)) {
                            candidates.add(totalPath);
                        }
                    }

                    graph.addEdges(removedEdges);

                }

                boolean isNewPath = true;
                while (!isNewPath) {
                    kthPath = candidates.poll();
                    isNewPath = true;
                    if(kthPath != null){
                        for(FlightPlan p : kShortestPaths){
                            if(p.getFlights().equals(kthPath.getFlights())){
                                isNewPath = false;
                                break;
                            }
                        }
                    }
                }
                if (kthPath == null) {
                    break;
                }

                kShortestPaths.add(kthPath);

            }
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return kShortestPaths;
    }

    private List<FlightPlan> removeDuplicateFlightPlans(List<FlightPlan> parsedFlightPlans){
//        System.out.println("running removeDuplicateFlightPlans");
        List<FlightPlan> uniqueFlightPlans = new LinkedList<FlightPlan>();
        boolean existsIn;
        for(int i=0; i<parsedFlightPlans.size(); i++){
            System.out.println("running loop iteration of parsedFlightPlans: " + i);
            existsIn = false;
            for(int j=0; j<uniqueFlightPlans.size(); j++){
                if (parsedFlightPlans.get(i).getFlights().equals(uniqueFlightPlans.get(j).getFlights())){
                    System.out.println("running loop iteration of uniqueFlightPlans: " + j);
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
