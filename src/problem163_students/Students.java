package problem163_students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by petar on 7/7/16.
 */
public class Students {

    public static void main(String[]args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            for(int i = 0; i < T; ++i) {
                String group1 = br.readLine();
                String group2 = br.readLine();
                String[] parts1 = group1.split("\\s+");
                String[] parts2 = group2.split("\\s+");
                int N = Integer.parseInt(parts1[0]);
                int sum1 = 0;
                ArrayList<Integer> scores1 = new ArrayList<>();
                for(int j = 1; j < N + 1; ++j) {
                    int scr = Integer.parseInt(parts1[j]);
                    scores1.add(scr);
                    sum1 += scr;
                }
                double avg1 = (double) sum1 / N;

                int M = Integer.parseInt(parts2[0]);
                int sum2 = 0;
                ArrayList<Integer> scores2 = new ArrayList<>();
                for(int j = 1; j < M + 1; ++j) {
                    int scr = Integer.parseInt(parts2[j]);
                    scores2.add(scr);
                    sum2 += scr;
                }
                double avg2 = (double) sum2 / M;

                int count = 0;
                for(int scr : scores1) {
                    if((double) (sum1 - scr) / (N - 1) > avg1 && (double) (sum2 + scr) / (M + 1) > avg2) {
                        count ++;
                    }
                }
                for(int scr : scores2) {
                    if((double) (sum2 - scr) / (M - 1) > avg2 && (double) (sum1 + scr) / (N + 1) > avg1) {
                        count ++;
                    }
                }
                System.out.println(count);
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
