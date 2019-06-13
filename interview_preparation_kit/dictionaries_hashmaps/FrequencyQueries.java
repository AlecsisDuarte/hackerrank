
/**
 * Name: Frequency Queries
 * 
 * Problem: You are given q queries. Each query is of the form two integers described below: 
 *  - 1:x Insert x in your data structure. 
 *  - 2:y Delete one occurence of y from your data structure, if present. 
 *  - 3:z Check if any integer is present whose frequency is exactly z. If yes, print 1 else 0.
 *  
 *  The queries are given in the form of a 2-D array queries of size q where queries[i][0] contains 
 *  the operation, and queries[i][1] contains the data element. 
 *  For example, you are given array queries = [(1,1),(2,2),(3,2),(1,1),(1,1),(2,1),(3,2)]. 
 *  The results of each operation are:
 *  
 *    Operation   Array   Output
 *    (1,1)       [1]
 *    (2,2)       [1]
 *    (3,2)                   0
 *    (1,1)       [1,1]
 *    (1,1)       [1,1,1]
 *    (2,1)       [1,1]
 *    (3,2)                   1
 *  Return an array with the output: [0,1].
 * 
 *  Input Format: The first line contains of an integer q, the number of queries. 
 *  Each of the next q lines contains two integers denoting the 2-d array queries.
 * 
 * Constraints: 
 *     1 <= q <= 10^6
 *     1 <= x,y,z <= 10^9
 *     All queries[i][0] {1, 2, 3}
 *     1 <= queries[i][1] <= 10^9
 * 
 * Output Format: Return an integer array consisting of all the outputs of queries of type 3.
 * 
 * Sample:
 *     Input:
 *         8
 *         1 5
 *         1 6
 *         3 2
 *         1 10
 *         1 10
 *         1 6
 *         2 5
 *         3 2
 *     Output:
 *         0
 *         1
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyQueries {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Boolean> ans = freqQuery(queries);
            StringBuilder res = new StringBuilder();
            ans.forEach((a) -> res.append(a ? "1\n" : "0\n"));
            // System.out.printf(res.toString());
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bufferedWriter.write(res.toString());
            }
        }
    }

    static List<Boolean> freqQuery(List<int[]> queries) {
        HashMap<Integer, Long> arr = new HashMap<>();
        HashMap<Long, Integer> freq = new HashMap<>();

        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int op = q[0];
            int val = q[1];
            long currVal;

            switch (op) {
            case 1:
                currVal = arr.compute(val, (k, v) -> v == null ? 1L : v + 1L);
                freq.compute(currVal, (k, v) -> v == null ? 1 : v + 1);
                if (currVal > 1) {
                    freq.compute(currVal - 1, (k, v) -> (v - 1));
                }
                break;
            case 2:
                currVal = arr.getOrDefault(val, 0L);
                if (currVal > 0L) {
                    arr.put(val, currVal - 1L);
                    freq.compute(currVal, (k, v) -> v - 1);
                    if (currVal > 1L) {
                        freq.compute(currVal - 1L, (k, v) -> v + 1);
                    }
                }
                break;
            case 3:
                res.add(freq.getOrDefault((long) val, 0) == 0 ? false : true);
                break;
            }
        }
        return res;
    }
}