package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n2251 {
    static int[] parent;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            int question = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();
            if (question == 0) {
                union(a, b);
            } else {
                if (checkSame(a, b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
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

    public static boolean checkSame(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        } else {
            return false;
        }
    }
}
