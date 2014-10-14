import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by thomaj46 on 10/13/2014.
 */
public class Problem06
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<Integer> wisconsin = new ArrayList<Integer>();
        ArrayList<Integer> minnesota = new ArrayList<Integer>();
        String filename = "input6.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null)
        {
            wisconsin.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        reader.close();

        filename = "input6a.txt";
        reader = new BufferedReader(new FileReader(filename));
        line = reader.readLine();
        while (line != null)
        {
            minnesota.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        reader.close();

        System.out.println(findkth(wisconsin, minnesota, 0));
        System.out.println(findkth(wisconsin, minnesota, 1));
        System.out.println(findkth(wisconsin, minnesota, 2));
        System.out.println(findkth(wisconsin, minnesota, 3));
        System.out.println(findkth(wisconsin, minnesota, 4));
        System.out.println(findkth(wisconsin, minnesota, 5));
        System.out.println(findkth(wisconsin, minnesota, 6));
        System.out.println(findkth(wisconsin, minnesota, 7));
        System.out.println(findkth(wisconsin, minnesota, 8));
        System.out.println(findkth(wisconsin, minnesota, 9));
        System.out.println(findkth(wisconsin, minnesota, 10));
        System.out.println(findkth(wisconsin, minnesota, 11));
        System.out.println(findkth(wisconsin, minnesota, 12));
        System.out.println(findkth(wisconsin, minnesota, 13));
        System.out.println(findkth(wisconsin, minnesota, 14));
        System.out.println(findkth(wisconsin, minnesota, 15));
        System.out.println(findkth(wisconsin, minnesota, 16));
        System.out.println(findkth(wisconsin, minnesota, 17));
        System.out.println(findkth(wisconsin, minnesota, 18));
    }

    public static int findkth(ArrayList<Integer> A, ArrayList<Integer> B, int k)
    {

        return kthSmallest(A, B, (A.size() + B.size() - k));
    }


    public static Integer kthSmallest(ArrayList<Integer> aList, ArrayList<Integer> bList, int k) {
        int aListSize = aList.size();
        int bListSize = bList.size();
        int aStart = 1;
        int bStart = 1;
        int aEnd = k;
        int bEnd = k;
        int k1 = k;
        int k2 = k;
        int aLast = aListSize;
        int bLast = bListSize;

        while (k1 > 2 || k2 > 2)
        {
            int aMid = (aStart + aEnd) / 2;
            int bMid = k - aMid;
            int aVal;
            int bVal;

            if (aMid > aLast)
            {
                bVal = bList.get(bMid - 1);
                aVal = bVal + 1;
            }
            else if (bMid > bLast)
            {
                aVal = aList.get(aMid - 1);
                bVal = aVal + 1;
            }
            else
            {
                aVal = aList.get(aMid - 1);
                bVal = bList.get(bMid - 1);
            }

            if (aVal == bVal)
            {
                return aVal;
            }
            else if (aVal > bVal)
            {
                aEnd = aMid;
                bStart = bMid;
            }
            else
            {
                aStart = aMid;
                bEnd = bMid;
            }

            k1 = aEnd - aStart + 1;
            k2 = bEnd - bStart + 1;
            aLast = Math.min(aListSize, aEnd);
            bLast = Math.min(bListSize, bEnd);
        }

        if (k <= 2)
        {
            int val1 = Math.min(aList.get(aStart - 1), aList.get(aLast - 1));
            int val2 = Math.min(bList.get(bStart - 1), bList.get(bLast - 1));
            return (k == 1) ? Math.min(val1, val2) : Math.max(val1, val2);
        }

        if (aStart == aLast)
        {
            int val1 = aList.get(aStart - 1);
            int val2 = bList.get(k - aStart - 1);
            return Math.max(val1, val2);
        }

        if (bStart == bLast)
        {
            int val2 = bList.get(bStart - 1);
            int val1 = aList.get(k - bStart - 1);
            return Math.max(val1, val2);
        }

        int max1 = Math.max(aList.get(aStart - 1), aList.get(aLast - 1));
        int max2 = Math.max(bList.get(bStart - 1), bList.get(bLast - 1));
        int min = Math.min(max1, max2);

        return min;
    }
}
