package problem25_neparenzbir;

import java.util.Scanner;

/**
 * Created by petar on 7/2/16.
 */
public class NeparenZbir {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        long sum = 0;
        for(int i = x; i <= y; ++i) {
            if(i % 2 == 1) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
