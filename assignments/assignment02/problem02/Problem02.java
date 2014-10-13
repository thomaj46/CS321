package CS321.assignments.assignment02.problem02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by John on 10/11/2014.
 */
public class Problem02
{
    private static String inputFile;
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            inputFile = "input2.txt";
        }
        else
        {
            inputFile = args[0];
        }

        new Problem02().run();
    }

    public Problem02()
    {

    }

    public void run()
    {
        this.processInputFile();
    }

    public void processInputFile()
    {
        int[] input = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();

            while (line != null)
            {
                String[] values = line.split(",");
                int openIndex = Integer.parseInt(values[0]) + 1;
                int openAltitude = Integer.parseInt(values[openIndex]);
                if (openAltitude < 1000)
                {
                    System.out.println("Landing gear opened below 1000.");
                }
                else
                {
                    System.out.println("Landing gear did not open below 1000.");
                }

                line = reader.readLine();
            }
        }
        catch (Exception exception)
        {
            System.out.println("Error processing input.");
            System.out.print(exception);
        }

        ;
    }

    private class Input
    {
        public int OpenIndex;
        public ArrayList<Integer> Altitudes;

        public Input()
        {
            this.Altitudes = new ArrayList<Integer>();
        }
    }
}
