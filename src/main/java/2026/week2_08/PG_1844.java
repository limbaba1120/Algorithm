import java.util.ArrayDeque;
import java.util.Queue;

class PG_1844 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        int[][] isVisited = new int[maps.length][maps[0].length];
        int answer = 0;

        isVisited[0][0] = 1;

        bfs(maps, isVisited, 0, 0);

        answer = isVisited[maps.length - 1][maps[0].length - 1];

        return answer == 0 ? -1 : answer;
    }

    static void bfs(int[][] maps, int[][] isVisited, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            for (int k = 0; k < 4; k++) {
                int nX = currentX + dx[k];
                int nY = currentY + dy[k];

                if (nX < 0 || nY < 0
                        || maps.length - 1 < nX
                        || maps[0].length - 1 < nY) {
                    continue;
                }

                if (isVisited[nX][nY] == 0 && maps[nX][nY] == 1) {
                    queue.offer(new int[]{nX, nY});
                    isVisited[nX][nY] = isVisited[currentX][currentY] + 1;
                }
            }
        }
    }
}
