
/**
 * Name: Luck Balance
 * 
 * Problem: Lena is preparing for an important coding competition that is preceded by a number of 
 *  sequential preliminary contests. She believes in "saving luck", and wants to check her theory. 
 *  Each contest is described by two integers, L[i] and T[i]:
 *    - L[i] is the amount of luck associated with a contest. If Lena wins the contest, her luck 
 *      balance will decrease by L[i]; if she loses it, her luck balance will increase by L[i].
 *    - T[i] denotes the contest's importance rating. It's equal to 1 if the contest is important, 
 *      and it's equal to 0 if it's unimportant.
 * 
 *  If Lena loses no more than k important contests, what is the maximum amount of luck she can 
 *  have after competing in all the preliminary contests? This value may be negative.
 *  For example,  and:
 *      Contest		L[i]	T[i]
 *      1		    5	    1
 *      2		    1	    1
 *      3		    4	    0
 *  If Lena loses all of the contests, her will be 5 + 1 + 4 = 10. Since she is allowed to lose 2 
 *  important contests, and there are only 2 important contests. She can lose all three contests to 
 *  maximize her luck at 10. If k = 1, she has to win at least 1 of the 2 important contests. She 
 *  would choose to win the lowest value important contest worth 1. Her final luck will be 
 *  5 + 4 - 1 = 8.
 * 
 * Input Format: The first line contains two space-separated integers n and k, the number of 
 *  preliminary contests and the maximum number of important contests Lena can lose. Each of the 
 *  next n lines contains two space-separated integers, L[i] and T[i], the contest's luck balance 
 *  and its importance rating.
 * 
 * Constraints: 
 *     1 <= n <= 100
 *     0 <= k <= N
 *     1 <= L[i] <= 10⁴
 *     T[i] {0 , 1}
 * 
 * Output Format: Print a single integer denoting the maximum amount of luck Lena can have after all the contests.
 * 
 * Sample:
 *     Input:
 *         6 3
 *         5 1
 *         2 1
 *         1 1
 *         8 1
 *         10 0
 *         5 0
 *     Output:
 *         29
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LuckBalance {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);

        ArrayList<Integer> importantContests = new ArrayList<>();
        int luckAmount = 0;

        for (int i = 0; i < n; i++) {
            data = in.nextLine().split(" ");
            int luck = Integer.parseInt(data[0]);
            char important = data[1].charAt(0);
            luckAmount += luck;
            if (important == '1') {
                importantContests.add(luck);
            }
        }

        luckAmount += getLuckLost(importantContests, k);
        System.out.println(luckAmount);
        in.close();
    }

    private static int getLuckLost(ArrayList<Integer> contests, int k) {
        Collections.sort(contests);
        int size = contests.size();
        int toLoose = Math.max(0, size - k);
        int luckLost = 0;

        for (int i = 0; i < toLoose; i++) {
            //Multiply by 2 because we previously added it to the luck sum
            luckLost -= contests.get(i) * 2;
        }
        return luckLost;
    }
}