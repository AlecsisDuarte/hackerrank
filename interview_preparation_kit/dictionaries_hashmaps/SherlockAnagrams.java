
/**
 * Name: Sherlock and Anagrams
 * 
 * Problem: Two strings are anagrams of each other if the letters of one string can be rearranged 
 *  to form the other string. Given a string, find the number of pairs of substrings of the string 
 *  that are anagrams of each other.
 *  For example s = mom, the list of all anagrammatic pairs is [m,m], [mo, om] at positions 
 *  [[0], [2]],[[0,1],[1,2]] respectively.
 * 
 * Input Format: The first line contains an integer q, the number of queries. 
 *  Each of the next q lines contains a string s to analyze.
 * 
 * Constraints: 
 *     1 <= q <= 10
 *     2 <= |s| <= 100
 *     String s contains only lowercase letters (ascii[a-z]).
 * 
 * Output Format: For each query, return the number of unordered anagrammatic pairs.
 * 
 * Sample:
 *     Input:
 *         2
 *         abba
 *         abcd
 *     Output:
 *         4
 *         0
 * 
 */
import java.util.Scanner;

public class SherlockAnagrams {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        short queries = in.nextShort();
        in.nextLine();// Clean current line (queries line);
        while (queries-- > 0) {
            final String line = in.nextLine();
            final int anagrams = findSubstringAnagrams(line);
            System.out.println(anagrams);
        }
        
        in.close();
    }

    private static int findSubstringAnagrams(String line) {
        int anagrams = 0;

        //We create all posible substring starting with the max length
        short len = (short)line.length();
        while (--len > 0) {
            short max = (short)(((short)line.length()) - len);
            for (short i = 0; i < max; i++) {
                String s1 = line.substring(i, i + len);
                short j = (short)(i + 1);
                for (; j <= max; j++) {
                    String s2 = line.substring(j, j + len);
                    if (anagramStrings(s1, s2) ) {
                        anagrams++;
                    }
                }
            }
        }

        return anagrams;
    }

    private static boolean anagramStrings(String s1, String s2) {
        //26 is the length from a - z
        short[] frequencies = new short[26];

        //Store the characters frequency
        for (short i = 0; i < s1.length(); i++) {
            short pos = (short)(s1.charAt(i) - 'a');
            frequencies[pos]++;
            pos = (short)(s2.charAt(i) - 'a');
            frequencies[pos]--;
        }
        for (short i = 0; i < frequencies.length; i++) {
            if (frequencies[i] != 0) {
                return false;
            }
        }
        return true;
    }
}