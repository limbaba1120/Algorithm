package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n2343 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int[] A = new int[N];
        int start = 0;
        int end = 0;
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
            if (start < A[i]) start = A[i];
            end = end + A[i];
        }

        while (start <= end) {
            int middle = (start + end) / 2;
            int sum = 0;
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (sum + A[i] > middle) {
                    count++;
                    sum = 0;
                }
                sum = sum + A[i];
            }
            if (sum != 0) {
                count++;
            }
            if (count > M)
                start = middle + 1;
            else
                end = middle -1;
        }
        System.out.println(start);
    }
}
