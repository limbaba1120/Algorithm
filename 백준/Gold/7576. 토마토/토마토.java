
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] graph;
    static int M, N;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();

        graph = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int s = scan.nextInt();
                graph[i][j] = s;
            }
        }

        int result = bfs();

        // 모든 토마토가 익지 못하는 상황
        if (checkAllTomato()) {
            System.out.println("-1");
        } else {
            System.out.println(result);
        }
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();

        // 익은 토마토 위치 큐에 추가
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        int days = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            days = now[2];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    if (graph[nx][ny] == 0) {
                        graph[nx][ny] = 1;
                        queue.offer(new int[]{nx, ny, days + 1});
                    }
                }
            }
        }

        return days;
    }

    private static boolean checkAllTomato() {
        // 모든 토마토가 익었는지 확인
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 0) {
                    return true; // 익지 않은 토마토가 있으면 true 반환
                }
            }
        }
        return false;
    }
}
