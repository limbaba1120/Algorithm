package algorithm.javabook.datastructure;

import java.util.Arrays;
import java.util.Scanner;

public class n1253 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextLong();
        }

        Arrays.sort(arr);

        int count = 0;

        for (int i = 0; i < N; i++) {
            long K = arr[i];
            int s = 0;
            int e = N-1;
            while (s < e) {
                if (arr[s] + arr[e] == K) {
                    if (s != K && e != K) {
                        count++;
                        break;
                    } else if (s == K) {
                        s++;
                    } else if (e == K) {
                        e--;
                    }
                } else if (arr[s] + arr[e] < K) {
                    s++;
                } else {
                    e--;
                }
            }
        }
        System.out.println(count);
        scan.close();
    }
}
