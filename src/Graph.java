import java.util.HashSet;
import java.util.Set;

/**
 * Created by tech4GT on 4/23/18.
 */
public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString(){
        String rv = "";
        for(Node node : nodes){
            rv+=node.toString();
        }
        return rv;
    }
}
