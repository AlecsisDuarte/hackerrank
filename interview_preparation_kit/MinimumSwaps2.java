
/**
 * Name: Minimum Swaps 2
 * 
 * Problem: You are given an unordered array consisting of consecutive integers 
 *  [1, 2, 3, ..., n] without any duplicates. You are allowed to swap any two 
 *  elements. You need to find the minimum number of swaps required to sort the 
 *  array in ascending order.
 *  
 *  For example, given the array  we perform the following steps:
 *   i   arr                     swap (indices)
 *   0   [7, 1, 3, 2, 4, 5, 6]   swap (0,3)
 *   1   [2, 1, 3, 7, 4, 5, 6]   swap (0,1)
 *   2   [1, 2, 3, 7, 4, 5, 6]   swap (3,4)
 *   3   [1, 2, 3, 4, 7, 5, 6]   swap (4,5)
 *   4   [1, 2, 3, 4, 5, 7, 6]   swap (5,6)
 *   5   [1, 2, 3, 4, 5, 6, 7]
 *  It took 5 swaps to sort the array.
 * 
 * Input Format: The first line contains an integer, n, the size of arr. 
 *  The second line contains n space-separated integers arr[i].
 * 
 * Constraints: 
 *  1 <= n <= 10^5
 *  1 <= arr[1] <= n
 * 
 * Output Format: Return the minimum number of swaps to sort the given array.
 * 
 * Sample: 
 *   Input: 
 *     4
 *     4 3 1 2
 * 
 *   Output: 
 *     3
 */
import java.util.Scanner;

public class MinimumSwaps2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];

        for (int index = 0; index < len; index++) {
            arr[index] = in.nextInt();
        }

        final int swaps = minimumSwapsNeeded(arr, len);
        System.out.println(swaps);
        in.close();
    }

    //In order to find the minimum amount of swaps I use selective sort
    private static int minimumSwapsNeeded(int[] arr, int len) {
        int pos = 0;
        int swaps = 0;
        int max = len - 1; //Is not needed to search for the biggest number

        while(pos++ < max) {
            int realPos = pos - 1;
            for (int index = realPos; index < len; index++) {
                if (pos == arr[index]) {
                    if (index != realPos) {
                        int tmp = arr[index];
                        arr[index] = arr[realPos];
                        arr[realPos] = tmp;
                        swaps++;
                    }
                    break;
                }
            }
        }
        return swaps;
    }

}