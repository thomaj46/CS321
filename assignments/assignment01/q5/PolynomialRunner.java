package CS321.assignments.assignment01.q5;

/**
 * Created by John on 9/21/2014.
 */
public class PolynomialRunner
{
    public static long run(int n, int exponent)
    {
        double x = 0;
        long startTime = System.nanoTime();
        for (double i = 0; i < Math.pow(n, exponent); i += 1)
        {
            x += 1;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }
}
