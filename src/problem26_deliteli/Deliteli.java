package problem26_deliteli;

import java.util.Scanner;

/**
 * Created by petar on 7/2/16.
 */
public class Deliteli {

    private static int getNumberOfDeliteli(int x) {
        int numDeliteli = 0;
        for(int i = 1; i <= Math.sqrt(x); ++i) {
            if(x % i == 0) {
                numDeliteli += 2;
                if(i * i == x) numDeliteli -= 1;
            }
        }
        return numDeliteli;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int maxDeliteli = 0;
        int number = 0;
        for(int i = x; i <= y; ++i) {
            int numDeliteli = getNumberOfDeliteli(i);
            if(numDeliteli > maxDeliteli) {
                maxDeliteli = numDeliteli;
                number = i;
            }
        }
        System.out.println(String.format("%d %d", number, maxDeliteli));
    }
}
