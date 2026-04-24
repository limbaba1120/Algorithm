package algorithm.javabook.datastructure;

import java.util.Arrays;
import java.util.Scanner;

public class n1940 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int[] arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);

        int s = 1;
        int e = N;
        int count = 0;

        while (s < e) {
            if (arr[s] + arr[e] < M) {
                s++;
            } else if (arr[s] + arr[e] > M) {
                e--;
            } else {
                count++;
                s++;
                e--;
            }
        }
        System.out.println(count);
    }
}
