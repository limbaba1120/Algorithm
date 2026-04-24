package algorithm.graph;

import java.util.PriorityQueue;
import java.util.Scanner;

public class n1197 {
    static int[] parent;
    static PriorityQueue<Edge> queue;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int M = scan.nextInt();

        queue = new PriorityQueue<Edge>();
        parent = new int[N + 1];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int s = scan.nextInt();
            int e = scan.nextInt();
            int v = scan.nextInt();
            queue.add(new Edge(s, e, v));
        }

        int useEdge = 0;
        int result = 0;
        while (useEdge < N - 1) {
            Edge now = queue.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                result = result + now.v;
                useEdge++;
            }
        }
        System.out.println(result);

    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }


    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        int v;

        Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            // 오름차순으로 정렬 (제일 낮은 숫자가 먼저 빠져나감)
            return this.v - o.v;
        }
    }
}
