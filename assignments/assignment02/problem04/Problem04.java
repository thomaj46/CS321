package CS321.assignments.assignment02.problem04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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




//        // Read Candidate Names File into an array
//
//        String[] candidates = null;
//        File file = new File("input4.txt");
//        if (!file.exists())
//        {
//            System.out.println("The file " + file + " doesn't exist!");
//        }
//        else
//        {
//            // read from file to array candidates
//            FileReader fr = new FileReader(file);
//            BufferedReader reader = new BufferedReader(fr);
//            int numLines = readLines(file);
//            int counter = 0;
//            candidates = new String[numLines];
//            String in = reader.readLine();
//            while(in != null)
//            {
//                candidates[counter] = in;
//                counter++;
//                in = reader.readLine();
//            }
//            reader.close();
//        }
//
//        //Print the votes
//        System.out.println(Arrays.toString(candidates));
//        // Find and Print the winner
//        System.out.println(findMajority(candidates));
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

    private static String findMajority(String[] names)
    {
        int mid = names.length / 2;
        return recurse(names, 0, mid-1, mid, names.length-1);
    }

    private static String recurse(String[] names, int leftStart, int leftEnd, int rightStart, int rightEnd)
    {
        int midLeft = (leftEnd - leftStart) / 2;
        int midRight = (rightEnd - rightStart) / 2;

        // Base Cases
        // Only one element on the left side
        if (midLeft == 0)
        {
            // return the majority winner
            return names[leftStart];
        }

        // Only one element on the right side
        if (midRight == 0)
        {
            // return the majority winner
            return names[rightStart];
        }

        if (leftEnd - rightEnd == names.length)
        {
            return "Nobody";
        }

        //Recursion
        return recurse(names, leftStart, midLeft-1, midLeft, leftEnd) + recurse(names, rightStart, midRight-1, midRight, rightEnd);
    }

    private static int readLines(File file) throws IOException
    {
        FileReader inputFile = new FileReader(file);
        BufferedReader bf = new BufferedReader(inputFile);
        int numLines = 0;
        String aLine = bf.readLine();
        while (aLine != null) {
            numLines++;
            aLine = bf.readLine();
        }
        bf.close();
        return numLines;
    }
}
