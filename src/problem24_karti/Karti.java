package problem24_karti;

import java.util.*;

/**
 * Created by petar on 7/1/16.
 */

/**
 * TODO: Doesn't work with BFS
 */
public class Karti {

    private static State start, goal1, goal2;

    private static class State {

        boolean[] state;
        int dist;

        State(boolean[] state, int dist) {
            this.state = state;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;
            if(getClass() != obj.getClass()) return false;
            State other = (State) obj;
            for(int i = 0; i < state.length; ++i) {
                if(state[i] != other.state[i]) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(state);
        }
    }

    private static int bfs(int N, int K) {
        Set<State> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            State current = queue.poll();
            if(!visited.contains(current)) {
                visited.add(current);
                if(current.equals(goal1)) return current.dist;
                if(current.equals(goal2)) return current.dist;
                for(int i = 0; i < N - K + 1; ++i) {
                    boolean[] newState = Arrays.copyOf(current.state, N);
                    for(int j = 0; j < K; ++j) {
                        newState[i + j] = !newState[i + j];
                    }
                    State next = new State(newState, current.dist + 1);
                    queue.add(next);
                }

            }
        }
        return -1;
    }

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        boolean[] tmp = new boolean[N];
        boolean[] tmp1 = new boolean[N];
        boolean[] tmp2 = new boolean[N];
        for(int i = 0; i < N; ++i) {
            tmp[i] = in.nextInt() == 1;
            tmp1[i] = false;
            tmp2[i] = true;
        }
        start = new State(tmp, 0);
        goal1 = new State(tmp1, 0);
        goal2 = new State(tmp2, 0);
        System.out.println(bfs(N, K));
    }
}
