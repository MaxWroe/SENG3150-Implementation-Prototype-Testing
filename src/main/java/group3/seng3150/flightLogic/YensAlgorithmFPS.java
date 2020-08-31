package group3.seng3150.flightLogic;

import group3.seng3150.FlightPlan;
import group3.seng3150.entities.Flight;

import java.sql.Timestamp;
import java.util.*;

public class YensAlgorithmFPS {
    private DijkstraAlgorithmFPS dijkstraSearch;

    public YensAlgorithmFPS(){
        dijkstraSearch = new DijkstraAlgorithmFPS();
    }

    public List<FlightPlan> getKShortestPaths(DijkstraGraph graph, String departureLocation, String arrivalLocation, Timestamp startingTime , int k){
        ArrayList<FlightPlan> kShortestPaths = new ArrayList<FlightPlan>();
        PriorityQueue<FlightPlan> candidates = new PriorityQueue<>();
        List<String> currentEdgeDepartures;

        try{

            FlightPlan kthPath = dijkstraSearch.getShortestPathDuration(graph, departureLocation, arrivalLocation, startingTime);
            kShortestPaths.add(kthPath);



            for(int i=1; i<k; i++){

                FlightPlan previousPath = kShortestPaths.get(i-1);

                if(previousPath == null){
                    kShortestPaths.remove(i-1);
                    break;
                }
//                System.out.println(previousPath.toString());

                for(int j = 0; j<previousPath.getNumberOfFlights(); j++) {

                    //the string is the mapping of flights where the string is the departure location of the flights
                    Map<String, List<Flight>> removedEdges = new HashMap<>();

                    String spurNode = previousPath.getFlights().get(j).getDepartureCode();

                    FlightPlan rootPath = previousPath.cloneTo(j);

                    for (FlightPlan p : kShortestPaths) {
                        FlightPlan stub = p.cloneTo(j);

                        if (rootPath.getFlights().equals(stub.getFlights())) {
                            List<Flight> rootEdge = new LinkedList<Flight>();
                            rootEdge.add(p.getFlights().get(j));
                            List<Flight> currentList = graph.removeEdge(rootEdge.get(0).getDepartureCode(), rootEdge.get(0).getDestination());

                            if(currentList!=null){
                                if(removedEdges.containsKey(rootEdge.get(0).getDepartureCode())) {
                                    List<Flight> tempList = new LinkedList<Flight>();
                                    tempList.addAll(currentList);
                                    tempList.addAll(removedEdges.get(rootEdge.get(0).getDepartureCode()));
                                    removedEdges.put(rootEdge.get(0).getDepartureCode(), tempList);
                                    rootEdge.remove(0);
                                }
                                else{
                                    removedEdges.put(rootEdge.get(0).getDepartureCode(), currentList);
                                    rootEdge.remove(0);
                                }
                            }
                            else{
                                removedEdges.put(rootEdge.get(0).getDepartureCode(), rootEdge);
                                rootEdge.remove(0);
                            }
                            //add to removed edges here
//                            currentEdgeDepartures = new LinkedList<>(removedEdges.keySet());
//                            for (String currentKey : currentEdgeDepartures){
//                                for(Flight currentFlight : removedEdges.get(currentKey)){
//                                    System.out.println("this class is a flight at line 293");
//                                }
//                            }


                        }
                    }


                    for (Flight rootEdgePath : rootPath.getFlights()) {
                        String rootNode = rootEdgePath.getDepartureCode();
                        if (!rootNode.equals(spurNode)) {
                            Map<String, List<Flight>> edgesToAndFromNode; // = new HashMap<String, List<Flight>>();
                            edgesToAndFromNode = graph.removeNode(rootNode);
                            List<String> keys = new LinkedList<>(edgesToAndFromNode.keySet());
                            for(String currentKey : keys){

                                if(removedEdges.containsKey(currentKey)){
                                    List<Flight> tempList = new LinkedList<Flight>();
                                    tempList.addAll(edgesToAndFromNode.get(currentKey));
                                    tempList.addAll(removedEdges.get(currentKey));
                                    removedEdges.put(currentKey, tempList);
//                                    for(Flight currentFlight : removedEdges.get(currentKey)){
//                                        System.out.println("this class is a flight at line 316");
//                                    }
                                }
                                else{
                                    removedEdges.put(currentKey, edgesToAndFromNode.get(currentKey));
//                                    for(Flight currentFlight : removedEdges.get(currentKey)){
//                                        System.out.println("this class is a flight at line 322");
//                                    }
                                }



                            }
                        }
                        //add to removed edges here
//                            currentEdgeDepartures = new LinkedList<>(removedEdges.keySet());
//                            for (String currentKey : currentEdgeDepartures){
//                                for(Flight currentFlight : removedEdges.get(currentKey)){
//                                    System.out.println("this class is a flight");
//                                }
//                            }

                    }

                    graph.resetShortestPaths();
                    FlightPlan spurPath = dijkstraSearch.getShortestPathDuration(graph, spurNode, arrivalLocation, startingTime);


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
}
