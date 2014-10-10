import java.util.Random;

/**
 * Created by thomaj46 on 10/8/2014.
 */
public class StockAnalyzer
{
    private static int maxProfit = Integer.MIN_VALUE;

    public static void main(String[] args)
    {
//        for (int p = 284; p < 285; p += 1)
//        {
//            Random random = new Random(p);
//            int size = 10000000;
//            int[] vals = new int[size];
//            for (int i = 0; i < size; i += 1)
//            {
//                vals[i] = random.nextInt(Integer.MAX_VALUE);
//            }
//
//            double maxProfit = StockAnalyzer.findMaxProfit(0, size - 1, vals);
//            System.out.println(maxProfit);
//        }

        int[] myArr = new int[]{ 4, -7, 3, 3, -5, -2, 3, -2, 4 };
        int maxProfit = StockAnalyzer.findMaxProfit(0, myArr.length - 1, myArr);
        System.out.println(maxProfit);
    }

    public static int findMaxProfit(int begin, int end, int[] values)
    {
        int difference = end - begin;
        if (difference == 0)
        {
            return values[begin];
        }

        if (difference == 1)
        {
            return values[begin] + values[end];
        }

        int mid = (end + begin) / 2;
        int leftSide = StockAnalyzer.findMaxProfit(begin, mid, values);
        int rightSide = StockAnalyzer.findMaxProfit(mid, end, values);
        int maxLeftHalf = findLeftMaxSum(mid, begin, values);
        int maxRightHalf = findRightMaxSum(mid, end, values);
        int midProfit = maxLeftHalf + maxRightHalf;

        int bestProfit = Math.max(rightSide, leftSide);
        bestProfit = Math.max(bestProfit, midProfit);

        System.out.println(bestProfit);

        return bestProfit;
    }

    public static int findLeftMaxSum(int mid, int end, int[] values)
    {
        int maxSum = Integer.MIN_VALUE;
        int total = Integer.MIN_VALUE;
        for(int i = mid; i < end; i -= 1)
        {
            total += values[i];
            maxSum = total > maxSum ? total : maxSum;
        }

        return maxSum;
    }

    public static int findRightMaxSum(int start, int mid, int[] values)
    {
        int maxSum = Integer.MIN_VALUE;
        int total = Integer.MIN_VALUE;
        for(int i = start; i <= mid; i += 1)
        {
            total += values[i];
            maxSum = total > maxSum ? total : maxSum;
        }

        return maxSum;
    }
}
