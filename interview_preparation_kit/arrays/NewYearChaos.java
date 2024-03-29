
/**
 * Name: New Year Chaos
 * 
 * Problem: t's New Year's Day and everyone's in line for the Wonderland 
 *  rollercoaster ride! There are a number of people queued up, and each person 
 *  wears a sticker indicating their initial position in the queue. Initial 
 *  positions increment by 1 from 1 at the front of the line to n at the back.
 *  
 *  Any person in the queue can bribe the person directly in front of them to 
 *  swap positions. If two people swap positions, they still wear the same 
 *  sticker denoting their original places in line. One person can bribe at 
 *  most two others. For example, if n = 8 and Person 5 bribes Person 4, the 
 *  queue will look like this: 1,2,3,5,4,6,7,8.
 *  
 *  Fascinated by this chaotic queue, you decide you must know the minimum 
 *  number of bribes that took place to get the queue into its current state!
 * 
 * Input Format: The first line contains an integer t, the number of test cases.
 *  Each of the next t pairs of lines are as follows: 
 *  - The first line contains an integer t, the number of people in the queue 
 *  - The second line has  space-separated integers describing the final state 
 *      of the queue.
 * 
 * Constraints: 
 *  1 <= t <= 10
 *  1 <= n <= 10^5
 * 
 * Output Format: Print an integer denoting the minimum number of bribes needed 
 *  to get the queue into its final state. Print Too chaotic if the state is 
 *  invalid, i.e. it requires a person to have bribed more than 2 people.
 * 
 * Sample: 
 *   Input: 
 *     2
 *     5
 *     2 1 5 3 4
 *     5
 *     2 5 1 3 4
 * 
 *   Output: 
 *     3
 *     Too chaotic
 */
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class NewYearChaos {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tests = scan.nextInt();
        while (tests-- > 0) {
            final int queueSize = scan.nextInt();
            final int[] queue = new int[queueSize];

            boolean tooChaotic = false;

            for (int pos = 1; pos <= queueSize; pos++) {
                int personPos = scan.nextInt();
                queue[pos - 1] = personPos;
                if (personPos - pos > 2) {
                    scan.nextLine();// We clean the line
                    tooChaotic = true;
                    break;
                }
            }
            if (tooChaotic) {
                System.out.println("Too chaotic");
            } else {
                int swaps = swapsToSort(queue);
                System.out.println(swaps);

            }
        }
        scan.close();
    }

    private static int swapsToSort(int[] queue) {
        int swaps = 0;
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (int index = 0; index < queue.length - 1; index++) {
                int next = index + 1;
                if (queue[index] > queue[next]) {
                    hasChanged = true;
                    int tmp = queue[index];
                    queue[index] = queue[next];
                    queue[next] = tmp;
                    ++swaps;
                }
            }
        }
        return swaps;
    }

}