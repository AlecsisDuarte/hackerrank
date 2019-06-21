
/**
 * Name: Greedy Florist
 * 
 * Problem: A group of friends want to buy a bouquet of flowers. The florist wants to maximize his 
 *  number of new customers and the money he makes. To do this, he decides he'll multiply the price 
 *  of each flower by the number of that customer's previously purchased flowers plus 1. The first 
 *  flower will be original price, (0+1) x original price, the next will be (1+1) x original price 
 *  and so on.
 *  Given the size of the group of friends, the number of flowers they want to purchase and the 
 *  original prices of the flowers, determine the minimum cost to purchase all of the flowers.
 *  For example, if there are k = 3 friends that want to buy n = 4 flowers that cost c = [1,2,3,4] 
 *  each will buy one of the flowers priced [2,3,4] at the original price. Having each purchased 
 *  x = 1 flower, the first flower in the list, c[0], will now cost (current purchase + previouse 
 *  purchase) x c[0] = (1+1)x1 = 2. The total cost will be 2 + 3 + 4 + 2 = 11.
 * 
 * Input Format: The first line contains two space-separated integers n and k, the number of 
 *  flowers and the number of friends. The second line contains n space-separated positive integers 
 *  c[i], the original price of each flower.
 * 
 * Constraints: 
 *     1 <= n,k <= 100
 *     1 <= c[i] <= 10⁶
 *     answer < 2³¹
 *     0 <= i < n
 * 
 * Output Format: Print the minimum cost to buy all n flowers.
 * 
 * Sample:
 *     Input:
 *         3 3
 *         2 5 6
 *     Output:
 *         13
 * 
 */
import java.util.Arrays;
import java.util.Scanner;

public class GreedyFlorist {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(" ");
        byte n = Byte.parseByte(data[0]);
        byte k = Byte.parseByte(data[1]);
        data = in.nextLine().split(" ");
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(data[i]);
        }
        System.out.println(getMinimumCost(k, prices));
        in.close();
    }

    private static long getMinimumCost(byte k, int[] prices) {
        long total = 0;
        Arrays.sort(prices);

        //We go from end to start to add all flowers that would cost the original price
        int middle = prices.length - k;
        int i = prices.length - 1;

        for (; i >= middle; i--) {
            total += prices[i];
        }
        
        //All clients start as their 2nd purchase
        int mul = 2;
        int tmpTimes = 0;
        
        //We add the remaining prices with their respective multiplier
        for (; i >= 0; i--) {
            int toAdd = (prices[i] * mul);
            total += toAdd;
            
            //We increment the multiplier once we ran of the lowes multipliers
            if (++tmpTimes == k) {
                tmpTimes = 0;
                ++mul;
            }
        }


        return total;
    }
}