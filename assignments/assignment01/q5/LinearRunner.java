package CS321.assignments.assignment01.q5;

/**
 * Created by John on 9/21/2014.
 */
public class LinearRunner
{
    public static long run(int n)
    {
        double x = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i += 1)
        {
            x += 1;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }
}