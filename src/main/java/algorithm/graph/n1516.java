package algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] A = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        int[] degree = new int[N + 1];
        int[] selfBuild = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            selfBuild[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == -1) {
                    break;
                }
                A[temp].add(i);
                degree[i]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] result = new int[N + 1];
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : A[now]) {
                degree[next]--;
                result[next] = Math.max(result[next], result[now] + selfBuild[now]);

                if (degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.println(result[i] + selfBuild[i]);
        }
    }
}
