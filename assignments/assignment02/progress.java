public class StockAnalyzer
{
    public static void main(String[] args)
    {
        for (int p = 284; p < 290; p += 1)
        {
            Random random = new Random(p);
            int size = 10000000;
            int[] values = new int[size];
            for (int i = 0; i < size; i += 1)
            {
                values[i] = random.nextInt(Integer.MAX_VALUE);
            }

            double maxProfit = StockAnalyzer.findMaxProfit(0, size - 1, values);
            System.out.printf("Max Profit: %.0f\n", maxProfit);
        }
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
