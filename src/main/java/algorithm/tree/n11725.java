package algorithm.tree;

import java.util.ArrayList;
import java.util.Scanner;

public class n11725 {
    static int N;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        tree = new ArrayList<>();
        visited = new boolean[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            int s = scan.nextInt();
            int e = scan.nextInt();
            tree.get(s).add(e);
            tree.get(e).add(s);
        }

        DFS(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(answer[i]);
        }

    }

    static void DFS(int node) {
        visited[node] = true;

        for (int next : tree.get(node)) {
            if (!visited[next]) {
                answer[next] = node;
                DFS(next);
            }
        }
    }
}
