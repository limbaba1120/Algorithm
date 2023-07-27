package ch4;

import java.util.Scanner;

public class n10811 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] arr = new int[N+1];

        for (int i = 1; i <= N; i++)
            arr[i] = i;

        for (int k = 0; k < M; k++) {
            int i = scan.nextInt();
            int j = scan.nextInt();

            while (i < j) {
                int tmp = arr[i];
                arr[i++] = arr[j];
                arr[j--] = tmp;
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
