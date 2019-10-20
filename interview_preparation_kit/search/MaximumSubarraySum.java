/**
 * Name: Maximum Subarray Sum
 * 
 * Problem: 
 *  We define the following:
 *    - A subarray of array a of length n is a contiguous segment from a[i] through a[j] where 
 *          0 <= i <= j < n.
 *    - The sum of an array is the sum of its elements.
 *  Given an n element array of integers, a, and an integer, m, determine the maximum value of the 
 *  sum of any of its subarrays modulo m. For example, Assume a = [1,2,3] and m = 2. The following 
 *  table lists all subarrays and their moduli:
 *        		    sum	    %2
 *      [1]		    1	    1
 *      [2]		    2	    0
 *      [3]		    3	    1
 *      [1,2]		3	    1
 *      [2,3]		5	    1
 *      [1,2,3]		6	    0
 *  The maximum modulus is 1.
 * 
 * Input Format: The first line contains an integer q, the number of queries to perform.
 *  The next q pairs of lines are as follows:
 *   - The first line contains two space-separated integers n and (long)m, the length of a and the 
 *      modulo divisor.
 *   - The second line contains n space-separated long integers a[i].
 * 
 * Constraints: 
 *     2 <= n <= 10⁵
 *     1 <= m <= 10¹⁴
 *     1 <= a[i] <= 10¹⁸
 *     2 <= the sum of n over all test cases <= 5 x 10⁵
 * 
 * Output Format: For each query, return the maximum value of subarray sum % m as a long integer.
 * 
 * Sample:
 *     Input:
 *         1
 *         5 7
 *         3 3 9 9 5
 *     Output:
 *         6
 * 
 */
import java.io.*;
import java.util.*;

public class MaximumSubarraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        long q = Long.parseLong(in.readLine());
        while (q-- > 0) {
            String[] values = in.readLine().split(" ");
            int n = Integer.parseInt(values[0]);
            long m = Long.parseLong(values[1]);

            values = in.readLine().split(" ");
            long[] arr = new long[n];
            arr[0] = Long.parseLong(values[0]) % m;
            for (int i = 1; i < arr.length; i++) {
                //We sum all the mod's result and mod that result
                arr[i] = (arr[i-1] + Long.parseLong(values[i])) % m;
            }

            out.write(String.valueOf(getMaxSum(arr, m)));
            out.write("\n");
        }

        out.close();
        in.close();
    }

    private static long getMaxSum(long[] arr, long m) {
        final long MAX_MODULUS = m - 1;
        long maxModulus = 0;

        TreeSet<Long> prefix = new TreeSet<Long>();
        for (int i = 0; i < arr.length; i++) {
            Iterator<Long> itr = prefix.tailSet(arr[i] + 1).iterator();
            if (itr.hasNext()) {
                long val = (arr[i] - itr.next() + m) % m;
                if (val > maxModulus) {
                    maxModulus = val;
                    if (maxModulus == MAX_MODULUS) {
                        return maxModulus;
                    }
                }
            }
            if (arr[i] > maxModulus) {
                maxModulus = arr[i];
                    if (maxModulus == MAX_MODULUS) {
                        return maxModulus;
                    }
            }
            prefix.add(arr[i]);
        }
        return maxModulus;
    }
}