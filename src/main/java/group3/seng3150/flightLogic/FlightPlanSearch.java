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
    public List<FlightPlan> createFlightPlans(List<Flight> flights, String departureLocation, String destination, String startingTimeString, String endingTimeString, List<Availability> parsedAvailabilities){

        Timestamp startingTime = Timestamp.valueOf(startingTimeString);
        Timestamp endingTime = Timestamp.valueOf(endingTimeString);
        List<FlightPlan> flightPlans = new LinkedList<>();
        Timestamp inputTime = startingTime;
        int numberOfCycles = 7;

//        for(int j=0; j<flights.size(); j++){
//            System.out.println("flight: " + flights.get(j).toString());
//        }

        List<Flight> filteredFlights = filterByAvailabilities(flights, parsedAvailabilities);

//        DijkstraGraph graph = buildGraph(filteredFlights);
//        System.out.println(getShortestPathDuration(graph, departureLocation, destination, inputTime).toString());

        if(filteredFlights.size()>0){
            for(int i=0; i<numberOfCycles; i++) {
                System.out.println("running iteration of search" + i + ", starting time: " + inputTime.toString());
                System.out.println("number of flights in the current iteration: " + filteredFlights.size());

                DijkstraGraph graph = buildGraph(filteredFlights);
                flightPlans.addAll(getKShortestPaths(graph, departureLocation, destination, inputTime, 10));
                System.out.println("number of flights after yenns in current iteration: " + filteredFlights.size());

//                for(int j=0; j<filteredFlights.size(); j++){
//                    System.out.println("flight: " + filteredFlights.get(j).toString());
//                }

                inputTime = new Timestamp(startingTime.getTime()+((endingTime.getTime() - startingTime.getTime())/numberOfCycles)*(i+1));
                System.out.println("date for removal: " + inputTime);
                for(int j=0; j<filteredFlights.size(); j++){
                    if(filteredFlights.get(j).getDepartureDate().before(inputTime)){
//                        System.out.println("removing flight: " + filteredFlights.get(j).toString());
                        filteredFlights.remove(j);
                        j--;
                    }
                }

            }
        }
        System.out.println("number of flight plans produced by Yenns: " + flightPlans.size());

        for(int j=0; j<flightPlans.size(); j++){
            System.out.println("flight: " + flightPlans.get(j).toString());
        }

        //sets availabilities
        if(flightPlans.size()>0) {
            flightPlans = removeDuplicateFlightPlans(flightPlans);
            flightPlans = SetFlightPlansAvailabilities(flightPlans, parsedAvailabilities);

        }
        System.out.println("flight plan search complete");
        return flightPlans;
    }

    //method that sets availabilities of a sent in flight plans
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

    //removes flights from the list that do not have an availability in the sent in list
    private List<Flight> filterByAvailabilities(List<Flight> flights, List<Availability> availabilities){
        List<Flight> filteredFlights = new LinkedList<>();
        boolean contains = false;
        for(int i=0; i<flights.size(); i++){
            contains = false;
            for (int j=0; j<availabilities.size(); j++)
            {
                if(flights.get(i).getFlightNumber().equals(availabilities.get(j).getFlightNumber()) && flights.get(i).getDepartureDate().equals(availabilities.get(j).getDepartureDate()) && flights.get(i).getAirlineCode().equals(availabilities.get(j).getAirlineCode())){
                    contains = true;
                    break;
                }
            }
            if(contains = true){
//                System.out.println("adding flight: " + flights.get(i).toString());
                filteredFlights.add(flights.remove(i));
                i--;
            }
        }
        return filteredFlights;
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
                if(adjacentNode.getShortestPathFlights().size()>0){
                    currentTime = adjacentNode.getShortestPathFlights().getLast().getArrivalDate();
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
            LinkedList<DijkstraNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);

            LinkedList<Flight> shortestPathFlights = new LinkedList<>(sourceNode.getShortestPathFlights());
            shortestPathFlights.add(currentEdge);
//            if(sourceNode.getAdjacentNodesFlightShortest().get(evaluationNode) != null && sourceNode.getAdjacentNodesFlightShortest().get(evaluationNode).getDepartureDate().after(startingTime)) {
//                shortestPathFlights.add(sourceNode.getAdjacentNodesFlightShortest().get(evaluationNode));
//            }
            evaluationNode.setShortestPathFlights(shortestPathFlights);
        }
    }

    public List<FlightPlan> getKShortestPaths(DijkstraGraph graph, String departureLocation, String arrivalLocation,Timestamp startingTime , int k){
        ArrayList<FlightPlan> kShortestPaths = new ArrayList<FlightPlan>();
        PriorityQueue<FlightPlan> candidates = new PriorityQueue<>();

        try{

            FlightPlan kthPath = getShortestPathDuration(graph, departureLocation, arrivalLocation, startingTime);
            kShortestPaths.add(kthPath);



            for(int i=1; i<k; i++){

                FlightPlan previousPath = kShortestPaths.get(i-1);

                if(previousPath == null){
                    kShortestPaths.remove(i-1);
                    break;
                }
                System.out.println(previousPath.toString());

                for(int j = 0; j<previousPath.getNumberOfFlights(); j++) {

                    Map<String, List<Flight>> removedEdges = new HashMap<>();

                    String spurNode = previousPath.getFlights().get(j).getDepartureCode();

                    FlightPlan rootPath = previousPath.cloneTo(j);

                    for (FlightPlan p : kShortestPaths) {
                        FlightPlan stub = p.cloneTo(j);

                        if (rootPath.getFlights().equals(stub.getFlights())) {
                            List<Flight> rootEdge = new LinkedList<Flight>();
                            rootEdge.add(p.getFlights().get(j));
                            List<Flight> currentList = graph.removeEdge(rootEdge.get(0).getDepartureCode(), rootEdge.get(0).getDestination());

                            if(currentList!=null && removedEdges.containsKey(rootEdge.get(0))) {
                                rootEdge.remove(0);
                                List<Flight> tempList = new LinkedList<Flight>();
                                tempList.addAll(currentList);
                                tempList.addAll(removedEdges.get(rootEdge.get(0).getDepartureCode()));
                                removedEdges.put(rootEdge.get(0).getDepartureCode(), tempList);
                            }
                            else{
                                removedEdges.put(rootEdge.get(0).getDepartureCode(), rootEdge);
                            }
                        }
                    }


                    for (Flight rootEdgePath : rootPath.getFlights()) {
                        String rootNode = rootEdgePath.getDepartureCode();
                        if (!rootNode.equals(spurNode)) {
                            Map<String, List<Flight>> edgesToAndFromNode = new HashMap<>();
                            edgesToAndFromNode.putAll(graph.removeNode(rootNode));
                            List<String> keys = new LinkedList<>(edgesToAndFromNode.keySet());
                            for(String currentKey : keys){

                                if(removedEdges.containsKey(currentKey)){
                                    List<Flight> tempList = new LinkedList<Flight>();
                                    tempList.addAll(edgesToAndFromNode.get(currentKey));
                                    tempList.addAll(removedEdges.get(edgesToAndFromNode.get(currentKey).get(0).getDepartureCode()));
                                    removedEdges.put(edgesToAndFromNode.get(currentKey).get(0).getDepartureCode(), tempList);
                                }
                                else{
                                    removedEdges.put(currentKey, edgesToAndFromNode.get(currentKey));
                                }
                            }

                        }
                    }

                    graph.resetShortestPaths();
                    FlightPlan spurPath = getShortestPathDuration(graph, spurNode, arrivalLocation, startingTime);


                    if (spurPath != null) {
                        FlightPlan totalPath = new FlightPlan(rootPath.getFlights());
                        totalPath.addFlights(spurPath);
                        System.out.println("Candidate option: " + totalPath.toString());
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
                            System.out.println("two paths being compared: " + p.toString() + ", " + kthPath.toString());
                            if(p.getFlights().equals(kthPath.getFlights())){
//                                System.out.println("not a new path");
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
        LinkedList<FlightPlan> uniqueFlightPlans = new LinkedList<FlightPlan>();
        boolean existsIn;
        for(int i=0; i<parsedFlightPlans.size(); i++){
//            System.out.println("running loop iteration of parsedFlightPlans: " + i);
            existsIn = false;
            for(int j=0; j<uniqueFlightPlans.size(); j++){
//                System.out.println("flight plan 1: " + parsedFlightPlans.get(i).toString() + "flight plan 2: " + uniqueFlightPlans.get(j).toString());
                if (parsedFlightPlans.get(i).getFlights().equals(uniqueFlightPlans.get(j).getFlights())){
//                    System.out.println("running loop iteration of uniqueFlightPlans: " + j);

                    existsIn = true;
                }
            }
            if(existsIn==false){
                uniqueFlightPlans.add(parsedFlightPlans.get(i));
                System.out.println("new flight plan added to list: " + uniqueFlightPlans.getLast().toString());
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
