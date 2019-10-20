
/**
 * Name: Count Triplets
 * 
 * Problem: You are given an array and you need to find number of tripets of indices (i,j,k) such 
 *  that the elements at those indices are in geometric progression for a given common ratio r and 
 *  i < j < k.
 *  For example, arr = [1, 4, 16, 64]. If r = 4, we have [1,4,16] and [4,16,64] at indices (0,1,2) 
 *  and (1,2,3).
 * 
 * Input Format: The first line contains two space-separated integers n and r, the size of arr and 
 *  the common ratio. 
 *  The next line contains n space-seperated integers arr[i].
 * 
 * Constraints: 
 *     1 <= n <= 10^5
 *     1 <= r <= 10^9
 *     1 <= arr[i] <= 10^9
 * 
 * Output Format: Return the count of triplets that form a geometric progression.
 * 
 * Sample:
 *     Input:
 *         4 2
 *         1 2 2 4
 *     Output:
 *         2
 * 
 * Reference: https://www.hackerrank.com/challenges/count-triplets-1/forum/comments/468507
 * 
 */
import java.util.HashMap;
import java.util.Scanner;

public class CountTriplets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        final int ratio = in.nextInt();
        
        //We store the times we can use a value on 2nd and 3rd position of the
        //triplet
        HashMap<Long, Long> second = new HashMap<>();
        HashMap<Long, Long> third = new HashMap<>();

        long total = 0L;
        while (len-- > 0) {
            long num = in.nextLong();
            long numR = num * ratio;

            Long thirdValue = third.get(num);
            if (thirdValue == null) {
                thirdValue = 0L;
            }
            total += thirdValue;
            
            Long secondValue = second.get(num);
            if (secondValue != null) {
                thirdValue = third.get(numR);
                if (thirdValue == null) {
                    thirdValue = 0L;
                }
                
                third.put(numR, thirdValue + secondValue);
            }
            
            secondValue = second.get(numR);
            second.put(numR, secondValue == null ? 1L : secondValue + 1L);
        }
        
        System.out.println(total);
        in.close();
    }
}