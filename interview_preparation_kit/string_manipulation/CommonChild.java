
/**
 * Problem: A string is said to be a child of a another string if it can be formed by deleting 0 or 
 * 	more characters from the other string. Given two strings of equal length, what's the longest 
 * 	string that can be constructed such that it is a child of both?
 *	For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD. They can be 
 *	formed by eliminating either the D or C from both strings. Note that we will not consider ABCD 
 *	as a common child because we can't rearrange characters and ABCD != ABDC.
 * 
 * Input Format: There is one line with two space-separated strings, s1 and s2.
 * 
 * Constraints: 
 *     1 <= |s1|,|s2| <= 5000
 *     All characters are upper case in the range ascii[A-Z].
 * 
 * Output Format: Print the length of the longest string s, such that s is a child of both s1 and s2.
 * 
 * Sample:
 *     Input:
 *         HARRY
 *         SALLY
 *     Output:
 *         2
 * 
 * Reference: https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
 */
import java.util.Scanner;

public class CommonChild {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s1 = in.nextLine();
		String s2 = in.nextLine();
		short[][] table = createComparizonTable(s1, s2);
		System.out.println(table[table.length -1][table[0].length -1]);
		in.close();
	}

	private static short[][] createComparizonTable(String s1, String s2) {
		// We add a prefix to avoid out of range exception
		s1 = "x" + s1;
		s2 = "x" + s2;
		short[][] table = new short[s1.length()][s2.length()];
		for (short i = 1; i < s1.length(); i++) {
			for (short j = 1; j < s2.length(); j++) {
				//Everytime we find an equal character we increase the value
				if (s1.charAt(i) == s2.charAt(j)) {
					table[i][j] = table[i - 1][j - 1];
					++table[i][j];
				} else {
					table[i][j] = ((short) Math.max(table[i - 1][j], table[i][j - 1]));
				}
			}
		}
		return table;
	}
}
