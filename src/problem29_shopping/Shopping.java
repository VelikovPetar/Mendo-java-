package problem29_shopping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by petar on 7/2/16.
 */
public class Shopping {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int D = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            list.add(in.nextInt());
        }
        Collections.sort(list);
        double sum = 0;
        int third = N / 3;
        for(int i = 0; i < N; ++i) {
            if(N - i <= third) {
                sum += (double) list.get(i) * (100 - D) / 100;
            } else {
                sum += (double) list.get(i);
            }
        }
        System.out.println(String.format("%.2f", sum));
    }
}
