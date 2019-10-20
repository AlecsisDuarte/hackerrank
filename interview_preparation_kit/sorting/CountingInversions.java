
/**
 * Name: Merge Sort: Counting Inversions
 * 
 * Problem: In an array, arr, the elements at indices i and j (where i < j) form an inversion if 
 *  arr[i] > arr[j]. In other words, inverted elements arr[i] and arr[j] are considered to be "out 
 *  of order". To correct an inversion, we can swap adjacent elements.
 *  
 *  For example, consider the dataset arr = [2,4,1]. It has two inversions: (4,1) and (2,1). To 
 *  sort the array, we must perform the following two swaps to correct the inversions:
 * 
 *      arr = [2,4,1] ----- [ swap(arr[1], arr[2]) -> swap(arr[0], arr[1]) ] -----> [1,2,4]
 * 
 *  Given d datasets, print the number of inversions that must be swapped to sort each dataset on a 
 *  new line.
 * 
 * Input Format: The first line contains an integer, d, the number of datasets.
 *  Each of the next d pairs of lines is as follows:
 *      1. The first line contains an integer, n, the number of elements in arr.
 *      2. The second line contains n space-separated integers, arr[i].
 * 
 * Constraints: 
 *     1 <= d <= 15
 *     1 <= n <= 10^5
 *     1 <= arr[i] <= 10^7
 * 
 * Output Format: For each of the d datasets, return the number of inversions that must be swapped 
 *  to sort the dataset.
 * 
 * Sample:
 *     Input:
 *         2  
 *         5  
 *         1 1 1 2 2  
 *         5  
 *         2 1 3 1 2
 *     Output:
 *         0
 *         4
 * 
 */
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CountingInversions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        byte d = Byte.parseByte(in.nextLine().trim());
        while (d-- > 0) {
            int n = Integer.parseInt(in.nextLine().trim());
            int[] arr = new int[n];
            String[] data = in.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(data[i].trim());
            }
            long swaps = mergeSort(arr);
            System.out.println(swaps);

        }
        in.close();
    }
    private static long mergeSort(int[] arr) {
        long swaps = mergeSort(arr, new int[arr.length], 0, arr.length - 1);
        return swaps;
    }

    private static long mergeSort(int[] arr, int[] tmp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return 0;
        }
        int middle = (leftStart + rightEnd) / 2;
        long count = mergeSort(arr, tmp, leftStart, middle);
        count += mergeSort(arr, tmp, middle+1, rightEnd);
        count += mergeHalves(arr, tmp, leftStart, rightEnd);
        return count;
    }

    private static long mergeHalves(int[] arr, int[] tmp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;
        long swaps = 0L;

        while (left <= leftEnd && right <= rightEnd) {
            if (arr[left] <= arr[right]) {
                tmp[index] = arr[left];
                ++left;
            } else {
                tmp[index] = arr[right];
                swaps += right - index;
                ++right;
            }
            ++index;
        }

        System.arraycopy(arr, left, tmp, index, leftEnd - left + 1);
        System.arraycopy(arr, right, tmp, index, rightEnd - right + 1);
        System.arraycopy(tmp, leftStart, arr, leftStart, size);
        return swaps;
    }
}