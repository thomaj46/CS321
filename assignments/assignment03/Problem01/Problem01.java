package CS321.assignments.assignment03.Problem01;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by John on 10/25/2014.
 */
public class Problem01
{
    private int turnPenalty;
    private int numberOfNodes;
    private Node[] graph;
    private int[] topSolutions;
    private int[] middleSolutions;
    private int[] bottomSolutions;

    public static void main (String[] args)
    {
        new Problem01().run();
    }

    public void run ()
    {
        int[] input = this.processInputFile();
        if (input.length < 1)
        {
            return;
        }

        this.turnPenalty = input[0];
        this.numberOfNodes = input[1];
        this.graph = new GraphMaker(this.numberOfNodes).makeGraph();
        this.fillGraphWeights(input);
        this.topSolutions = new int[this.graph.length];
        this.middleSolutions = new int[this.graph.length];
        this.bottomSolutions = new int[this.graph.length];
        for (int i = 0; i < this.graph.length; i +=1)
        {
            this.topSolutions[i] = Integer.MIN_VALUE;
            this.middleSolutions[i] = Integer.MIN_VALUE;
            this.bottomSolutions[i] = Integer.MIN_VALUE;
        }

        int solution = this.findMax();
        System.out.println(solution);
    }

    private int findMax ()
    {
        Node rootNode = this.graph[1];
        int top = this.findMax(Path.Top, this.graph[rootNode.TopChild]) + rootNode.TopChildCost;
        int middle = this.findMax(Path.Middle, this.graph[rootNode.MiddleChild]) + rootNode.MiddleChildCost;
        int bottom = this.findMax(Path.Bottom, this.graph[rootNode.BottomChild]) + rootNode.BottomChildCost;

        return Math.max(Math.max(top, middle), bottom);
    }

    private int findMax (Path currentPath, Node currentNode)
    {
        if (currentNode.HasNoChildren())
        {
            return 0;
        }

        if (currentPath == Path.Top && this.topSolutions[currentNode.Id] > Integer.MIN_VALUE)
        {
            return this.topSolutions[currentNode.Id];
        }

        if (currentPath == Path.Middle && this.middleSolutions[currentNode.Id] > Integer.MIN_VALUE)
        {
            return this.middleSolutions[currentNode.Id];
        }

        if (currentPath == Path.Bottom && this.bottomSolutions[currentNode.Id] > Integer.MIN_VALUE)
        {
            return this.bottomSolutions[currentNode.Id];
        }

        int penalty;
        if (currentNode.HasTopChild())
        {
            penalty = currentPath == Path.Top ? 0 : this.turnPenalty;
            this.topSolutions[currentNode.Id] = this.findMax(Path.Top, this.graph[currentNode.TopChild]) + currentNode.TopChildCost + penalty;
        }

        if (currentNode.HasMiddleChild())
        {
            penalty = currentPath == Path.Middle ? 0 : this.turnPenalty;
            this.middleSolutions[currentNode.Id] = this.findMax(Path.Middle, this.graph[currentNode.MiddleChild]) + currentNode.MiddleChildCost + penalty;
        }

        if (currentNode.HasBottomChild())
        {
            penalty = currentPath == Path.Bottom ? 0 : this.turnPenalty;
            this.bottomSolutions[currentNode.Id] = this.findMax(Path.Bottom, this.graph[currentNode.BottomChild]) + currentNode.BottomChildCost + penalty;
        }

        int top = this.topSolutions[currentNode.Id];
        int middle = this.middleSolutions[currentNode.Id];
        int bottom = this.bottomSolutions[currentNode.Id];
        return Math.max(Math.max(top, middle), bottom);
    }

    private void fillGraphWeights (int[] input)
    {
        int nextWeight = 2;
        Node node;
        for (int i = 1; i < this.graph.length; i += 1)
        {
            node = this.graph[i];
            node.TopChildCost = node.HasTopChild() ? input[nextWeight++] : 0;
            node.MiddleChildCost = node.HasMiddleChild() ? input[nextWeight++] : 0;
            node.BottomChildCost = node.HasBottomChild() ? input[nextWeight++] : 0;
        }
    }

    public int[] processInputFile ()
    {
        int[] input = new int[0];
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("input1.txt"));
            String line = reader.readLine();
            String[] values = line.split(" ");
            input = new int[values.length];
            for (int i = 0; i < values.length; i += 1)
            {
                input[i] = Integer.parseInt(values[i]);
            }
        }
        catch (Exception exception)
        {
            System.out.println("Error processing input.");
            System.out.print(exception);
        }

        return input;
    }

    private void printGraph ()
    {
        for (Node node : this.graph)
        {
            System.out.println("Node: " + node.Id + " TopChild: " + node.TopChild + " MiddleChild: " + node.MiddleChild + " BottomChild: " + node.BottomChild);
        }
    }

    private enum Path
    {
        Top,
        Middle,
        Bottom,
    }
}
