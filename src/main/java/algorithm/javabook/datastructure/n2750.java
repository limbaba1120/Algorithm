package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n2750 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (A[j] > A[j + 1]) {
                    int tmp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = tmp;
                }
            }
        }
        for (int i : A) {
            System.out.println(i);
        }
    }
}
