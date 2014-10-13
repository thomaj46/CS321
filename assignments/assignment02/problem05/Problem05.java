package CS321.assignments.assignment02.problem05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by John on 10/12/2014.
 */
public class Problem05
{
    public String inputFile;

    public static void main(String[] args)
    {
        String filename;
        if (args.length == 0)
        {
            filename = "input5.txt";
        }
        else
        {
            filename = args[0];
        }

        new Problem05().run(filename);
    }

    public void run(String filename)
    {
        this.inputFile = filename;
        ArrayList<Student> students = this.getStudents();

        if (students.size() < 2)
        {
            System.out.println("Invalid input.  Less than two students");
        }
        else
        {
            Cheaters cheaters = this.divideAndConquer(students, 0, students.size() - 1);
            System.out.println(cheaters);
        }
    }

    private Cheaters divideAndConquer(ArrayList<Student> students, int begin, int end)
    {
        // 2 students.  Return pair.
        if (end - begin == 1)
        {
            Cheaters cheaters = new Cheaters(students.get(begin), students.get(end));
            return cheaters;
        }

        // 3 students.  Return pair with min alpha.
        if (end - begin == 2)
        {
            Student studentA = students.get(begin);
            Student studentB = students.get(begin + 1);
            Student studentC = students.get(end);
            Cheaters cheatersAB = new Cheaters(studentA, studentB);
            Cheaters cheatersAC = new Cheaters(studentA, studentC);
            Cheaters cheatersBC = new Cheaters(studentB, studentC);

            Cheaters cheaters = this.findLowestAlpha(this.findLowestAlpha(cheatersAB, cheatersAC), cheatersBC);
            return cheaters;
        }

        int mid = (begin + end) / 2;
        Cheaters left = this.divideAndConquer(students, begin, mid);
        Cheaters right = this.divideAndConquer(students, mid + 1, end);

        return findLowestAlpha(left, right);
    }

    private Cheaters findLowestAlpha(Cheaters left, Cheaters right)
    {
        Cheaters[] cheatersArray = new Cheaters[5];
        cheatersArray[0] = left;
        cheatersArray[1] = right;

        if (left.CheaterOne.Id != right.CheaterOne.Id)
        {
            cheatersArray[2] = new Cheaters(left.CheaterOne, right.CheaterOne);
        }
        else
        {
            cheatersArray[2] = new Cheaters();
        }

        if (left.CheaterOne.Id != right.CheaterTwo.Id)
        {
            cheatersArray[3] = new Cheaters(left.CheaterOne, right.CheaterTwo);
        }
        else
        {
            cheatersArray[3] = new Cheaters();
        }

        if (left.CheaterTwo.Id != right.CheaterTwo.Id)
        {
            cheatersArray[4] = new Cheaters(left.CheaterTwo, right.CheaterTwo);
        }
        else
        {
            cheatersArray[4] = new Cheaters();
        }

        Arrays.sort(cheatersArray);
        return cheatersArray[0];
    }

    private ArrayList<Student> getStudents()
    {
        ArrayList<Student> students = new ArrayList<Student>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null)
            {
                students.add(new Student(line.split(" ")));
                line = reader.readLine();
            }
        }
        catch (Exception exception)
        {
            System.out.println("Error processing input.");
            System.out.println(exception);
        }

        return students;
    }

    private class Cheaters implements Comparable
    {
        public Student CheaterOne;
        public Student CheaterTwo;
        public double AlphaScore;

        public Cheaters(Student cheaterOne, Student cheaterTwo)
        {
            this.CheaterOne = cheaterOne;
            this.CheaterTwo = cheaterTwo;
            this.AlphaScore = cheaterOne.getAlpha(cheaterTwo);
        }

        public Cheaters()
        {
            this.AlphaScore = Double.MAX_VALUE;
        }

        @Override
        public String toString()
        {
            return new StringBuilder()
                    .append("Possible cheaters: ")
                    .append(this.CheaterOne.Id)
                    .append(" & ")
                    .append(this.CheaterTwo.Id)
                    .append(" with alpha score: ")
                    .append(this.AlphaScore)
                    .toString();
        }

        @Override
        public int compareTo(Object o)
        {
            Cheaters otherCheaters = (Cheaters)o;
            return ((Double)this.AlphaScore).compareTo(otherCheaters.AlphaScore);
        }
    }

    private class Student
    {
        public int Id;
        public double MidtermScore;
        public double FinalScore;

        public Student(String[] data)
        {
            this.Id = Integer.parseInt(data[0]);
            this.MidtermScore = Double.parseDouble(data[1]);
            this.FinalScore = Double.parseDouble(data[2]);
        }

        public double getAlpha(Student student)
        {
            return Math.sqrt(Math.pow(this.MidtermScore - student.MidtermScore, 2) + Math.pow(this.FinalScore - student.FinalScore, 2));
        }
    }
}
