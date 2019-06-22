
/**
 * Name: Pairs
 * 
 * Problem: You will be given an array of integers and a target value. Determine the number of 
 *  pairs of array elements that have a difference equal to a target value.
 *  For example, given an array of [1, 2, 3, 4] and a target value of 1, we have three values 
 *  meeting the condition: 2 - 1 = 1, 3- 2 = 1, and 4 - 3 = 1.
 * 
 * Input Format: The first line contains two space-separated integers n and k, the size of arr and 
 *  the target value. 
 *  The second line contains n space-separated integers of the array arr.
 * 
 * Constraints: 
 *     2 <= n <= 10⁵
 *     0 < k < 10⁹
 *     0 < arr[i] < 2³¹ - 1
 *     each integer arr[i] will be unique
 * 
 * Output Format: An integer representing the number of pairs of integers whose difference is k.
 * 
 * Sample:
 *     Input:
 *         5 2
 *         1 5 3 4 2
 *     Output:
 *         3
 * 
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class Pairs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);
        
        data = in.nextLine().split(" ");
        HashSet<Integer> sets = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(data[i]);
            sets.add(num);
        }

        System.out.println(countPairs(n, k, sets));
        in.close();

    }

    private static int countPairs(int n, int k, HashSet<Integer> sets) {
        int pairs = 0;
        Iterator<Integer> iterateNums = sets.iterator();
        
        for (int i = 0; i < n; i++) {
        Integer num = iterateNums.next();

            boolean pairExists = sets.contains(num + k);
            if (pairExists) {
                pairs++;
            }
        }

        return pairs;
    }
}