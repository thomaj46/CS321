import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by thomaj46 on 10/11/2014.
 */
public class Problem06
{
    public static void main(String[] args)
    {
        int[] A = new int[] {27, 40, 42, 43, 76, 79, 94, 99, 105, 106, 111, 122, 129, 146, 151, 152, 154, 163, 175, 186, 210, 216, 228, 233, 236, 247, 259, 286, 309, 317, 328, 336, 338, 340, 357, 372, 385, 390, 393, 394, 405, 415, 421, 424, 453, 460, 464, 484, 488, 496};
        int[] B = new int[] {5, 17, 38, 40, 52, 54, 81, 84, 85, 87, 92, 103, 118, 130, 146, 152, 166, 186, 187, 204, 218, 239, 255, 256, 257, 274, 306, 319, 325, 331, 332, 339, 345, 350, 356, 375, 389, 394, 396, 398, 405, 407, 410, 429, 430, 435, 455, 470, 481, 483};
        int k = 0;
        int kthLargest;

        int aEnd = A.length - 1;
        int bEnd = B.length - 1;

        for (int i = 3; i < 20; i += 1)
        {
            k = i;
            kthLargest = findKthLargest2(A, 0, aEnd, B, 0, bEnd, k);
            System.out.println(kthLargest);
        }

    }

    public static int findKthLargest2(int[] A, int aStart, int aEnd, int[] B, int bStart, int bEnd, int k)
    {
        int i = (k / 2) + aStart;
        int j = bEnd - i;
        int aVal = A[i];
        int bVal = B[j];

        if (aVal == bVal)
        {
            return aVal;
        }

        if (i == 0 && j == 0)
        {
            return Math.max(aVal, bVal);
        }

        if (aVal <= bVal)
        {
            return findKthLargest2(A, i, aEnd, B, bStart, j, k / 2);
        }
        else
        {
            return findKthLargest2(A, aStart, i, B, j, bEnd, k / 2);
        }

    }

    public static int findKthLargest(int[] A, int aEnd, int[] B, int bEnd, int k)
    {
        int mid = (k / 2);
        int i = mid;
        int j = k - mid;
        int aVal = A[i];
        int bVal = B[j];

        if (k < 5)
        {
            return getKth(A, aEnd, B, bEnd, k);
        }

        int newK = mid;
        if (aVal > bVal)
        {
            return findKthLargest(A, aEnd - mid, B, bEnd, newK);
        }
        else
        {
            return findKthLargest(A, aEnd, B, bEnd - mid, newK);
        }

    }

    public static int getKth(int[] A, int aEnd, int[] B, int bEnd, int k)
    {
        int i;
        int newSize = 2 * k;
        int[] merged = new int[newSize];
        for (i = 0; i < k; i += 1)
        {
            merged[i] = A[aEnd - i];
        }

        for (i = 0; i < k; i += 1)
        {
            merged[k + i] = B[bEnd - i];
        }

        Arrays.sort(merged);

        return merged[merged.length - 1];
    }
}
