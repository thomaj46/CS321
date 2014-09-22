package CS321.assignments.assignment00;

import java.util.ArrayList;

/**
 * Created by John Thomas on 2014-06-08.
 */
public class Graph
{
    private ArrayList<Node> nodes;

    public Graph()
    {
        this.nodes = new ArrayList<Node>();
    }

    public ArrayList<Node> getNodes()
    {
        return this.nodes;
    }

    public Node getNode(int index)
    {
        return this.nodes.get(index);
    }

    public void addNode(Node node)
    {
        this.nodes.add(node);
    }

    public void addNode(int index, Node node)
    {
        this.nodes.add(index, node);
    }
}
