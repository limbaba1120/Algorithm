package algorithm.javabook.datastructure;

import java.io.BufferedReader;
import java.util.Scanner;

public class n10986 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] A = new int[N + 1];
        int[] S = new int[N + 1];

        S[0] = scan.nextInt();
        int cnt = 0;

        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + scan.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int remainder = S[i] % M;
            if (remainder == 0) cnt++;
            A[remainder]++;
        }
        for (int i = 0; i < M; i++) {
            if (A[i] > 1) {
                cnt += (A[i] * (A[i-1]) / 2);
            }
        }
        System.out.println(cnt);


    }
}
