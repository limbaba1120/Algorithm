package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n11659 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int[] A = new int[N+1];
        int[] S = new int[N+1];

        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }

        S[0] = A[0];

        for (int i = 1; i <= N; i++) {
            S[i] = S[i-1] + A[i];
        }

        for (int i = 0; i < M; i++) {
            int s = scan.nextInt();
            int e = scan.nextInt();
            System.out.println(S[e] - S[s-1]);
        }
    }
}
