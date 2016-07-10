package problem167_ramps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by petar on 7/10/16.
 */

class Ramp {

    int distance;
    int interval;

    public Ramp(int distance, int interval) {
        this.distance = distance;
        this.interval = interval;
    }

}

public class Ramps {

    private static long solve(ArrayList<Ramp> ramps) {
        for(long startTime = 0; startTime < 2000000; ++startTime) {
            boolean flag = false;
            for (int i = 0; i < ramps.size(); ++i) {
                Ramp r = ramps.get(i);
                if (((r.distance + startTime) / r.interval) % 2 != 1) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                return startTime + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            for(int t = 0; t < T; ++t) {
                int r = Integer.parseInt(br.readLine());
                String[] distances = br.readLine().split("\\s+");
                String[] intervals = br.readLine().split("\\s+");
                ArrayList<Ramp> ramps = new ArrayList<>();
                for(int i = 0; i < r; ++i) {
                    ramps.add(new Ramp(Integer.parseInt(distances[i]), Integer.parseInt(intervals[i])));
                }
                System.out.println(solve(ramps));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
