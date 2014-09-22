package CS321.assignments.assignment00;

import java.util.ArrayList;

/**
 * Created by JT on 2014-06-08.
 */
public class TravelingSalesman
{
    public static double bestPathWeight = Double.MAX_VALUE;
    public static String bestPath = "";

    public static void main(String[] args) throws Exception
    {
        InputParser parser = new InputParser();
        Graph graph = parser.parse(args[0]);

        int size = graph.getNodes().size();
        boolean[] visited = new boolean[size];

        long startTime = System.nanoTime();
        for (Node node : graph.getNodes())
        {
            visited[node.getNodeIndex()] = true;
            visitNode(node, node, new ArrayList<Edge>(), size, visited);
        }
        long endTime = System.nanoTime();

        System.out.print("The shortest path found was: ");
        System.out.println(bestPath);
        System.out.print("Which has a weight of: ");
        System.out.println(bestPathWeight);
        System.out.print("Execution time in milliseconds took: ");
        System.out.println((endTime - startTime) / 1000000);
    }

    public static void visitNode(Node startNode, Node currentNode, ArrayList<Edge> currentPath, int numberOfNodes, boolean[] visited)
    {
        if (numberOfNodes <= currentPath.size() + 1)
        {
            // End of path add last edge to close route and add to paths queue
            Edge closingEdge = currentNode.getEdgeToNode(startNode);
            currentPath.add(closingEdge);
            Path path = new Path(currentPath);

            if (bestPathWeight > path.getWeight())
            {
                bestPathWeight = path.getWeight();
                bestPath = path.getPath();
            }

            currentPath.remove(closingEdge);
        return;
    }

        for(Edge edge : currentNode.getEdges())
        {
            Node destination = edge.getDestinationNode();
            if (!visited[destination.getNodeIndex()])
            {
                currentPath.add(edge);
                visited[destination.getNodeIndex()] = true;
                visitNode(startNode, destination, currentPath, numberOfNodes, visited);
                visited[destination.getNodeIndex()] = false;
                currentPath.remove(edge);
            }
        }
    }
}
