package problem35_chasovnik;

import java.util.Scanner;

/**
 * Created by petar on 8/19/16.
 */
public class Chasovnik {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int startH = in.nextInt();
        int startM = in.nextInt();
        int endH = in.nextInt();
        int endM = in.nextInt();
        int S = startH * 60 + startM;
        int E = endH * 60 + endM;
        if(endH < startH) {
            E += 24 * 60;
        }
        if(startH == endH && startM > endM) {
            E += 24 * 60;
        }
        int ans = Math.abs(E - S);
        int h = ans / 60;
        int min = ans % 60;
        System.out.printf("%02d:%02d", h, min);
    }
}
