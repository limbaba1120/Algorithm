package ch7;

import java.util.Scanner;

public class n2566 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arr[][] = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        int max = 0;
        int N = 0;
        int M = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (max < arr[i][j]) {
                    max = arr[i][j];
                    N = i + 1;
                    M = j + 1;
                }
            }
        }
        System.out.println(max);
        System.out.println(N + " " + M);
    }
}
/*
이 문제도 arr[i][j]값을 받고 바로 max 비교해도됨
 */