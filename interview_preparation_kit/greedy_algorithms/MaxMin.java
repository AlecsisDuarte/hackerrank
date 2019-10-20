
/**
 * Name: Max Min
 * 
 * Problem: You will be given a list of integers, arr, and a single integer k. You must create an 
 *  array of length k from elements of arr such that its unfairness is minimized. Call that array 
 *  subarr. Unfairness of an array is calculated as max(subarr) - min(subarr)
 *  Where: 
 *  - max denotes the largest integer in subarr.
 *  - min denotes the smallest integer in subarr.
 *  
 *  As an example, consider the array [1,4,7,2] with a k of 2. Pick any two elements, test 
 *  subarr = [4,7].
 *  unfairnes = max(4,7) - min(4,7) = 7 - 4 = 3
 *  Testing for all pairs, the solution  provides the minimum unfairness.
 *  
 *  Note: Integers in  may not be unique.
 * 
 * Input Format: The first line contains an integer , the number of elements in array arr. 
 *  The second line contains an integer k. 
 *  Each of the next  lines contains an integer arr[i] where 0 <= 1 <= n.
 * 
 * Constraints: 
 *     2 <= n <= 10⁵
 *     2 <= k <= n
 *     0 <= arr[i] <= 10⁹
 * 
 * Output Format: An integer that denotes the minimum possible value of unfairness.
 * 
 * Sample:
 *     Input:
 *         7
 *         3
 *         10
 *         100
 *         300
 *         200
 *         1000
 *         20
 *         30
 *     Output:
 *         20
 * 
 */
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxMin {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int k = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }
        System.out.println(minimumPossibleUnfairness(k, arr));
        in.close();
    }

    private static int minimumPossibleUnfairness(int k, int[] arr) {
        Arrays.sort(arr);

        int minimumUnfairnes = Integer.MAX_VALUE;
        for (int i = k - 1; i < arr.length; i++) {
            int min = arr[i - k + 1];
            int max = arr[i];
            if (min == max) {
                return 0;
            }
            int unfairness = max - min;
            if (unfairness < minimumUnfairnes) {
                minimumUnfairnes = unfairness;
            }
        }
        return minimumUnfairnes;
    }
}