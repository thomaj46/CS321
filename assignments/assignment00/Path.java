package CS321.assignments.assignment00;

import java.util.ArrayList;

/**
 * Created by John Thomas on 2014-09-09.
 */
public class Path implements Comparable
{
    private String path;
    private double weight;

    public Path(ArrayList<Edge> edges)
    {
        this.weight = 0;
        StringBuilder stringBuilder = new StringBuilder();
        Edge firstEdge = edges.get(0);
        stringBuilder.append(firstEdge.getSourceNode());
        for (int i = 0; i < edges.size(); i += 1)
        {
            Edge edge = edges.get(i);
            stringBuilder.append("=>").append(edge.getDestinationNode());
            this.weight += edge.getWeight();
        }

        this.path = stringBuilder.toString();
    }

    public String getPath ()
    {
        return path;
    }

    public double getWeight ()
    {
        return weight;
    }

    @Override
    public int compareTo (Object o)
    {
        Path otherPath = (Path)o;
        return ((Double)this.weight).compareTo(otherPath.getWeight());
    }
}
