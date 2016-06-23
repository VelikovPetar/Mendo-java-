package problem18_cifri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by petar on 6/23/16.
 */
public class Cifri {

    public static int getMax(String s) {
        int maxIndex = 0;
        for(int i = 1; i < s.length(); ++i) {
            if(s.charAt(i) > s.charAt(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static String build(String s, int k) {
        if(k == 0) {
            return s;
        }
        int len = s.length();
        if(len <= k) {
            return "";
        }
        String subs = s.substring(0, k + 1);
        int maxIndex = getMax(subs);
        String newString = s.substring(maxIndex + 1);
        return s.charAt(maxIndex) + build(newString, k - maxIndex);
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            String[] part = line.split("\\s+");
            int k = Integer.parseInt(part[1]);
            System.out.println(build(part[0], k));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
