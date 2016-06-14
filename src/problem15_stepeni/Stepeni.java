package problem15_stepeni;

import java.util.*;

/**
 * Created by petar on 5/31/16.
 */
public class Stepeni {

    public static Set<Integer> ones;

    public static int getNth(int n) {
        if(ones.contains(n)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        ones = new HashSet<>();
        int o = 1;
        int incr = 0;
        while(o < 10000) {
            o += incr;
            ones.add(o);
            incr ++;
        }
        System.out.println(getNth(n) + " " + getNth(n+1));
    }
}
