import java.util.Arrays;

class PG_42861 {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] cost : costs) {
            int nodeA = cost[0];
            int nodeB = cost[1];

            if (find(parent, nodeA) != find(parent, nodeB)) {
                union(parent, nodeA, nodeB);
                answer += cost[2];
            }
        }

        return answer;
    }

    private int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent, parent[node]);
    }

    private void union(int[] parent, int nodeA, int nodeB) {
        int rootA = find(parent, nodeA);
        int rootB = find(parent, nodeB);

        parent[rootB] = rootA;
    }
}
