import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Created by tech4GT on 4/23/18.
 */
public class routing {
    public static void main(String[] args) {

        ArrayList<Graph> routingTables = new ArrayList<>();
        Graph graph = new Graph();
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A"));
        nodes.add(new Node("B"));
        nodes.add(new Node("C"));
        nodes.add(new Node("D"));
        nodes.add(new Node("E"));
        nodes.add(new Node("F"));

        for (Node node : nodes) {
            ArrayList<Node> localNodes = new ArrayList<>();

            Node nodeA = new Node("A");
            localNodes.add(nodeA);
            Node nodeB = new Node("B");
            localNodes.add(nodeB);
            Node nodeC = new Node("C");
            localNodes.add(nodeC);
            Node nodeD = new Node("D");
            localNodes.add(nodeD);
            Node nodeE = new Node("E");
            localNodes.add(nodeE);
            Node nodeF = new Node("F");
            localNodes.add(nodeF);

            nodeA.addDestination(nodeB, 10);
            nodeA.addDestination(nodeC, 15);

            nodeB.addDestination(nodeD, 12);
            nodeB.addDestination(nodeF, 15);

            nodeC.addDestination(nodeE, 10);

            nodeD.addDestination(nodeE, 2);
            nodeD.addDestination(nodeF, 1);

            nodeF.addDestination(nodeE, 5);

            graph = new Graph();

            graph.addNode(nodeA);
            graph.addNode(nodeB);
            graph.addNode(nodeC);
            graph.addNode(nodeD);
            graph.addNode(nodeE);
            graph.addNode(nodeF);

            Node src = new Node("X");
            for(Node n : localNodes){
                if(n.equals(node))
                    src = n;
            }


            routingTables.add(calculateShortestPathFromSource(graph, src));
        }

        for (Graph table : routingTables) {
            System.out.print("Table for " + nodes.toArray()[routingTables.indexOf(table)] + "  : {   ");
            System.out.println(table + " }");
        }
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void CalculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
