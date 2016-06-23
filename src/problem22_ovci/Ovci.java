package problem22_ovci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by petar on 6/23/16.
 */
public class Ovci {

    private static int sheep;
    private static int wolf;
    private static char[][] pole;

    private static void dfs(int i, int j) {
        if(pole[i][j] == 'o')
            sheep ++;
        if(pole[i][j] == 'v')
            wolf ++;
        pole[i][j] = '#';
        if(i > 0 && pole[i - 1][j] != '#') dfs(i - 1, j);
        if(i < pole.length - 1 && pole[i + 1][j] != '#') dfs(i + 1, j);
        if(j > 0 && pole[i][j - 1] != '#') dfs(i, j - 1);
        if(j < pole[i].length - 1 && pole[i][j + 1] != '#') dfs(i, j + 1);
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            String[] info = line.split("\\s+");
            int r = Integer.parseInt(info[0]);
            int s = Integer.parseInt(info[1]);
            pole = new char[r][s];
            for(int i = 0; i < r; ++i) {
                line = br.readLine();
                char[] l = line.toCharArray();
                pole[i] = l;
            }
            int sheep_total = 0;
            int wolf_total = 0;
            for(int i = 0; i < r; ++i) {
                for(int j = 0; j < s; ++j) {
                    sheep = 0;
                    wolf = 0;
                    if(pole[i][j] == 'o' || pole[i][j] == 'v') {
                        dfs(i, j);
                        if(sheep > wolf) {
                            sheep_total += sheep;
                        } else {
                            wolf_total += wolf;
                        }
                    }
                }
            }
            System.out.println(String.format("%d %d", sheep_total, wolf_total));
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
