package problem17_stranica;

import java.util.*;

/**
 * Created by petar on 5/31/16.
 */
public class Stranica {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int finish = in.nextInt();
        int num_broken = in.nextInt();
        Set<Integer> broken = new HashSet<>();
        for(int i = 0; i < num_broken; ++i) {
            broken.add(in.nextInt());
        }
        int[] D = new int[100001];
        D[100] = 0;
        for(int i = 0; i < 10; ++i) {
            if(!broken.contains(i)) {
                D[i] = 1;
            }
        }
        int opt = 1;
        for(int i = 10; i < 100001; ++i) {
            if(i == 100) {opt = 2; continue;}
            if(i == 1000) opt = 3;
            if(i == 10000) opt = 4;
            if(!broken.contains(i % 10) && D[i / 10] == opt) {
                D[i] = Math.min(opt, D[i - 1]) + 1;
            } else {
                D[i] = D[i - 1] + 1;
            }
        }
        for(int i = 99999; i >= 10; --i) {
            if(i == 10000) opt = 3;
            if(i == 1000) opt = 2;
            if(i == 100) {opt = 1; continue;}
            if(!broken.contains(i % 10) && D[i / 10] == opt) {
                D[i] = Math.min(opt, D[i - 1]) + 1;
            } else {
                D[i] = Math.min(D[i - 1],D[i + 1]) + 1;
            }
        }
        System.out.println(D[finish]);
    }
}
