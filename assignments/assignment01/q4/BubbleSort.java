package CS321.assignments.assignment01.q4;

/**
 * Created by John on 9/21/2014.
 */
public class BubbleSort
{
    public static void sort(int[] array)
    {
        int temp, i, j;
        for (i = 0; i < array.length; i += 1)
        {
            for (j = 1; j < array.length - i; j += 1)
            {
                if (array[j] > array[j - 1])
                {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
}
