import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static boolean[][] isVisited;
    static int r, c, d;

    // 문제의 방향 번호 유지: 1=상, 2=하, 3=좌, 4=우
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n + 1][n + 1];
        isVisited = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isVisited[r][c] = true;
        answer.append(r).append(' ').append(c).append('\n');

        while (true) {
            if (exploreOneStep()) {
                continue;
            }

            int[] target = findTarget();
            if (target == null) {
                break;
            }

            moveToTarget(target[0], target[1]);
        }

        System.out.print(answer);
    }

    private static boolean inRange(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }

    private static boolean canExplore(int row, int col) {
        return inRange(row, col)
                && board[row][col] == 0
                && !isVisited[row][col];
    }

    // 1단계: 현재 방향 기준 직진 → 좌회전 → 우회전 → 뒤쪽
    private static boolean exploreOneStep() {
        int[] directions;
        switch (d) {
            case 1:
                directions = new int[]{1, 3, 4, 2};
                break;
            case 2:
                directions = new int[]{2, 4, 3, 1};
                break;
            case 3:
                directions = new int[]{3, 2, 1, 4};
                break;
            case 4:
                directions = new int[]{4, 1, 2, 3};
                break;
            default:
                throw new IllegalArgumentException("잘못된 방향");
        }

        for (int nextDir : directions) {
            int nr = r + dx[nextDir];
            int nc = c + dy[nextDir];
            if (!canExplore(nr, nc)) {
                continue;
            }

            r = nr;
            c = nc;
            d = nextDir;
            isVisited[r][c] = true;
            answer.append(r).append(' ').append(c).append('\n');
            return true;
        }

        return false;
    }

    // 실제 고래는 움직이지 않고, 출발점에서 모든 칸까지 거리를 계산
    private static int[][] getDistances(int startR, int startC) {
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        dist[startR][startC] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int dir = 1; dir <= 4; dir++) {
                int nr = row + dx[dir];
                int nc = col + dy[dir];
                if (!inRange(nr, nc)) {
                    continue;
                }
                if (board[nr][nc] == 1 || dist[nr][nc] != -1) {
                    continue;
                }

                dist[nr][nc] = dist[row][col] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }

        return dist;
    }

    // 목적지 선택 우선순위: 최단 거리 → 작은 행 → 작은 열
    private static int[] findTarget() {
        int[][] dist = getDistances(r, c);
        int minDistance = Integer.MAX_VALUE;
        int[] target = null;

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (board[row][col] == 1
                        || isVisited[row][col]
                        || dist[row][col] == -1) {
                    continue;
                }

                // 행, 열 순으로 순회하므로 같은 거리는 기존 후보 유지
                if (dist[row][col] < minDistance) {
                    minDistance = dist[row][col];
                    target = new int[]{row, col};
                }
            }
        }

        return target;
    }

    private static void moveToTarget(int targetR, int targetC) {
        // 목적지에서 BFS: 각 칸에서 목적지까지 남은 거리와 동일
        int[][] dist = getDistances(targetR, targetC);
        int[] directions = {3, 2, 4, 1}; // 좌 → 하 → 우 → 상

        while (r != targetR || c != targetC) {
            for (int nextDir : directions) {
                int nr = r + dx[nextDir];
                int nc = c + dy[nextDir];
                if (!inRange(nr, nc) || board[nr][nc] == 1) {
                    continue;
                }
                if (dist[nr][nc] != dist[r][c] - 1) {
                    continue;
                }

                r = nr;
                c = nc;
                d = nextDir;

                if (!isVisited[r][c]) {
                    isVisited[r][c] = true;
                    answer.append(r).append(' ').append(c).append('\n');
                }

                break;
            }
        }
    }
}
