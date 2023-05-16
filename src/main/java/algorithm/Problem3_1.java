package algorithm;

import java.util.Scanner;

public class Problem3_1 {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        System.out.println(hap123(N));
    }

    private static int hap123(int n) {
        if (n == 0) {
            return 0;
        }
        //배열 생성
        int A[] = new int[n + 1];
        //Base Case
        A[0] = 1;
        A[1] = 1;
        A[2] = 2;
        A[3] = 4;
        //Recursion
        for (int i = 4; i <= n; i++) {
            A[i] = A[i-1] + A[i-2] + A[i-3];
        }
        return A[n];

    }
}
