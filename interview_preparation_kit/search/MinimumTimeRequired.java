/**
 * Name: Minimum Time Required
 * 
 * Problem: You are planning production for an order. You have a number of machines that each have 
 *  a fixed number of days to produce an item. Given that all the machines operate simultaneously, 
 *  determine the minimum number of days to produce the required order.
 * 
 *  For example, you have to produce goal = 10 items. You have three machines that take machines = 
 *  [2,3,2] days to produce an item. The following is a schedule of items produced:
 * 
 *  Day Production  Count
 *  2   2               2
 *  3   1               3
 *  4   2               5
 *  6   3               8
 *  8   2              10
 *  It takes 8 days to produce 10 items using these machines.
 * 
 * Input Format: The first line consist of two integers n and goal, the size of machines and the 
 *  target production. 
 *  The next line contains n space-separated integers, machines[i].
 * 
 * Constraints: 
 *     1 <= n <= 10⁵
 *     1 <= goal <= 10⁹
 *     1 <= machines[i] <= 10⁹
 * 
 * Output Format: Return the minimum time required to produce goal items considering all machines 
 *  work simultaneously.
 * 
 * Sample:
 *     Input:
 *         2 5
 *         2 3
 *     Output:
 *         6
 * Reference: https://www.hackerrank.com/challenges/minimum-time-required/forum/comments/470821
 */
import java.util.Scanner;
import java.util.Arrays;

public class MinimumTimeRequired {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(" ");
        int n = Integer.parseInt(data[0]);
        long goal = Integer.parseInt(data[1]);

        data = in.nextLine().split(" ");
        long[] machines = new long[n];

        for (int i = 0; i < n; i++) {
            machines[i] = Integer.parseInt(data[i]);
        }

        System.out.println(countDays(goal, machines));

        in.close();
    }

    private static int countDays(long goal, long[] machines) {
        Arrays.sort(machines);

        //We get the theoretical minimum amount of days to reach the goal
        long minDays = (long)(goal / ((float)(machines.length) / machines[0]));
        
        //We get the theoretical maximum amount of days to reach the goal
        long maxDays = (long)(goal / ((float)(machines.length) / machines[machines.length - 1]) + 1);

        while (minDays < maxDays) {
            //We get the days in between of max and min
            long days = (minDays + maxDays)/2;
            long total = itemsProduced(machines, goal, days);

            //If at such day we produce more than needed we reduce the max amount of days
            //else we increase the minimum amount of days
            //searching the minimum amount of days with O(nlogn) runtime
            if (total >= goal) {
                maxDays = days;
            } else {
                minDays = days + 1;
            }
        }

        return minDays;
    }

    //Calculate the ammount of items produces at such day
    private static long itemsProduced(long[] machines, long goal, long days) {
        long total = 0;
        for (long machine : machines) {
            total += days/machine;
            if (total >= goal) {
                break;
            }
        }
        return total;
    }

}