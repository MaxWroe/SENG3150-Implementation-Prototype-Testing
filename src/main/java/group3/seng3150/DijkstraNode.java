package group3.seng3150;

import java.util.*;

public class DijkstraNode {
    private String name;
    private List<DijkstraNode> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<DijkstraNode, Integer> adjacentNodes = new HashMap<>();

    public DijkstraNode(String Name){
        this.name = name;
    }

    public void addDestination(DijkstraNode destination, int distance){
        adjacentNodes.put(destination,distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DijkstraNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<DijkstraNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<DijkstraNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<DijkstraNode, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
