package problem158_letenkamp;

import java.util.*;

/**
 * Created by petar on 9/1/16.
 */
public class LetenKamp {

    static class Vertex implements Comparable<Vertex> {
        int id;
        List<Edge> edges;
        long dist;

        Vertex(int id) {
            this.id = id;
            edges = new ArrayList<>();
            this.dist = INF;
        }

        @Override
        public int compareTo(Vertex o) {
            return Long.compare(dist, o.dist);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            return id == vertex.id;

        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    static class Edge  {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            return to == edge.to && weight == edge.weight;

        }

        @Override
        public int hashCode() {
            return to;
        }
    }

    private static Vertex[] v;
    private static Map<Integer, Integer> map;
    private static long[][] distances;
    private static long[][] dp;
    private static final long INF = Long.MAX_VALUE;

    public static void shortestPaths(List<Edge>[] edges, int s, int[] prio) {
        Arrays.fill(prio, Integer.MAX_VALUE);
        prio[s] = 0;
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add((long) s);
        while (!q.isEmpty()) {
            long cur = q.remove();
            int curu = (int) cur;
            if (cur >>> 32 != prio[curu])
                continue;
            for (Edge e : edges[curu]) {
                int v = e.to;
                int nprio = prio[curu] + e.weight;
                if (prio[v] > nprio) {
                    prio[v] = nprio;
                    q.add(((long) nprio << 32) + v);
                }
            }
        }
    }

    private static long tsp(int n) {
        int S = 1 << n;
        dp = new long[n][S];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < S; ++j) {
                dp[i][j] = -1;
            }
        }
        for(int i = 0; i < n; ++i) {
            dp[i][1] = distances[0][i];
        }
        return solve(0, S - 1);
    }

    private static long solve(int current, int mask) {
        if(dp[current][mask] != -1) {
            return dp[current][mask];
        }
        long ans = Long.MAX_VALUE;
        for(int i = 1; i < dp.length; ++i) {
            if((mask & (1 << i)) != 0) {
                ans = Math.min(ans, solve(i, mask ^ (1 << i)) + distances[i][current]);
            }
        }
        return dp[current][mask] = ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        int R = in.nextInt();
        int[] toVisit = new int[K + 1];
        map = new TreeMap<>();
        map.put(0, 0);
        for(int i = 1; i <= K; ++i) {
            toVisit[i] = in.nextInt();
            map.put(toVisit[i], i);
        }
        v = new Vertex[N];
        for(int i = 0; i < N; ++i) {
            v[i] = new Vertex(i);
        }
        ArrayList[] es = new ArrayList[N];
        for(int i = 0; i < N; ++i) {
            es[i] = new ArrayList<>();
        }
        for(int i = 0; i < R; ++i) {
            int from = in.nextInt();
            int to = in.nextInt();
            int wo = in.nextInt();
            es[from].add(new Edge(to, wo));
            es[to].add(new Edge(from, wo));
        }
        distances = new long[K + 1][K + 1];
        for(int i = 0; i < K + 1; ++i) {
            for(int j = 0; j < K + 1; ++j) {
                distances[i][j] = INF;
            }
        }
        for(int i = 0; i < K + 1; ++i) {
            int [] distancesFromI = new int[N];
            shortestPaths(es, toVisit[i], distancesFromI);
            for(int vv : map.keySet()) {
                distances[i][map.get(vv)] = distancesFromI[vv];
            }
        }
        System.out.println(tsp(K + 1));
    }
}
