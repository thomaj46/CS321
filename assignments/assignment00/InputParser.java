package CS321.assignments.assignment00;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by John Thomas on 2014-06-08.
 */
public class InputParser
{
    /**
     * Parse a input .txt file into a Graph object
     * @param filePath file path of the input file
     * @return a Graph representation of the input
     */
    public Graph parse(String filePath) throws Exception
    {
        ArrayList<double[]> input = this.tokenizeInput(filePath);
        Graph graph = new Graph();
        Node sourceNode;
        Node destinationNode;
        double weight;
        int i, j;

        // Generate all nodes
        for (i = 0; i < input.size(); i += 1)
        {
            sourceNode = new Node((int)input.get(i)[0]);
            graph.addNode(i, sourceNode);
        }

        // Add edges to all nodes
        for (i = 0; i < input.size(); i += 1)
        {
            double[] line = input.get(i);
            sourceNode = graph.getNode(i);
            for (j = 1; j < line.length; j += 1)
            {
                weight = line[j];
                destinationNode = graph.getNode(j + i);
                destinationNode.addEdge(new Edge(destinationNode, sourceNode, weight));
                sourceNode.addEdge(new Edge(sourceNode, destinationNode, weight));
            }
        }

        return graph;
    }

    public ArrayList<double[]> tokenizeInput(String filePath) throws Exception
    {
        ArrayList<double[]> output = new ArrayList<double[]>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        String spaceRegex = "\\s+";
        while (null != line)
        {
            String[] tokens = line.split(spaceRegex);
            double[] lineOutput = new double[tokens.length];
            for (int i = 0; i < tokens.length; i += 1)
            {
                lineOutput[i] = Double.parseDouble(tokens[i]);
            }
            output.add(lineOutput);
            line = reader.readLine();
        }

        reader.close();
        return output;
    }
}
