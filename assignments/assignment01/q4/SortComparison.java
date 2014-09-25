package CS321.assignments.assignment01.q4;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by John on 9/21/2014.
 */
public class SortComparison
{
    public static void main(String[] args)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 10; i < 10000001; i *= 10)
        {
            long arraysSortRuntime = timeArraysSort(i);
            stringBuilder.append("Arrays.sort() took ").append(arraysSortRuntime).append(" milliseconds to sort ").append(i).append(" items.");
            System.out.println(stringBuilder);
            stringBuilder = new StringBuilder();
        }

        stringBuilder = new StringBuilder();
        for (int i = 10; i < 10000001; i *= 10)
        {
            long bubbleSortRuntime = timeBubbleSort(i);
            stringBuilder.append("BubbleSort.sort() took ").append(bubbleSortRuntime).append(" milliseconds to sort ").append(i).append(" items.");
            System.out.println(stringBuilder);
            stringBuilder = new StringBuilder();
        }
    }

    private static long timeArraysSort(int itemCount)
    {
        int[] array = buildArray(itemCount);
        long startTime = System.nanoTime();
        Arrays.sort(array);
        long stopTime = System.nanoTime();
        return nanoToMilli(stopTime - startTime);
    }

    private static long timeBubbleSort(int itemCount)
    {
        int[] array = buildArray(itemCount);
        long startTime = System.nanoTime();
        BubbleSort.sort(array);
        long stopTime = System.nanoTime();
        return nanoToMilli(stopTime - startTime);
    }

    private static long nanoToMilli(long input)
    {
        return input / 1000000;
    }

    private static int[] buildArray(int itemCount)
    {
        Random random = new Random(321);
        int[] array = new int[itemCount];
        for(int i = 0; i < itemCount; i += 1)
        {
            array[i] = random.nextInt();
        }
        return array;
    }
}
