//package CS321.assignments.mini06;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by thomaj46 on 10/20/2014.
 */
public class ChickenFarmer
{
    int numberOfEggs;
    EggProfit[] EggProfits;


    public static void main(String[] args)
    {
        new ChickenFarmer(args[0]).run();
    }

    public ChickenFarmer(String numberOfEggs)
    {
        this.numberOfEggs = Integer.parseInt(numberOfEggs);
        this.EggProfits = new EggProfit[]
                {
                        new EggProfit(1, 2),
                        new EggProfit(2, 4),
                        new EggProfit(3, 7),
                        new EggProfit(4, 8),
                        new EggProfit(5, 12),
                        new EggProfit(6, 14),
                        new EggProfit(7, 15),
                        new EggProfit(8, 18),
                };
    }

    public void run()
    {
        Arrays.sort(this.EggProfits, Collections.reverseOrder());

        int eggsSold = 0;
        int totalProfit = 0;
        for (int i = 0; i < this.EggProfits.length; i += 1)
        {
            int profit = (int)this.EggProfits[i].Profit;
            int quantity = (int)this.EggProfits[i].Quantity;
            int eggsLeft = (this.numberOfEggs - eggsSold);
            int groups = eggsLeft / quantity;
            totalProfit += groups * profit;
            eggsSold += groups * quantity;
        }

        // Comment this line if you don't want to see the max profit.
        System.out.println(totalProfit);
    }

    public class EggProfit implements Comparable
    {
        public double Quantity;
        public double Profit;
        public double ProfitRatio;

        public EggProfit(double quantity, double profit)
        {
            this.Quantity = quantity;
            this.Profit = profit;
            this.ProfitRatio = profit / quantity;
        }

        @Override
        public int compareTo(Object o)
        {
            return ((Double)(this.ProfitRatio)).compareTo(((EggProfit) o).ProfitRatio);
        }
    }
}

