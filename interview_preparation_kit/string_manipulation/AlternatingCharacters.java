
/**
 * Name: Alternating Characters
 * 
 * Problem: You are given a string containing characters A and B only. Your 
 *  task is to change it into a string such that there are no matching adjacent 
 *  characters. To do this, you are allowed to delete zero or more characters 
 *  in the string.
 *  Your task is to find the minimum number of required deletions.
 *  For example, given the string s = AABAAB, remove an A at positions 0 and 3 
 *  to make s = ABAB in 2 deletions.
 * 
 * Input Format: The first line contains an integer q, the number of queries. 
 *  The next q lines each contain a string s.
 * 
 * Constraints: 
 *  1 <= q <= 10
 *  1 <= |s| <= 10^5
 *  Each string will consist only of characters A and B
 * 
 * Output Format: For each query, print the minimum number of deletions 
 *  required on a new line.
 * 
 * Sample: 
 *   Input: 
 *      5
 *      AAAA
 *      BBBBB
 *      ABABABAB
 *      BABABA
 *      AAABBB
 *     
 * 
 *   Output: 
 *      3
 *      4
 *      0
 *      0
 *      4
 * 
 */
import java.util.Scanner;

public class AlternatingCharacters {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        byte tests = in.nextByte();
        in.nextLine(); //Remove remaining line
        while (tests-- > 0) {
            String line = in.nextLine();
            int deletions = minDeletions(line);
            System.out.println(deletions);
        }
        in.close();
    }

    private static int minDeletions(String line) {
        int deletions = 0;
        if (line.length() == 1) {
            return deletions;
        }
        char prev = line.charAt(0);
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == prev) {
                deletions++;
            } else {
                prev = line.charAt(i);
            }
        }
        return deletions;
    }
}