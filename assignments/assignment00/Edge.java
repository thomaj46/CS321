package CS321.assignments.assignment00;

/**
 * Created by John Thomas on 2014-06-08.
 */
public class Edge
{
    private double weight;
    private Node sourceNode;
    private Node destinationNode;

    public Edge(Node sourceNode, Node destinationNode, double weight)
    {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.weight = weight;
    }

    public double getWeight ()
    {
        return this.weight;
    }

    public Node getSourceNode ()
    {
        return this.sourceNode;
    }

    public Node getDestinationNode ()
    {
        return this.destinationNode;
    }

    @Override
    public String toString ()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.sourceNode);
        stringBuilder.append("->");
        stringBuilder.append(this.destinationNode);
        stringBuilder.append("  ");
        stringBuilder.append(this.weight);
        return stringBuilder.toString();
    }
}
