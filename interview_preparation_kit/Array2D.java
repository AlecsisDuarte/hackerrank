
/**
 * Name: 2D Array - DS
 * 
 * Problem: Given a 6 X 6  2D Array, arr:
 *  1 1 1 0 0 0
 *  0 1 0 0 0 0
 *  1 1 1 0 0 0
 *  0 0 0 0 0 0
 *  0 0 0 0 0 0
 *  0 0 0 0 0 0
 * 
 *  We define an hourglass in A to be a subset of values with indices falling in this pattern in 
 *  arr's graphical representation:
 *  a b c
 *    d
 *  e f g
 * 
 *  There are 16 hourglasses in arr, and an hourglass sum is the sum of an hourglass' values. 
 *  Calculate the hourglass sum for every hourglass in arr, then print the maximum hourglass sum.
 *  
 *  For example, given the 2D array: 
 *  -9 -9 -9  1 1 1 
 *   0 -9  0  4 3 2
 *  -9 -9 -9  1 2 3
 *   0  0  8  6 6 0
 *   0  0  0 -2 0 0
 *   0  0  1  2 4 0
 * 
 *  We calculate the following 16 hourglass values: 
 *  -63, -34, -9, 12, 
 *  -10, 0, 28, 23, 
 *  -27, -11, -2, 10, 
 *  9, 17, 25, 18
 * 
 *  Our highest hourglass value is 28 from the hourglass:
 *  0 4 3
 *    1
 *  8 6 6
 * 
 * Input Format: Each of the 6 lines of inputs arr[i][j] contains 6 space-separated integers arr[i][j].
 * 
 * Constraints:
 *  -9 <= arr[i][j] <= 9
 *  0 <= i,j <= 5
 * 
 * Output Format: Print the largest (maximum) hourglass sum found in arr.
 * 
 * Sample:
 *  Input:
 *      1 1 1 0 0 0
 *      0 1 0 0 0 0
 *      1 1 1 0 0 0
 *      0 0 2 4 4 0
 *      0 0 0 2 0 0
 *      0 0 1 2 4 0
 * 
 *  Output: 
 *      19
 */

import java.util.Scanner;

public class Array2D {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte[][] numbers = new byte[6][6];
        for (byte y = 0; y < 6; y++) {
            for (byte x = 0; x < 6; x++) {
                numbers[y][x] = scan.nextByte();
            }
        }
        byte maxValue = getMaxHourglassValue(numbers);
        System.out.println(maxValue);;
        scan.close();
    }

    private static byte getHourglassValue(byte[][] square) {
        byte sum = 0;
        for (byte y = 0; y < 3; y++) {
            for (byte x = 0; x < 3; x++) {
                if (y == 1 && x != 1) {
                    continue;
                }
                sum += square[y][x];
            }
        }
        return sum;
    }

    private static byte getMaxHourglassValue(byte[][] numbers) {
        byte max = -63;
        for (byte y = 0; y < 4; y++) {
            for (byte x = 0; x < 4; x++) {
                byte[][] square = new byte[3][3];
                for (byte sqrY = 0; sqrY < 3; sqrY++) {
                    for (byte sqrX = 0; sqrX < 3; sqrX++) {
                        square[sqrY][sqrX] = numbers[y + sqrY][x + sqrX];
                    }
                }
                byte hourglassValue = getHourglassValue(square);
                if (hourglassValue > max) {
                    max = hourglassValue;
                }
            }
        }
        return max;
    }
}