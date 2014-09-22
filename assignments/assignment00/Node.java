package CS321.assignments.assignment00;

import java.util.ArrayList;

/**
 * Created by John Thomas on 2014-06-08.
 */
public class Node
{
    private ArrayList<Edge> edges;
    private int nodeIndex;

    public Node(int nodeIndex)
    {
        this.edges = new ArrayList<Edge>();
        this.nodeIndex = nodeIndex;
    }

    public ArrayList<Edge> getEdges()
    {
        return this.edges;
    }

    public void addEdge(Edge edge)
    {
        this.edges.add(edge);
    }

    public int getNodeIndex ()
    {
        return nodeIndex;
    }

    public Edge getEdgeToNode(Node destination)
    {
        Edge requestedEdge = null;

        for(Edge edge : this.edges)
        {
            if (edge.getDestinationNode() == destination)
            {
                requestedEdge = edge;
                break;
            }
        }

        if (null == requestedEdge)
        {
            System.out.println("Something went wrong.  Unable to find path linking nodes.");
            System.out.println(this);
            System.out.println(destination);
        }

        return requestedEdge;

    }

    @Override
    public String toString()
    {
        return String.valueOf(this.nodeIndex);
    }
}
