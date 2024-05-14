package algorithm.tree;

import java.util.ArrayList;
import java.util.Scanner;

public class n1068 {
    static int N;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
    static int answer = 0;
    static int deleteNode;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        tree = new ArrayList<>();
        visited = new boolean[N + 1];
        int root = 0;

        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int s = scan.nextInt();
            if (s != -1) {
                tree.get(s).add(i);
                tree.get(i).add(s);
            } else {
                root = i;
            }
        }
        deleteNode = scan.nextInt();

        if (deleteNode == root) {
            System.out.println(0);
        } else {
            DFS(root);
            System.out.println(answer);
        }

    }

    static void DFS(int node) {
        visited[node] = true;
        int cNode = 0;

        for (int next : tree.get(node)) {
            if (!visited[next] && next != deleteNode) {
                cNode++;
                DFS(next);
            }
        }
        if (cNode == 0) {
            answer++;
        }
    }
}
