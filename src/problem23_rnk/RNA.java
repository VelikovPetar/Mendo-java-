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
        if(end - start < 2) return 0;
        if(canBond(sequence[start], sequence[end])) return 1 + getInRange(start + 1, end - 1);
        else return Math.max(getInRange(start, end - 1), getInRange(start + 1, end));
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
            int[][] pairs = new int[l][l];
            for(int i = 0; i < l; ++i)  {
                for(int j = 0; j < l; ++j) {
                    if(Math.abs(i - j) < 2) continue;
                    if(canBond(sequence[i], sequence[j])) {
                        pairs[i][j] = 1;
                    }
                }
            }
//            for(int i = 0; i < l; ++i)  {
//                for(int j = 0; j < l; ++j) {
//                    System.out.print(pairs[i][j] + "\t");
//                }
//                System.out.println();
//            }
            int[][] D = new int[l][l];
            for(int i = 2; i < l; ++i) {
                if(canBond(sequence[0], sequence[i])) {
                    D[0][i] = D[i][0] = 1;
                }
            }
            for(int i = 1; i < l; ++i) {
                for(int j = 1; j < l; ++j) {
                    if(Math.abs(i - j) < 2) continue;
                    if(i > j) D[i][j] = D[j][i];
                    if(canBond(sequence[i], sequence[j])) {
                        D[i][j] = D[i - 1][j - 1] + 1;
                    } else {
                        D[i][j] = D[i - 1][j - 1];
                    }
                }
            }
            int maxBonds = 0;
            for(int start = 0; start < l; ++start) {
                List<Interval> intervals = getIntervalsFrom(start, l);
                for(Interval i : intervals) {
                    maxBonds = Math.max(maxBonds, getInRange(i.start, i.end));
                }
            }
            System.out.print(maxBonds);
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
