
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Node>[] A;
    static long lcm;
    static boolean visited[];
    static long D[];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        A = new ArrayList[N];
        visited = new boolean[N];
        D = new long[N];
        lcm = 1;

        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<Node>();
        }
        for (int i = 0; i < N - 1; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int p = scan.nextInt();
            int q = scan.nextInt();
            A[a].add(new Node(b, p, q));
            A[b].add(new Node(a, q, p));
            lcm *= (p * q / gcd(p, q));
        }
        D[0] = lcm;
        DFS(0);
        long mgcd = D[0];
        for (int i = 1; i < N; i++) {
            mgcd = gcd(mgcd, D[i]);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(D[i] / mgcd + " ");
        }
    }

    public static void DFS(int Node) {
        visited[Node] = true;

        for (Node i : A[Node]) {
            int next = i.getB();
            if (!visited[next]) {
                D[next] = D[Node] * i.getQ() / i.getP();
                DFS(next);
            }
        }

    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    static class Node {
        int b;
        int p;
        int q;

        public Node(int b, int p, int q) {
            super();
            this.b = b;
            this.p = p;
            this.q = q;
        }
        public int getB() {
            return b;
        }
        public int getP() {
            return p;
        }
        public int getQ() {
            return q;
        }
    }
}
