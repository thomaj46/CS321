package CS321.assignments.assignment02.problem04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Problem04
{
    private String inputFile;

    public static void main(String[] args) throws Exception
    {
        String filename;
        if (args.length == 0)
        {
            filename = "input4.txt";
        }
        else
        {
            filename = args[0];
        }

        new Problem04().run(filename);
    }

    private void run(String filename)
    {
        this.inputFile = filename;
        ArrayList<String> votes = this.getInput();
        this.tallyVotes(votes);
    }

    private void tallyVotes(ArrayList<String> votes)
    {
        HashMap<String, Integer> voteTally = new HashMap<String, Integer>(votes.size() + 2);
        this.divideAndConquer(voteTally, votes, 0, votes.size() - 1);

        int totalVotes = votes.size();
        String candidate = "nobody";

        for(Map.Entry<String, Integer> entry : voteTally.entrySet())
        {
            if (entry.getValue() >= totalVotes / 2)
            {
                candidate = entry.getKey();
                break;
            }
        }

        System.out.println(candidate);
    }

    private void divideAndConquer(HashMap<String, Integer> voteTally, ArrayList<String> votes, int start, int end)
    {
        if (end - start == 0)
        {
            String vote = votes.get(end);
            if (!voteTally.containsKey(vote))
            {
                voteTally.put(vote, 0);
            }

            int currentTally = voteTally.get(vote);
            voteTally.put(vote, currentTally + 1);
            return;
        }

        int mid = (start + end) / 2;
        this.divideAndConquer(voteTally, votes, start, mid);
        this.divideAndConquer(voteTally, votes, mid + 1, end);
    }

    private ArrayList<String> getInput()
    {
        ArrayList<String> input = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();

            while (line != null)
            {
                input.add(line);
                line = reader.readLine();
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
