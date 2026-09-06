import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class PG_87694 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(
            int[][] rectangle,
            int characterX,
            int characterY,
            int itemX,
            int itemY
    ) {
        int[][] map = new int[102][102];
        int[][] distance = new int[102][102];

        doubleRectangles(rectangle);

        characterX = doubleCoordinate(characterX);
        characterY = doubleCoordinate(characterY);
        itemX = doubleCoordinate(itemX);
        itemY = doubleCoordinate(itemY);

        fillRectangles(rectangle, map);
        removeInside(rectangle, map);

        int doubleDistance = bfs(
                map,
                distance,
                characterX,
                characterY,
                itemX,
                itemY
        );

        return doubleDistance / 2;
    }

    private int bfs(
            int[][] map,
            int[][] distance,
            int characterX,
            int characterY,
            int itemX,
            int itemY
    ) {
        for (int i = 0; i < distance.length; i++) {
            Arrays.fill(distance[i], -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{characterX, characterY});
        distance[characterX][characterY] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            if (currentX == itemX && currentY == itemY) {
                return distance[currentX][currentY];
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextX = dx[direction] + currentX;
                int nextY = dy[direction] + currentY;

                if (nextX < 0 || nextY < 0
                        || nextX >= map.length
                        || nextY >= map[0].length) {
                    continue;
                }

                if (map[nextX][nextY] != 1) {
                    continue;
                }

                if (distance[nextX][nextY] != -1) {
                    continue;
                }

                distance[nextX][nextY] =
                        distance[currentX][currentY] + 1;

                queue.offer(new int[]{nextX, nextY});
            }
        }

        return -1;
    }

    private void removeInside(int[][] rectangle, int[][] map) {
        for (int[] rect : rectangle) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];

            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = 0;
                }
            }
        }
    }

    private void fillRectangles(int[][] rectangle, int[][] map) {
        for (int[] rect : rectangle) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    map[x][y] = 1;
                }
            }
        }
    }

    private void doubleRectangles(int[][] rectangle) {
        for (int i = 0; i < rectangle.length; i++) {
            rectangle[i][0] *= 2;
            rectangle[i][1] *= 2;
            rectangle[i][2] *= 2;
            rectangle[i][3] *= 2;
        }
    }

    private int doubleCoordinate(int coordinate) {
        return 2 * coordinate;
    }
}
