import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// 2026-11-12 | 프로그래머스 132266: 부대복귀
// 출발지마다 BFS를 실행하는 학습용 풀이. 반복 탐색 최적화는 아직 적용하지 않았다.
// 프로그래머스 제출 시 클래스 이름을 Solution으로 변경한다.
class PG_132266 {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        int[] result = new int[sources.length];

        for (int i = 0; i < sources.length; i++) {
            result[i] = bfs(graph, sources[i], destination);
        }

        return result;
    }

    private int bfs(List<List<Integer>> graph, int start, int destination) {
        if (start == destination) {
            return 0;
        }

        int[] distance = new int[graph.size()];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        distance[start] = 0;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                if (distance[next] != -1) {
                    continue;
                }

                distance[next] = distance[current] + 1;

                if (next == destination) {
                    return distance[next];
                }

                queue.offer(next);
            }
        }

        return -1;
    }
}
