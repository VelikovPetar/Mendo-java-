package problem23_rnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by petar on 6/30/16.
 */

/**
 * TODO: Works on 6/10 test cases
 */
public class RNA {

    private static char[] sequence;
    private static long[][] memo;

    private static boolean canBond(char a, char b) {
        if(a == 'A' && b == 'U') return true;
        if(a == 'U' && b == 'A') return true;
        if(a == 'C' && b == 'G') return true;
        if(a == 'G' && b == 'C') return true;
        if(a == 'G' && b == 'U') return true;
        if(a == 'U' && b == 'G') return true;
        return false;
    }

    private static long getInRange(int start, int end) {
        if(start >= end) return 0;
        if(end - start < 2) {
            if(end > start) return getInRange(end, start);
            if(end > memo.length - 1 || start < 0 || end < 0 || start > memo.length - 1) return 0;
            return memo[start][end];
        }
        if(memo[start][end] != 0) {
            return memo[start][end];
        }
        long p1;
        if(memo[start+1][end-1] == 0) {
            memo[start + 1][end - 1] = getInRange(start + 1, end - 1);
        }
        p1 = memo[start + 1][end - 1];
        if(canBond(sequence[start], sequence[end])) {
            p1 = memo[start][end] = memo[start + 1][end - 1] + 1;
        }
        long p2;
        if(memo[start+1][end] == 0) {
            memo[start + 1][end] = getInRange(start + 1, end);
        }
        p2 = memo[start + 1][end];
        long p3;
        if(memo[start][end - 1] == 0) {
            memo[start][end - 1] = getInRange(start, end - 1);
        }
        p3 = memo[start][end - 1];
        memo[start][end] = Math.max(p1, Math.max(p2, p3));
        return memo[start][end];

    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int l = Integer.parseInt(br.readLine());
            String input = br.readLine();
            sequence = input.toCharArray();
            memo = new long[l][l];
            // TODO
            long maxBonds = 0;
            for(int start = 0; start < l; ++start) {
                for(int end = l - 1; end >= 0; --end) {
                    long tmp;
                    if(end <= start) tmp = 0;
                    else tmp = getInRange(0, start - 1) + getInRange(start, end) + getInRange(end + 1, l - 1);
                    if(tmp > maxBonds) {
                        maxBonds = tmp;
                    }
                }
            }
            System.out.println(maxBonds);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
