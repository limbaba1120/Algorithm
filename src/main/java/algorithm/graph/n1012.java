package algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class n1012 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] graph;
    static int T;
    static int M, N, K;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        T = scan.nextInt();

        for (int i = 0; i < T; i++) {
            int totalSwarm = 0;
            M = scan.nextInt();
            N = scan.nextInt();
            K = scan.nextInt();

            visited = new boolean[M][N];
            graph = new int[M][N];

            for (int j = 0; j < K; j++) {
                int s = scan.nextInt();
                int e = scan.nextInt();

                graph[s][e] = 1;
            }

            for (int k = 0; k < M; k++) {
                for (int l = 0; l < N; l++) {
                    if (graph[k][l] == 1 && !visited[k][l]) {
                        totalSwarm++;

                        bfs(k, l);
                    }
                }
            }
            System.out.println(totalSwarm);
        }




    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            for (int k = 0; k < 4; k++) {
                int X = now[0] + dx[k];
                int Y = now[1] + dy[k];
                if (X >= 0 && Y >= 0 && X < M && Y < N) {
                    if (graph[X][Y] != 0 && !visited[X][Y]) {
                        bfs(X, Y);
                    }
                }
            }
        }
    }

}
