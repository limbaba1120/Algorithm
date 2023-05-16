package algorithm;

import java.util.Scanner;

public class Cheese {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //N X M 칸이 있는 테이블
        int N = scan.nextInt();
        int M = scan.nextInt();
        int [][] A = new int [N+1][M+1];

        //초기화 => A(1,1)부터 시작함
        for (int i = 1; i < N + 1; i++) {
            A[i][0] = 0;
        }
        for (int j = 1; j < M + 1; j++) {
            A[0][j] = 0;
        }
        //A(i,j)에 치즈를 놓는다.
        for (int a = 1; a < M + 1; a++) {
            int i = scan.nextInt();
            // 0을 입력하면 치즈를 그만 놓음
            if (i == 0) {
                break;
            }
            int j = scan.nextInt();
            A[i][j] = 1;
        }
        System.out.println(getCheese(A, N));
        
    }

    private static int getCheese(int[][] a, int n) {
        int sum = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                sum = Math.max(a[i-1][j], a[i][j-1]);
                if (a[i][j] == 1) {
                    sum += 1;
                }
                a[i][j] = sum;
            }
        }
        return a[n][n];
    }
}
