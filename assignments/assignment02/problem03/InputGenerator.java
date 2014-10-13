package CS321.assignments.assignment02.Problem03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by John on 10/4/2014.
 */
public class InputGenerator
{
    public static void main(String[] args)
    {
//        int[] test1 = new int[] {5, 2, 1, 6, 1, 3};
//        int[] test2 = new int[] {3, 4, 5, 6, 8, 9};
//        int[] test3 = new int[] {8, 6, 5, 4, 3, 8};
//        writeToFile(test1);
//        writeToFile(test2);
//        writeToFile(test3);


        for(int p=284; p<290; p++)
        {
            Random r = new Random(p);
            int size = 10000000;
            int numbers[] = new int[size];
            for(int i=0; i<size; i++)
            {
                numbers[i] = r.nextInt(Integer.MAX_VALUE);
            }

            writeToFile(numbers);
        }
    }

    public static void writeToFile(int[] array)
    {
        try
        {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("input3.txt", true)));

            int lastNumber = array.length - 1;
            for (int i = 0; i < array.length; i += 1)
            {
                out.print(array[i]);

                if (i < lastNumber)
                {
                    out.print(',');
                }
            }

            out.close();
        }
        catch (IOException e)
        {
            //exception handling left as an exercise for the reader
        }
    }
}
