import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Name: String Construction
 * 
 * Problem: Amanda has a string of lowercase letters that she wants to copy to a
 * new string. She can perform the following operations with the given costs.
 * She can perform them any number of times to construct a new string p: -
 * Append a character to the end of string at a cost of dollar. - Choose any
 * substring of and append it to the end of at no charge.
 * 
 * Given n strings s[i], find and print the minimum cost of copying each s[i] to
 * p[i] on a new line.
 * 
 * For example, given a string s = abcabc, it can be copied for 3 dollars. Start
 * by copying a, b and c individually at a cost of 1 dollar per character.
 * String p = abc at this time. Copy p[0 : 2] to the end of p at no cost to
 * complete the copy.
 * 
 * Input Format: The first line contains a single integer n, the number of
 * strings. Each of the next n lines contains a single string, s[i].
 * 
 * Constraints: 1 <= n <= 5 1 < |s[i]| <= 10^5
 * 
 * Output Format: For each string s[i] print the minimum cost of constructing a
 * new string p[i] on a new line.
 * 
 * Sample: Input: 2 abcd abab
 * 
 * Output: 4 2
 * 
 */
public class StringConstruction {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();
        in.nextLine();
        while (num-- > 0) {
            String line = in.nextLine();
            HashSet<Character> letters = new HashSet<Character>(line.length());

            for (int index = 0; index < line.length(); index++) {
                letters.add(line.charAt(index));
            }
            int dollars = letters.size();
            System.out.println(String.valueOf(dollars));
        }
        in.close();
    }
}