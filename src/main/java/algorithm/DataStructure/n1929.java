package algorithm.DataStructure;

import java.util.Scanner;

public class n1929 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int M = scan.nextInt();
        int N = scan.nextInt();

        int[] A = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = i;
        }

        int rootN = (int) Math.sqrt(N);

        for (int i = 1; i <= rootN; i++) {
            if (i == 1) {
                A[i] = 0;
            }
            if (A[i] == 0) continue;

            for (int j = i + i; j <= N; j = j + i) {
                A[j] = 0;
            }
        }

        for (int i = M; i <= N; i++) {
            if (A[i] != 0) {
                System.out.println(A[i]);
            }
        }
    }
}
