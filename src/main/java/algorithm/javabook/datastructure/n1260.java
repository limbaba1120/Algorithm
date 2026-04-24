package algorithm.javabook.datastructure;

import java.util.*;

public class n1260 {
    static boolean visited[];
    static ArrayList<Integer>[] A;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int Start = scan.nextInt();
        A = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            int s = scan.nextInt();
            int e = scan.nextInt();
            A[s].add(e);
            A[e].add(s);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(A[i]);
        }
        visited = new boolean[N + 1];
        DFS(Start);
        System.out.println();
        visited = new boolean[N + 1];
        BFS(Start);
        System.out.println();
    }

    public static void DFS(int Node) {
        System.out.print(Node + " ");
        visited[Node] = true;
        for (int i : A[Node]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        visited[Node] = true;

        while (!queue.isEmpty()) {
            int now_node = queue.poll();
            System.out.println(now_node + " ");
            for (int i : A[now_node]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
