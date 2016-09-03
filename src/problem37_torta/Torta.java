package problem37_torta;

import java.util.Scanner;

/**
 * Created by petar on 8/19/16.
 */

class Egg {
    int single, pack;

    public Egg(int pack, int single) {
        this.single = single;
        this.pack = pack;
    }
}

public class Torta {

    static Egg[] eggs;

    public static int solve(int N) {
        int minSinglePrice = Integer.MAX_VALUE;
        int minPackPrice = Integer.MAX_VALUE;
        for(int i = 0; i < eggs.length; ++i) {

            minSinglePrice = Math.min(minSinglePrice, eggs[i].single);
            minPackPrice = Math.min(minPackPrice, eggs[i].pack);
        }
        int cost = 0;
        while(N >= 6) {
            cost += Math.min(minPackPrice, minSinglePrice * 6);
            N -= 6;
        }
        cost += Math.min(minSinglePrice * N, minPackPrice);
        return cost;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int E = in.nextInt();
        eggs = new Egg[E];
        for(int e = 0; e < E; ++e) {
            eggs[e] = new Egg(in.nextInt(), in.nextInt());
        }
        in.close();
        System.out.println(solve(N));
    }
}
