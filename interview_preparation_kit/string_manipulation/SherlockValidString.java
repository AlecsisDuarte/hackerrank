
/**
 * Name: Sherlock and the Valid String
 * 
 * Problem: Sherlock considers a string to be valid if all characters of the 
 *  string appear the same number of times. It is also valid if he can remove 
 *  just 1 character at 1 index in the string, and the remaining characters 
 *  will occur the same number of times. Given a string s, determine if it is 
 *  valid. If so, return YES, otherwise return NO.
 *  
 *  For example, if s = abc, it is a valid string because frequencies are 
 *  {a: 1, b: 1, c: 1}. So is s = abcc because we can remove one c and have 1 
 *  of each character in the remaining string. If s = abccc however, the string 
 *  is not valid as we can only remove 1 occurrence of c. That would leave 
 *  character frequencies of {a: 1, b: 1, c: 2}.
 * 
 * Input Format: A single string s.
 * 
 * Constraints: 
 *  1 <= |s| <= 10^5
 *  Each character s[i] is ascii[a-z]
 * 
 * Output Format: Print YES if string  is valid, otherwise, print NO.
 * 
 * Sample: 
 *   Input: 
 *     abcdefghhgfedecba
 * 
 *   Output: 
 *     YES
 * 
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

public class SherlockValidString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        int[] letters = new int['z' - 'a' + 1];
        for (int index = 0; index < line.length(); index++) {
            int pos = line.charAt(index) - 'a';
            letters[pos]++;
        }

        HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
        for (int index = 0; index < letters.length ; index++) {
            if (letters[index] == 0) continue;
            if ( index > 0 && Math.abs(letters[index-1] - letters[index]) > 1) {
                System.out.println("NO");
                return;
            }
            int frequency = frequencies.getOrDefault(letters[index], 0);
            frequencies.put(letters[index], frequency + 1);
        }

        if (frequencies.size() == 1) {
            System.out.println("YES");
        } else if (frequencies.size() > 2) {
            System.out.println("NO");
        } else {
            Iterator<Entry<Integer, Integer>> entries = frequencies.entrySet().iterator();
            Entry<Integer, Integer> freqX = entries.next();
            Entry<Integer, Integer> freqY = entries.next();

            if (freqX.getValue() > 1 && freqY.getValue() > 1) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
        in.close();
    }
}