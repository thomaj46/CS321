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

        this.printSolution();
    }

    private void printSolution ()
    {
        int solution = this.findMax();
        Node node = this.graph[1];
        System.out.print(solution + " {" + node.Id);
        Path path = node.BestPathComingFrom(Path.None, this.turnPenalty);
        node = node.GetChild(path);
        while (node != null && node.Id > 0)
        {
            System.out.print(", " + node.Id);
            path = node.BestPathComingFrom(path, this.turnPenalty);
            node = node.GetChild(path);
        }

        System.out.println("}");
    }

    private int findMax ()
    {
        return findBestWeight(Path.None, this.graph[1]);
    }

    private int findBestWeight (Path currentPath, Node currentNode)
    {
        if (currentNode.HasNoChildren())
        {
            return 0;
        }

        if (currentNode.ChildSolutionsFound)
        {
            return currentNode.BestWeightComingFrom(currentPath, this.turnPenalty);
        }

        if (currentNode.HasTopChild())
        {
            currentNode.TopChildSolution = currentNode.TopChildWeight + this.findBestWeight(Path.Top, currentNode.TopChild);
        }

        if (currentNode.HasMiddleChild())
        {
            currentNode.MiddleChildSolution = currentNode.MiddleChildWeight + this.findBestWeight(Path.Middle, currentNode.MiddleChild);
        }

        if (currentNode.HasBottomChild())
        {
            currentNode.BottomChildSolution = currentNode.BottomChildWeight + this.findBestWeight(Path.Bottom, currentNode.BottomChild);
        }

        currentNode.ChildSolutionsFound = true;
        return currentNode.BestWeightComingFrom(currentPath, this.turnPenalty);
    }

    private void fillGraphWeights (int[] input)
    {
        int nextWeight = 2;
        Node node;
        for (int i = 1; i < this.graph.length; i += 1)
        {
            node = this.graph[i];
            node.TopChildWeight = node.HasTopChild() ? input[nextWeight++] : 0;
            node.MiddleChildWeight = node.HasMiddleChild() ? input[nextWeight++] : 0;
            node.BottomChildWeight = node.HasBottomChild() ? input[nextWeight++] : 0;
        }
    }

    private int[] processInputFile ()
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

}
