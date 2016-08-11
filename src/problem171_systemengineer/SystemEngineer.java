package problem171_systemengineer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by petar on 7/10/16.
 */

class Position {
    int x,y, depth;

    Position(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}

/**
 * TODO: Sfati shto se bara ustvari u zadachava...
 */
public class SystemEngineer {

    private static int R, C;
    private static char[][][] building;


    public static void bfs(Position start, ArrayList<Position> ends) {
        double total = 0;
        for(int i = 0; i < building.length; ++i) {
            Position end = ends.get(i);
            for(int j = 0; j < R; ++j) {
                for(int k = 0; k < C; ++k) {
                    if(building[i][j][k] == 'E' && (end.x != j || end.y != k)) {
                        building[i][j][k] = '.';
                    }
                    System.out.print(building[i][j][k]);
                }
                System.out.println();
            }
            System.out.println("++++++++++++++++++++++++++");

            Queue<Position> queue = new LinkedList<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                Position current = queue.poll();
                int x = current.x;
                int y = current.y;
                int depth = current.depth;
                if (building[i][x][y] == 'E') {
                    System.out.println(String.format("[%d, %d] at %d distance.", x, y, depth));
                    total += (double) depth;
                    break;
                }
                building[i][x][y] = 'X';
                if (x - 1 >= 0) {
                    if (building[i][x - 1][y] != 'X') {
                        queue.add(new Position(x - 1, y, depth + 1));
                    }
                }
                if (x + 1 < R) {
                    if (building[i][x + 1][y] != 'X') {
                        queue.add(new Position(x + 1, y, depth + 1));
                    }
                }
                if (y - 1 >= 0) {
                    if (building[i][x][y - 1] != 'X') {
                        queue.add(new Position(x, y - 1, depth + 1));
                    }
                }
                if (y + 1 < C) {
                    if (building[i][x][y + 1] != 'X') {
                        queue.add(new Position(x, y + 1, depth + 1));
                    }
                }
            }
        }
        System.out.println(String.format("%.10f", Math.pow(total/building.length, 2)));
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            for(int t = 0; t < T; ++t) {
                String[] parts = br.readLine().split("\\s+");
                R = Integer.parseInt(parts[0]);
                C = Integer.parseInt(parts[1]);
                char[][] tmpBuilding = new char[R][C];
                int startX, startY, endX, endY;
                Position start = null;
                ArrayList<Position> ends = new ArrayList<>();
                for(int i = 0; i < R; ++i) {
                    char[] row = br.readLine().toCharArray();
                    for(int j = 0; j < row.length; ++j) {
                        if(row[j] == 'S') {
                            start = new Position(i,j, 0);
                        }
                        if(row[j] == 'E') {
                            ends.add(new Position(i, j, 0));
                        }
                    }
                    tmpBuilding[i] = row;
                }
                building = new char[ends.size()][R][C];
                for(int i = 0; i < ends.size(); ++i) {
                    for(int r = 0; r < R; ++r) {
                        building[i][r] = Arrays.copyOf(tmpBuilding[r], C);
                    }
                }
                bfs(start, ends);
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
