
/**
 * Name: Array Manipulation
 * 
 * Problem: Starting with a 1-indexed array of zeros and a list of operations, 
 *  for each operation add a value to each of the array element between two 
 *  given indices, inclusive. Once all operations have been performed, 
 *  return the maximum value in your array.
 * 
 *  For example, the length of your array of zeros n = 10. Your list of queries 
 *  is as follows:
 *      a b k
 *      1 5 3
 *      4 8 7
 *      6 9 1
 * 
 *  Add the values of k between the indices a and b inclusive:
 *  
 *  index->	 1 2 3  4  5 6 7 8 9 10
 *  	    [0,0,0, 0, 0,0,0,0,0, 0]
 *  	    [3,3,3, 3, 3,0,0,0,0, 0]
 *  	    [3,3,3,10,10,7,7,7,0, 0]
 *  	    [3,3,3,10,10,8,8,8,1, 0]
 *  The largest value is 10 after all operations are performed.
 * 
 * Input Format: The first line contains two space-separated integers n and m, 
 *  the size of the array and the number of operations. 
 *  Each of the next m lines contains three space-separated integers a, b and k, 
 *  the left index, right index and summand.
 * 
 * Constraints: 
 *  3 <= n <= 10^7
 *  1 <= m <= 2 * 10^5
 *  1 <= a <= b <= n
 *  0 <= k <= 10^9
 * 
 * Output Format: Return the integer maximum value in the finished array.
 * 
 * Sample: 
 *   Input: 
 *     5 3
 *     1 2 100
 *     2 5 100
 *     3 4 100
 *     
 *   Output: 
 *     200
 */
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayManipulation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int len = in.nextInt();
        int tasks = in.nextInt();
        final long[] values = new long[len + 2];

        while (tasks-- > 0) {
            int from = in.nextInt();
            int to = in.nextInt();
            int val = in.nextInt();
            
            if (val > 0) {
                //We set the limits of where the value start and ends
                values[from] += val;
                values[to + 1] -= val;
            }
        }
        
        long sum = 0, max = 0;
        //we start adding up the values until we reach the end and check
        //the spot with the maximum value 
        for (long val : values) {
            sum += val;
            if (sum > max) {
                max = sum;
            }
        }

        System.out.println(max);

        in.close();
    }

}