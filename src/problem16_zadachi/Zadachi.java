package problem16_zadachi;

import java.util.Scanner;

/**
 * Created by petar on 5/31/16.
 */
public class Zadachi {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        in.close();
        int days = 0;
        int tasks_daily = 0;
        int[] D = new int[N+1];
        if(N < 5)
            tasks_daily = 5;
        else {
            D[0] = D[1] = D[2] = 0;
            D[3] = D[4] = 5;
            D[5] = 9;
            for (int i = 5; i < N + 1; ++i) {
                D[i] = Math.max(D[i - 1], Math.max(D[i - 3] + 5, D[i - 5] + 9));
            }
            tasks_daily = D[N];
        }
        days = (int) Math.ceil((double) K / tasks_daily);
        System.out.println(days);
    }
}
