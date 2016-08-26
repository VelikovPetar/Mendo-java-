package problem_141_magicforest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by petar on 8/26/16.
 */

class Position {
    int i, j;
    int dist;

    public Position(int i, int j, int dist) {
        this.i = i;
        this.j = j;
        this.dist = dist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return i == position.i && j == position.j;

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }
}

public class MagicForest {

    static int R, C;
    static char[][] grid;
    static Position start, finish;

    static Map<Integer, Integer> bfs() {
        Queue<Position> queue = new LinkedList<>();
        queue.add(finish);
        Map<Integer, Integer> damage = new HashMap<>();
        start.dist = Integer.MAX_VALUE;
        while(!queue.isEmpty() && queue.peek().dist <= start.dist) {
            Position current = queue.poll();
            int i = current.i;
            int j = current.j;
            int dist = current.dist;
            if(grid[i][j] == 'T') continue;
            if(Character.isDigit(grid[i][j])) {
                int val = 0;
                if (damage.containsKey(dist)) {
                    val = damage.get(dist);
                }
                val += Character.getNumericValue(grid[i][j]);
                damage.put(dist, val);
            }
            grid[i][j] = 'T';
            if(current.equals(start)) {
                start.dist = dist;
            }
            if(i > 0 && grid[i - 1][j] != 'T' && dist + 1 < start.dist) {
                queue.add(new Position(i - 1, j , dist + 1));
            }
            if(i < R - 1 && grid[i + 1][j] != 'T' && dist + 1 < start.dist) {
                queue.add(new Position(i + 1, j, dist + 1));
            }
            if(j > 0 && grid[i][j - 1] != 'T' && dist + 1 < start.dist) {
                 queue.add(new Position(i, j - 1, dist + 1));
            }
            if(j < C - 1 && grid[i][j + 1] != 'T' && dist + 1 < start.dist) {
                queue.add(new Position(i, j + 1, dist + 1));
            }
        }
        return damage;
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            String line = null;
            for(int t = 0; t < T; ++t) {
                line = br.readLine();
                String[] part = line.split("\\s+");
                R = Integer.parseInt(part[0]);
                C = Integer.parseInt(part[1]);
                grid = new char[R][C];
                for(int r = 0; r < R; ++r) {
                    line = br.readLine();
                    grid[r] = line.toCharArray();
                    for(int c = 0; c < C; ++c) {
                        if(grid[r][c] == 'S') {
                            start = new Position(r, c, 0);
                        } else if (grid[r][c] == 'E') {
                            finish = new Position(r, c, 0);
                        }
                    }
                }
                Map<Integer, Integer> damage = bfs();
                int hp = 100;
                for(Integer i : damage.keySet()) {
                    if(i <= start.dist) {
                        hp -= damage.get(i);
                    }
                }
                int ans = Math.max(0, hp);
                System.out.println(ans);
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
