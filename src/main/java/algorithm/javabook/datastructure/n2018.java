package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n2018 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int sum = 1, start_index = 1, end_index = 1, count = 1;
        int[] A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = i;
        }

        while (end_index != N) {
            if (sum < N) {
                end_index++;
                sum += end_index;
            } else if (sum > N) {
                sum -= start_index;
                start_index++;
            } else {
                count++;
                end_index++;
                sum += end_index;
            }
        }
        System.out.println(count);
    }
}
