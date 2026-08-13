import java.util.ArrayDeque;
import java.util.Queue;

class PG_43162 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                bfs(i, computers, isVisited);
                answer++;
            }
        }

        return answer;
    }

    static void bfs(int start, int[][] computers, boolean[] isVisited) {
        Queue<Integer> queue = new ArrayDeque<>();

        isVisited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 0; next < computers.length; next++) {
                if (computers[current][next] == 1 && !isVisited[next]) {
                    isVisited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}
