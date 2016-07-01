package problem23_rnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by petar on 6/30/16.
 */

class Graph {

    Vertex[] vertex;

    public Graph(int V) {
        this.vertex = new Vertex[V];
        for(int i = 0; i < V; ++i) {
            this.vertex[i] = new Vertex(i);
        }
    }

    public void addEdge(int from, int to) {
        vertex[from].addEdge(to);
        vertex[to].addEdge(from);
    }

    class Vertex {
        int id;
        List<Edge> edges;

        Vertex(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        void addEdge(int to) {
            this.edges.add(new Edge(to));
        }
    }

    class Edge {
        int to;

        Edge(int to) {
            this.to = to;
        }
    }
}

/**
 * TODO: Works on 5/10 test cases
 */
public class RNA {

    static char[] sequence;
    static int[][] memo;

    static boolean canBond(char a, char b) {
        if(a == 'A' && b == 'U') return true;
        if(a == 'U' && b == 'A') return true;
        if(a == 'C' && b == 'G') return true;
        if(a == 'G' && b == 'C') return true;
        if(a == 'G' && b == 'U') return true;
        if(a == 'U' && b == 'G') return true;
        return false;
    }

    static int nextMatch(int x) {
        for(int i = x + 2; i < sequence.length; ++i) {
            if(canBond(sequence[x], sequence[i]))
                return i;
        }
        return -1;
    }

    static List<Interval> getIntervalsFrom(int start, int end) {
        List<Interval> intervals = new ArrayList<>();
        for(int i = start; i < end; ++i) {
            if(canBond(sequence[start], sequence[i])) {
                intervals.add(new Interval(start, i));
            }
        }
        return intervals;
    }

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int getInRange(int start, int end) {
        if(end - start < 2) {
            if(end > start) return getInRange(end, start);
            if(end > memo.length - 1 || start < 0 || end < 0 || start > memo.length - 1) return 0;
            return memo[start][end];
        }
        if(memo[start][end] != 0) {
            return memo[start][end];
        }
        int p1;
        if(memo[start+1][end-1] == 0) {
            memo[start + 1][end - 1] = getInRange(start + 1, end - 1);
        }
        p1 = memo[start + 1][end - 1];
        if(canBond(sequence[start], sequence[end])) {
            p1 = memo[start][end] = memo[start + 1][end - 1] + 1;
        }
        int p2;
        if(memo[start+1][end] == 0) {
            memo[start + 1][end] = getInRange(start + 1, end);
        }
        p2 = memo[start + 1][end];
        int p3;
        if(memo[start][end - 1] == 0) {
            memo[start][end - 1] = getInRange(start, end - 1);
        }
        p3 = memo[start][end - 1];
        memo[start][end] = Math.max(p1, Math.max(p2, p3));
        return memo[start][end];

    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int l = Integer.parseInt(br.readLine());
            String input = br.readLine();
            sequence = input.toCharArray();
            memo = new int[l][l];
            // TODO
            int maxBonds = 0;
            Interval mm = new Interval(0, l-1);
            for(int start = 0; start < l; ++start) {
                for(int end = l - 1; end >= 0; --end) {
                    int tmp = getInRange(start, end);
                    if(tmp > maxBonds) {
                        maxBonds = tmp;
                        mm = new Interval(start, end);
                    }
                }
            }
            System.out.println(getInRange(0, mm.start - 1) + getInRange(mm.start, mm.end) + getInRange(mm.end + 1, l-1));
//            for(int i = 0; i < l; ++i)  {
//                for(int j = 0; j < l; ++j) {
//                    System.out.print(memo[i][j] + "\t");
//                }
//                System.out.println();
//            }
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
