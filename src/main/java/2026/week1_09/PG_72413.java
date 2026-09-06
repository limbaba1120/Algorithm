import java.util.Arrays;
import java.util.PriorityQueue;

class PG_72413 {
    private static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] road = new int[n + 1][n + 1];

        for (int[] row : road) {
            Arrays.fill(row, INF);
        }

        for (int i = 1; i <= n; i++) {
            road[i][i] = 0;
        }

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int distance = fare[2];

            road[start][end] = Math.min(road[start][end], distance);
            road[end][start] = Math.min(road[end][start], distance);
        }

        int[] fromS = dijkstra(road, n, s);
        int[] fromA = dijkstra(road, n, a);
        int[] fromB = dijkstra(road, n, b);

        int answer = INF;

        for (int sharedEnd = 1; sharedEnd <= n; sharedEnd++) {
            int totalFare = fromS[sharedEnd]
                    + fromA[sharedEnd]
                    + fromB[sharedEnd];

            answer = Math.min(answer, totalFare);
        }

        return answer;
    }

    private int[] dijkstra(int[][] road, int n, int start) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, INF);
        distances[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (x, y) -> Integer.compare(x[1], y[1])
        );
        queue.offer(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentPlace = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[currentPlace]) {
                continue;
            }

            for (int nextPlace = 1; nextPlace <= n; nextPlace++) {
                int fare = road[currentPlace][nextPlace];

                if (fare == INF) {
                    continue;
                }

                int nextDistance = currentDistance + fare;

                if (nextDistance < distances[nextPlace]) {
                    distances[nextPlace] = nextDistance;
                    queue.offer(new int[]{nextPlace, nextDistance});
                }
            }
        }

        return distances;
    }
}
