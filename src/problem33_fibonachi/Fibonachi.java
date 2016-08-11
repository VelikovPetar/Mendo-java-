package problem33_fibonachi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by petar on 8/11/16.
 */
public class Fibonachi {

    static Set<Integer> fibonacci;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int r = in.nextInt();
        in.close();
        int f1 = 1;
        int f2 = 1;
        fibonacci = new HashSet<>();
        while(f2 < 20000) {
            fibonacci.add(f2);
            int next = f1 + f2;
            f1 = f2;
            f2 = next;
        }
        int ans = 0;
        for(int i = l; i <= r; ++i) {
            if(fibonacci.contains(i)) {
                ans ++;
            }
        }
        System.out.println(ans);
    }
}
