package problem23_rnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by petar on 6/30/16.
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
        if(end >= memo.length || start >= memo.length) {
            return 0;
        }
        if(memo[start][end] != -1) {
            return memo[start][end];
        }
        if(end - start < 2) {
            memo[start][end] = 0;
            return 0;
        }
        long max = 0;
        max = getInRange(start + 1, end);
        for(int i = 2; i < end - start + 1; ++i) {
            if(canBond(sequence[start], sequence[start + i])) {
                long tmp = getInRange(start + 1, start + i - 1) + getInRange(start + i + 1, end) + 1;
                max = Math.max(max, tmp);
            }
        }
        memo[start][end] = max;
        return max;
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int l = Integer.parseInt(br.readLine());
            String input = br.readLine();
            sequence = input.toCharArray();
            memo = new long[l][l];
            for(int i = 0; i < l; ++i) {
                for(int j = 0; j < l; ++j) {
                    memo[i][j] = -1;
                }
            }
            System.out.println(getInRange(0, l - 1));
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
