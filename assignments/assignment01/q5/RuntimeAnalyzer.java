package CS321.assignments.assignment01.q5;

/**
 * Created by John on 9/21/2014.
 */
public class RuntimeAnalyzer
{
    public static void main(String[] args)
    {
        int n = 38;

        runLinearAlgorithm(n);
        runPolynomialAlgorithm(n);
        runExponentialAlgorithm(n);
    }

    public static void runLinearAlgorithm(int n)
    {
        System.out.print("Running of linear algorithm with n = ");
        System.out.print(n);
        System.out.print(" has runtime in nanoseconds: ");
        System.out.println(LinearRunner.run(n));
    }

    public static void runPolynomialAlgorithm(int n)
    {
        System.out.print("Running of cubic algorithm with n = ");
        System.out.print(n);
        System.out.print(" has runtime in milliseconds: ");
        System.out.println(PolynomialRunner.run(n, 6));
    }

    public static void runExponentialAlgorithm(int n)
    {
        System.out.print("Running of exponential algorithm with n = ");
        System.out.print(n);
        System.out.print(" has runtime in milliseconds: ");
        System.out.println(ExponentialRunner.run(n));
    }
}
