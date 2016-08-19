package problem38_potoci;

import java.util.*;

/**
 * Created by petar on 8/19/16.
 */
public class Potoci {

    static Set<Integer> potok1;
    static Set<Integer> potok3;
    static Set<Integer> potok9;

    public static int sumOfDigits(long N) {
        int sum = 0;
        while(N > 0) {
            sum += N % 10;
            N /= 10;
        }
        return sum;
    }

    public static void solve(int init) {
        while(init < 100000) {
            if(potok1.contains(init)) {
                System.out.println("1 " + init);
                return;
            } else if(potok3.contains(init)) {
                System.out.println("3 " + init);
                return;
            } else if(potok9.contains(init)) {
                System.out.println("9 " + init);
                return;
            } else {
                init += sumOfDigits(init);
            }
        }
    }

    public static void generatePotoci() {
        int init = 1;
        while(init < 100000) {
            potok1.add(init);
            init += sumOfDigits(init);
        }
        init = 3;
        while(init < 100000) {
            potok3.add(init);
            init += sumOfDigits(init);
        }
        init = 9;
        while(init < 100000) {
            potok9.add(init);
            init += sumOfDigits(init);
        }
    }

    public static void main(String[] args) {
        potok1 = new HashSet<>();
        potok3 = new HashSet<>();
        potok9 = new HashSet<>();
        generatePotoci();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.close();
        solve(N);
    }
}
