
/**
 * Name: Minimum Absolute Difference in an Array
 * 
 * Problem: Consider an array of integers, arr = [arr[0], arr[1], ..., arr[n-1]]. We define the 
 *  absolute difference between two elements, arr[i] and arr[j] (where i != j), to be the absolute 
 *  value of a[i] - a[j].
 *  Given an array of integers, find and print the minimum absolute difference between any two 
 *  elements in the array. For example, given the array arr = [-2, 2, 4] we can create 3 pairs of 
 *  numbers: [-2,2], [-2,4] and [2,4]. The absolute differences for these pairs are [(-2) -2] = 4, 
 *  [(-2) -4] = 6 and [2 -4] = 2. The minimum absolute difference is 2.
 * 
 * Input Format: The first line contains a single integer n, the size of arr. 
 *  The second line contains n space-separated integers arr[i].
 * 
 * Constraints: 
 *     2 <= n <= 10⁵
 *     -10⁹ <= arr[i] <= 10⁹
 * 
 * Output Format: Print the minimum absolute difference between any two elements in the array.
 * 
 * Sample:
 *     Input:
 *         3
 *         3 -7 0
 *     Output:
 *         3
 * 
 */
import java.util.Arrays;
import java.util.Scanner;

public class MinimumAbsoluteDifferenceArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] arr = new int[n];
        String[] data = in.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(data[i]);
        }
        int min = minimumAbsoluteDifference(arr);
        System.out.println(min);
        in.close();
    }

    private static int minimumAbsoluteDifference(int[] arr) {
        //We sort the array
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 1; i < arr.length; i++) {
            int diff = Math.abs(arr[i - 1] - arr[i]);
            if (diff == 0) {
                return 0;
            } else if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }
}
