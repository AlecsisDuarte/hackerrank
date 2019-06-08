
/**
 * Name: Special Palindrome Again
 * 
 * Problem: A string is said to be a special palindromic string if either of 
 *  two conditions is met:
 *   -All of the characters are the same, e.g. aaa.
 *   -All characters except the middle one are the same, e.g. aadaa.
 *  A special palindromic substring is any substring of a string which meets 
 *  one of those criteria. Given a string, determine how many special 
 *  palindromic substrings can be formed from it.
 *  
 *  For example, given the string s = mnonopoo, we have the following special 
 *  palindromic substrings: {m, n, o, n, o, p, o, o, non, ono, opo, oo}.
 * 
 * Input Format: The first line contains an integer, n, the length of s. 
 *  The second line contains the string s.
 * 
 * Constraints: 
 *  1 <= n <= 10^6
 *  Each character of the string is a lowercase alphabet, ascii[a-z]  
 * 
 * Output Format: Print a single line containing the count of total special 
 *  palindromic substrings.
 * 
 * Sample: 
 *   Input: 
 *     5
 *     asasd
 * 
 *   Output: 
 *     7
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class SpecialPalindromeAgain {
    private static class Letter {
        char key;
        int times;

        public Letter(char key, int times) {
            this.key = key;
            this.times = times;
        }
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int len = in.nextInt();
        in.nextLine();

        final String line = in.nextLine();
        long totalPalindromics = getTotalPalindromics(line);
        System.out.println(totalPalindromics);

        in.close();
    }

    private static long getTotalPalindromics(String line) {
        int len = line.length();
        HashMap<Integer, Long> freqVal = fillFrequencyValue(len);

        ArrayList<Letter> letters = new ArrayList<Letter>();
        char c = line.charAt(0);
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (c == line.charAt(i)) {
                count++;
            } else {
                letters.add(new Letter(c, count));
                c = line.charAt(i);
                count = 1;
            }
        }
        letters.add(new Letter(c, count));

        long totalPalindromics = 0;
        if (letters.size() >= 3) {
            Iterator<Letter> iterator = letters.iterator();

            Letter prev = iterator.next();
            totalPalindromics += freqVal.get(prev.times);
            Letter curr = iterator.next();
            
            Letter last = null;
            do {
                last = iterator.next();

                totalPalindromics += freqVal.get(curr.times);
                
                if (curr.times == 1 && prev.key == last.key) {
                    int min = Math.min(prev.times, last.times);
                    totalPalindromics += min;
                }
                prev = curr;
                curr = last;
            } while (iterator.hasNext());
            totalPalindromics += freqVal.get(last.times);

        } else {
            for (Letter letter : letters) {
                totalPalindromics += freqVal.get(letter.times);
            }
        }

        return totalPalindromics;
    }

    //Store how many palindromics you could get from N number of the same
    //character
    private static HashMap<Integer, Long> fillFrequencyValue(int max) {
        HashMap<Integer, Long> freqVal = new HashMap<Integer, Long>(max);
        long sum = 0;
        for (int i = 1; i <= max; i++) {
            sum += i;
            freqVal.put(i, sum);
        }
        return freqVal;
    }
}