
/**
 * Name: Triple Sum
 * 
 * Problem: Given 3 arrays a, b, c of different sizes, find the number of distinct triplets (p, q, 
 *  r) where p is an element of a, written as p ∈ a , q ∈ b, and r ∈ c, satisfying the criteria: 
 *  p <= q and q >= r.
 * 
 *  For example, given a = [3,5,7], b =[3,6] and c = [4,6,9], we find four distinct triplets: 
 *  (3,6,4), (3,6,6), (5,6,4), (5,6,6).
 * 
 * Input Format: The first line contains 3 integers lena, lenb, and lenc the sizes of the three 
 *  arrays. 
 *  The next 3 lines contain space-separated integers numbering lena, lenb, lenc respectively.

 * 
 * Constraints: 
 *     1 <= lena, lenb, lenc <= 10⁵
 *     1 <= all elemnts in a,b,c <= 10⁸
 * 
 * Output Format: Print an integer representing the number of distinct triplets.
 * 
 * Sample:
 *     Input:
 *         3 2 3
 *         1 3 5
 *         2 3
 *         1 2 3
 *     Output:
 *         8
 * 
 */
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

public class TripleSum {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(" ");
        int lena = Integer.parseInt(data[0]);
        int lenb = Integer.parseInt(data[1]);
        int lenc = Integer.parseInt(data[2]);

        int[] aArr = getSet(in, lena);
        int[] bArr = getSet(in, lenb);
        int[] cArr = getSet(in, lenc);

        System.out.println(countTriplets(aArr, bArr, cArr));
        in.close();
    }

    private static int[] getSet(Scanner in, int len)  {
        String[] data = in.nextLine().split(" ");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int num = Integer.parseInt(data[i]);
            arr[i] = num;
        }
        return Arrays.stream(arr).sorted().distinct().toArray();
    }

    private static long countTriplets(int[] aArr, int[] bArr, int[] cArr) {
        long count = 0;
        int aIndex = 0, cIndex = 0;
        for (int i = 0; i < bArr.length; i++) {
            int bVal = bArr[i];

            while (aIndex < aArr.length && aArr[aIndex] <= bVal) {
                ++aIndex;
            }

            while (cIndex < cArr.length && cArr[cIndex] <= bVal) {
                ++cIndex;
            }

            count += (long)(aIndex) * cIndex;
        }
        return count;
    }
}