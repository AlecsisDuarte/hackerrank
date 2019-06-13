import java.util.Arrays;
import java.util.Scanner;
/**
 * Name: Mark and Toys
 * 
 * Problem: Mark and Jane are very happy after having their first child. Their son loves toys, so 
 *  Mark wants to buy some. There are a number of different toys lying in front of him, tagged with 
 *  their prices. Mark has only a certain amount to spend, and he wants to maximize the number of 
 *  toys he buys with this money. 
 *  Given a list of prices and an amount to spend, what is the maximum number of toys Mark can buy? 
 *  For example, if prices = [1,2,3,4] and Mark has k = 7 to spend, he can buy items [1,2,3] for 6, 
 *  or [3,4] for 7 units of currency. He would choose the first group of 3 items.
 * 
 * Input Format: The first line contains two integers, n and k, the number of priced toys and the 
 *  amount Mark has to spend. 
 *  The next line contains n space-separated integers prices[i].
 * 
 * Constraints: 
 *     1 <= n <= 10^5
 *     1 <= k <= 10^9
 *     1 <= prices[i] <= 10^9
 *     A toy can't be brought multiple times
 *     
 * Output Format: An integer that denotes the maximum number of toys Mark can buy for his son.
 * 
 * Sample:
 *     Input:
 *         7 50
1 12 5 111 200 1000 10
 *     Output:
 *         4
 * 
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkAndToys {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        in.nextLine();
        int[] prices = new int[n];
        Pattern p = Pattern.compile("(\\d+)");
        String[] nums = in.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(nums[i]);
        }
        int max = maxToys(prices, k);
        System.out.println(max);
        in.close();
    }

    private static int maxToys(int[] prices, int cash) {
        Arrays.sort(prices);

        int count = 0;
        int price = 0;

        for (int i = 0; i < prices.length; i++ ) {
            price += prices[i];
            if (price > cash) {
                break;
            }
            ++count;
        }

        return count;
    }
}