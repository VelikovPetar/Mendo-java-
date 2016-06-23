package problem19_izbori;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * Created by petar on 6/23/16.
 */

class Krava implements Comparable<Krava> {
    int index, k1, k2;

    @Override
    public int compareTo(Krava o) {
        return Integer.compare(this.k1, o.k1) * -1;
    }

    Krava(int index, int k1, int k2) {
        this.index = index;
        this.k1 = k1;
        this.k2 = k2;
    }
}

public class Izbori {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            String[] info = line.split("\\s+");
            int n = Integer.parseInt(info[0]);
            int k = Integer.parseInt(info[1]);
            PriorityQueue<Krava> pq = new PriorityQueue<>();
            for(int i = 1; i <= n; ++i) {
                line = br.readLine();
                info = line.split("\\s+");
                Krava krava = new Krava(i, Integer.parseInt(info[0]), Integer.parseInt(info[1]));
                pq.add(krava);
            }
            int max = Integer.MIN_VALUE;
            int index = -1;
            for(int i = 0; i < k; ++i) {
                Krava krava = pq.poll();
                if(krava.k2 > max) {
                    max = krava.k2;
                    index = krava.index;
                }
            }
            System.out.println(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
