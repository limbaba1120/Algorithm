import java.util.Arrays;
import java.util.PriorityQueue;

class PG_12978 {
    private static final int INF = Integer.MAX_VALUE;

    public int solution(int N, int[][] road, int K) {
        int[][] town = new int[N + 1][N + 1];

        for (int[] row : town) {
            Arrays.fill(row, INF);
        }

        for (int i = 1; i <= N; i++) {
            town[i][i] = 0;
        }

        for (int[] currentRoad : road) {
            int start = currentRoad[0];
            int end = currentRoad[1];
            int time = currentRoad[2];

            town[start][end] = Math.min(town[start][end], time);
            town[end][start] = Math.min(town[end][start], time);
        }

        return dijkstra(N, town, K);
    }

    private int dijkstra(int N, int[][] town, int K) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, INF);
        distances[1] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[1], b[1])
        );
        queue.offer(new int[]{1, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentTown = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[currentTown]) {
                continue;
            }

            for (int nextTown = 1; nextTown <= N; nextTown++) {
                int roadTime = town[currentTown][nextTown];

                if (roadTime == INF) {
                    continue;
                }

                int newDistance = currentDistance + roadTime;

                if (newDistance < distances[nextTown]) {
                    distances[nextTown] = newDistance;
                    queue.offer(new int[]{nextTown, newDistance});
                }
            }
        }

        int deliverableTownCount = 0;

        for (int townNumber = 1; townNumber <= N; townNumber++) {
            if (distances[townNumber] <= K) {
                deliverableTownCount++;
            }
        }

        return deliverableTownCount;
    }
}
