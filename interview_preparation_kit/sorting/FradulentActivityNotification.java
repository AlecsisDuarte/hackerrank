
/**
 * Name: Fradulent Activity Notifications
 * 
 * Problem: HackerLand National Bank has a simple policy for warning clients about possible 
 *  fraudulent account activity. If the amount spent by a client on a particular day is greater 
 *  than or equal to 2x the client's median spending for a trailing number of days, they send the 
 *  client a notification about potential fraud. The bank doesn't send the client any notifications 
 *  until they have at least that trailing number of prior days' transaction data.
 *  
 *  Given the number of trailing days d and a client's total daily expenditures for a period of n 
 *  days, find and print the number of times the client will receive a notification over all n days.
 *  For example, d = 3 and expenditures = [10, 20, 30, 40, 50]. On the first three days, they just 
 *  collect spending data. At day 4, we have trailing expenditures of [10, 20, 30]. The median is 
 *  20 and the day's expenditur is 40. Because 40 >= 2x20, there will be a notice. The next day, 
 *  our trailing expenditures are [20, 30, 40] and the expenditures are 50. This is less than 2x30 
 *  so no notice will be sent. Over the period, there was one notice sent. 
 * 
 *  Note: The median of a list of numbers can be found by arranging all the numbers from smallest 
 *      to greatest. If there is an odd number of numbers, the middle one is picked. If there is an 
 *      even number of numbers, median is then defined to be the average of the two middle values. 
 * 
 * Input Format: The first line contains two space-separated integers n and d, the number of days 
 *  of transaction data, and the number of trailing days' data used to calculate median spending. 
 *  The second line contains n space-separated non-negative integers where each integer i denotes 
 *  expenditure[i].
 * 
 * Constraints: 
 *     1 <= n <= 2x10^5
 *     1 <= d <= n
 *     0 <= expenditure[i] <= 200
 * 
 * Output Format: Print an integer denoting the total number of times the client receives a 
 *  notification over a period of n days.
 * 
 * Sample:
 *     Input:
 *         9 5
 *         2 3 4 2 3 6 8 4 5
 *     Output:
 *         2
 * 
 * Reference(s): 
 *  https://www.hackerrank.com/challenges/fraudulent-activity-notifications/forum/comments/348845
 *  https://www.geeksforgeeks.org/counting-sort/
 */
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class FradulentActivityNotification {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int d = Integer.parseInt(data[1]);
        short[] expenditures = new short[n];
        data = in.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            expenditures[i] = Short.parseShort(data[i]);
        }
        int notifications = getNotifications(expenditures, d);
        System.out.println(notifications);
        in.close();
    }

    private static int getNotifications(short[] expenses, int daysBack) {
        int middle = (daysBack / 2) + 1;
        boolean isOdd = daysBack % 2 != 0;
        int[] freq = new int[201];
        
        for (short num : Arrays.copyOf(expenses, daysBack)) {
            ++freq[num];
        }

        int count = 0;
        for (int i = daysBack; i < expenses.length; i++) {
            short maxExpense = getMaxExpense(freq, daysBack, isOdd, middle);
            if (expenses[i] >= maxExpense) {
                ++count;
            }
            --freq[expenses[i - daysBack]];
            ++freq[expenses[i]];
            
        }
        return count;
    }

    private static short getMaxExpense(int[] freq, int len, boolean isOdd, int middle) {
        int[] count = new int[201];
        count[0] = freq[0];
        for (short i = 1; i < freq.length; i++) {
            count[i] = count[i - 1] + freq[i];
        }

        short i = 0, a = 0, b = 0;
        if (isOdd) {
            for (; i < 201; i++) {
                if (middle <= count[i]) {
                    a = (short)(i * 2);
                    break;
                }
            }
        } else {
            int prev = middle - 1;
            for (; i < 201; i++) {
                if (prev <= count[i]) {
                    a = i;
                    break;
                }
            }

            for (; i < 201; i++) {
                if (middle <= count[i]) {
                    b = i;
                    break;
                }
            }
        }
        return (short)(a + b);
    }
}