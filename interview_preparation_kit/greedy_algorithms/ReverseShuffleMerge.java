
/**
 * Name: Reverse Shuffle Merge
 * 
 * Problem: Given a string, A, we define some operations on the string as follows:
 *  a. reverse(A) denotes the string obtained by reversing string A. Example:  
 *      reverse("abc") = "cba"
 * 
 *  b. shuffle(A) denotes any string that's a permutation of string A. Example:  
 *      shuffle("god") E ['god', 'gdo', 'ogd', 'odg', 'dog']
 *  
 *  c. merge(A) denotes any string that's obtained by interspersing the two strings A1 & A2, 
 *  maintaining the order of characters in both. For example, A1 = "abc" & A2 = "def", one possible 
 *  result of merge(A1,A2) could be "abcdef", another could be "abdecf", another could be "adbecf" 
 *  and so on.
 * 
 *  Given a string s such that s = merge(reverse(A), shuffle(A)) for some string A, find the 
 *  lexicographically smallest a.
 *  For example, s = abab. We can split it into two strings of ab. The reverse is ba and we need to 
 *  find a string to shuffle in to get abab. The middle two characters match our reverse string, 
 *  leaving the a and b at the ends. Our shuffle string needs to be ab. Lexicographically ab < ba, 
 *  so our answer is ab.
 * 
 * Input Format: A single line containing the string s.
 * 
 * Constraints: 
 *     s contains only lower-cae English letters, ascii[a-z]
 *     1 <= |s| <= 10000
 * 
 * Output Format: Find and return the string which is the lexicographically smallest valid A.
 *
 * Sample:
 *     Input:
 *         eggegg
 *     Output:
 *         egg
 * 
 * Reference: https://www.hackerrank.com/challenges/reverse-shuffle-merge/forum/comments/118784?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=greedy-algorithms
 * #Note: I couldn't finish this problem by myself as I never understood the problem/solution
 *  so I just ported that code to here. I'm leaving this in here as a reference or further 
 *  understanding of the problem.
 */
import java.util.Scanner;
import java.lang.StringBuilder;

public class ReverseShuffleMerge {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(smallestValidA(s));
        in.close();

    }

    private static String smallestValidA(String s) {
        int[] frequencies = new int['z' - 'a' + 1];
        char smallestChar = 'z';
        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
            if (smallestChar > s.charAt(i)) {
                smallestChar = s.charAt(i);
            }
        }
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                frequencies[i] /= 2;
            }
        }
        int[] taken = frequencies.clone();
        
        char max = 'z' + 1, bestSeen = max;
        int bestIndex = 0;
        int i = s.length() - 1;
        
        StringBuilder sb = new StringBuilder();
        for (; i >= 0; i--) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (taken[index] == 0) continue;
            if (c > smallestChar) {
                if (frequencies[index] == 0) {
                    if (c < bestSeen) {
                        taken[index]--;
                        sb.append(c);
                        bestSeen = max;
                    } else {
                        while (i < bestIndex) {
                            i++;
                            index = s.charAt(i) - 'a';
                            frequencies[index]++;
                        }
                        taken[index]--;
                        c = s.charAt(i);
                        sb.append(c);
                        bestSeen = max;
                    }
                } else {
                    frequencies[index]--;
                    if (c < bestSeen) {
                        bestSeen = c;
                        bestIndex = i;
                    }
                }
            } else {
                if (taken[index] != 0) {
                    taken[index]--;
                    smallestChar = smallest(taken);
                    sb.append(c);
                    bestSeen = max;
                }
            }
        }
        
        return sb.toString();        
    }

    private static char smallest(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                return (char)(i + 97);
            }
        }
        return 'z';
    }
}