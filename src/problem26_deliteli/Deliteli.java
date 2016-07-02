package problem26_deliteli;

import java.util.Scanner;

/**
 * Created by petar on 7/2/16.
 */
public class Deliteli {

    private static int getNumberOfDivisors(int x) {
        int numDiv = 0;
        for(int i = 1; i <= Math.sqrt(x); ++i) {
            if(x % i == 0) {
                numDiv += 2;
                if(i * i == x) numDiv -= 1;
            }
        }
        return numDiv;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int maxDiv = 0;
        int number = 0;
        for(int i = x; i <= y; ++i) {
            int numDeliteli = getNumberOfDivisors(i);
            if(numDeliteli > maxDiv) {
                maxDiv = numDeliteli;
                number = i;
            }
        }
        System.out.println(String.format("%d %d", number, maxDiv));
    }
}
