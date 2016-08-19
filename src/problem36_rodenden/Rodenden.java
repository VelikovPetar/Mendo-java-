package problem36_rodenden;

import java.util.Scanner;

/**
 * Created by petar on 8/19/16.
 */
public class Rodenden {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String years = in.nextLine();
        in.close();
        int[] count = new int[10];
        for(char c : years.toCharArray()) {
            int i = c - '0';
            if(i == 9) {
                count[6]++;
            } else {
                count[i]++;
            }
        }
        count[6] += 1;
        count[6] /= 2;
        int max = -1;
        for(int i = 0; i < 10; ++i) {
            max = Math.max(max, count[i]);
        }
        System.out.println(max);
    }
}
