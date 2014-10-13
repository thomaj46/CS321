package CS321.assignments.assignment02.problem06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by John on 10/12/2014.
 */
public class Problem06
{
    public static void main(String[] args) throws Exception
    {
        // Read Wisconsin File into an array

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

//         Testing Calls
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
        return findKthLargest(A, B, k, 0, A.size() - 1, 0, B.size() - 1);
    }

    public static int findKthLargest(ArrayList<Integer> A, ArrayList<Integer> B, int k, int aStart, int aEnd, int bStart, int bEnd)
    {
        if (k == 0)
        {
            return Math.max(A.get(aEnd), B.get(bEnd));
        }

        if (k == 1)
        {
            int aValue = A.get(aEnd) > B.get(bEnd) ? A.get(aEnd - 1) : A.get(aEnd);
            int bValue = B.get(bEnd) > A.get(aEnd) ? B.get(bEnd - 1) : B.get(bEnd);
            return Math.max(aValue, bValue);
        }

        if ((aEnd - aStart) > k)
        {
            aStart = aEnd - k;
        }

        if ((bEnd - bStart) > k)
        {
            bStart = bEnd - k;
        }

        int aMid = (aStart + aEnd) / 2;
        int bMid = (bStart + bEnd) / 2;

        aMid = aMid < 0 ? 0 : aMid;
        bMid = bMid < 0 ? 0 : bMid;

        int aVal = A.get(aMid);
        int bVal = B.get(bMid);


        if (aVal > bVal)
        {
            return findKthLargest(A, B, k / 2, aStart, aMid, bMid, bEnd);
        }
        else
        {
            return findKthLargest(A, B, k / 2, aMid, aEnd, bStart, bMid);
        }
    }
}