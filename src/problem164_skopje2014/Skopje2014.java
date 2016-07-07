package problem164_skopje2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by petar on 7/7/16.
 */

class HorizontalBorder {
    int x1, x2, y;

    HorizontalBorder(int x1, int x2, int y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorizontalBorder that = (HorizontalBorder) o;

        return x1 == that.x1 && x2 == that.x2 && y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x1;
        result = 31 * result + x2;
        result = 31 * result + y;
        return result;
    }
}

class VerticalBorder {
    int x, y1, y2;

    VerticalBorder(int x, int y1, int y2) {
        this.x = x;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerticalBorder that = (VerticalBorder) o;

        return x == that.x && y1 == that.y1 && y2 == that.y2;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y1;
        result = 31 * result + y2;
        return result;
    }
}

class Position {
    int x, y;
    boolean top, right, bot, left;

    Position(int x, int y, Set<HorizontalBorder> hbs, Set<VerticalBorder> vbs) {
        this.x = x;
        this.y = y;
        for(HorizontalBorder hb : hbs) {
            if(hb.x1 <= x && hb.x2 > x && hb.y == y + 1) {
                bot = true;
            }
            if(hb.x1 <= x && hb.x2 > x && hb.y == y) {
                top = true;
            }
        }
        for(VerticalBorder vb : vbs) {
            if(vb.y1 <= y && vb.y2 > y && vb.x == x + 1) {
                right = true;
            }
            if(vb.y1 <= y && vb.y2 > y && vb.x == x) {
                left = true;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return x == position.x && y == position.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

class State implements Comparable<State> {
    Position p;
    int bordersJumped;

    State(Position p, int bordersJumped) {
        this.p = p;
        this.bordersJumped = bordersJumped;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(bordersJumped, o.bordersJumped);
    }
}

public class Skopje2014 {

    private static int W, L;
    private static Set<HorizontalBorder> hbs;
    private static Set<VerticalBorder> vbs;

    private static int bfs(Position start, Position end) {
        Set<Position> visited = new HashSet<>();
        PriorityQueue<State> queue = new PriorityQueue<>();
        State init = new State(start, 0);
        visited.add(start);
        queue.add(init);
        while(!queue.isEmpty()) {
            State currentState = queue.poll();
            Position currentPosition = currentState.p;
            if(currentPosition.equals(end)) {
                return currentState.bordersJumped;
            }
            visited.add(currentPosition);
            int x = currentPosition.x;
            int y = currentPosition.y;
            if(y + 1 < L) {
                Position down = new Position(x, y + 1, hbs, vbs);
                if(!visited.contains(down)) {
                    State ns = new State(down, currentState.bordersJumped + (currentPosition.bot ? 1 : 0));
                    queue.add(ns);
                }
            }
            if(y - 1 >= 0) {
                Position up = new Position(x, y - 1, hbs, vbs);
                if(!visited.contains(up)) {
                    State ns = new State(up, currentState.bordersJumped + (currentPosition.top ? 1 : 0));
                    queue.add(ns);
                }
            }
            if(x + 1 < W) {
                Position right = new Position(x + 1, y, hbs, vbs);
                if(!visited.contains(right)) {
                    State ns = new State(right, currentState.bordersJumped + (currentPosition.right ? 1 : 0));
                    queue.add(ns);
                }
            }
            if(x - 1 >= 0) {
                Position left = new Position(x - 1, y, hbs, vbs);
                if(!visited.contains(left)) {
                    State ns = new State(left, currentState.bordersJumped + (currentPosition.left ? 1 : 0));
                    queue.add(ns);
                }
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
                String[] parts = br.readLine().split("\\s+");
                W = Integer.parseInt(parts[0]);
                L = Integer.parseInt(parts[1]);
                parts = br.readLine().split("\\s+");
                int startX = Integer.parseInt(parts[0]);
                int startY = Integer.parseInt(parts[1]);
                int endX = Integer.parseInt(parts[2]);
                int endY = Integer.parseInt(parts[3]);
                hbs = new HashSet<>();
                int H = Integer.parseInt(br.readLine());
                for(int h = 0; h < H; ++h) {
                    parts = br.readLine().split("\\s+");
                    hbs.add(new HorizontalBorder(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                }
                vbs = new HashSet<>();
                int V = Integer.parseInt(br.readLine());
                for(int v = 0; v < V; ++v) {
                    parts = br.readLine().split("\\s+");
                    vbs.add(new VerticalBorder(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                }
                Position start = new Position(startX, startY, hbs, vbs);
                Position end = new Position(endX, endY, hbs, vbs);
                System.out.println(bfs(start, end));
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
