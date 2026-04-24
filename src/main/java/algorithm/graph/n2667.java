package algorithm.graph;

import java.util.*;

public class n2667 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int totalApartNum = 0;
    static int[] aparts = new int[10000];
    static int n;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        // 배열 초기화
        A = new int[n][n];
        visited = new boolean[n][n];

        // 전체 사각형 입력 받기
        for (int i = 0; i < n; i++) {
            String input = scan.next();
            for (int j = 0; j < n; j++) {
                A[i][j] = input.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1 && !visited[i][j]) {
                    totalApartNum++;
//                    dfs(i, j);
                    bfs(i, j);
                }
            }
        }
        Arrays.sort(aparts);
        System.out.println(totalApartNum);
        for (int i = 0; i < aparts.length; i++) {
            if (aparts[i] == 0) {
                continue;
            } else {
                System.out.println(aparts[i]);
            }
        }


    }

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        aparts[totalApartNum]++;
        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                if (x >= 0 && y >= 0 && x < n && y < n) {
                    if (A[x][y] != 0 && !visited[x][y]) {
                        bfs(x, y);
                    }
                }
            }
        }
    }

//    private static void dfs(int x, int y) {
//        visited[x][y] = true;
//        aparts[totalApartNum]++;
//
//        for (int i = 0; i < 4; i++) {  // 상하좌우로 탐색
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
//                if (A[nx][ny] != 0 && !visited[nx][ny]) {
//                    dfs(nx, ny);
//                }
//            }
//
//        }
//    }

}
