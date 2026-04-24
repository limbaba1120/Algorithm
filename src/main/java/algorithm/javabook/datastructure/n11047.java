package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n11047 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        int[] A = new int[N];
        int count = 0;

        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }

        for (int i = N - 1; i >= 0; i--) {
            if (A[i] <= K) {
                count += K / A[i];
                K = K % A[i];
            }
        }
        System.out.println(count);
    }
}
