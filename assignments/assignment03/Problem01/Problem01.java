package CS321.assignments.assignment03.Problem01;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by John on 10/25/2014.
 */
public class Problem01
{
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

        int turnPenalty = input[0];
        int numberOfNodes = input[1];
        Node[] graph = new GraphMaker(numberOfNodes).makeGraph();
        for (Node node : graph)
        {
            System.out.println("Node: " + node.Id + " TopChild: " + node.TopChild + " MiddleChild: " + node.MiddleChild + " BottomChild: " + node.BottomChild);
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
}
