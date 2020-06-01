package group3.seng3150;

import java.util.HashSet;
import java.util.Set;

public class DijkstraGraph {

    private Set<DijkstraNode> nodes = new HashSet<>();

    public void addNode(DijkstraNode node){
        nodes.add(node);
    }

    public Set<DijkstraNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<DijkstraNode> nodes) {
        this.nodes = nodes;
    }
}
