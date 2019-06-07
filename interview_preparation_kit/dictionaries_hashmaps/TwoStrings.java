
/**
 * Name: Two Strings
 * 
 * Problem: Given two strings, determine if they share a common substring. 
 *  A substring may be as small as one character. 
 *  For example, the words "a", "and", "art" share the common substring a. The 
 *  words "be" and "cat" do not share a substring.
 * 
 * Input Format: The first line contains a single integer p, the number of test cases.
 *  The following p pairs of lines are as follows:
 *   - The first line contains string s1.
 *   - The second line contains string s2.
 * 
 * Constraints: 
 *  s1 and s2 consist of characters in the range ascii[a-z]
 *  1 <= p <= 10
 *  1 <= |s1|,|s2| <= 10^5
 * 
 * Output Format: For each pair of strings, return YES or NO.
 * 
 * Sample: 
 *   Input: 
 *     2
 *     hello
 *     world
 *     hi
 *     world
 * 
 *   Output: 
 *     YES
 *     NO
 * 
 */
import java.util.HashSet;
import java.util.Scanner;

public class TwoStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        byte tests = in.nextByte();
        in.nextLine();
        while (tests-- > 0) {
            String response = "NO";
            String line = in.nextLine();
            HashSet<Character> charsSet = new HashSet<Character>(line.length());
            
            for (int i = 0; i < line.length(); i++) {
                charsSet.add(line.charAt(i));
            }

            line = in.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (charsSet.contains(line.charAt(i))) {
                    response = "YES";
                    break;
                }
            }

            System.out.println(response);
        }
        in.close();
    }
}