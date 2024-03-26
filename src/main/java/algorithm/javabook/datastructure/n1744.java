package algorithm.javabook.datastructure;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class n1744 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        PriorityQueue<Integer> plusPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusPQ = new PriorityQueue<>();
        int one_count = 0;
        int zero_count = 0;

        for (int i = 0; i < N; i++) {
            int num = scan.nextInt();
            if (num >= 0) {
                if (num == 1) {
                    one_count++;
                    continue;
                }
                if (num == 0) {
                    zero_count++;
                    continue;
                }
                plusPQ.add(num);
            } else if (num < 0) minusPQ.add(num);
        }

        int sum = 0;
        while (plusPQ.size() > 1) {
            int data1 = plusPQ.remove();
            int data2 = plusPQ.remove();
            sum += (data1 * data2);
        }
        if (!plusPQ.isEmpty()) {
            int data3 = plusPQ.remove();
            sum += data3;
        }

        while (minusPQ.size() > 1) {
            int data3 = minusPQ.remove();
            int data4 = minusPQ.remove();
            sum += (data3 * data4);
        }
        if (!minusPQ.isEmpty()) {
            if (zero_count == 0) {
                sum = sum + minusPQ.remove();
            }
        }
        sum = sum + one_count;
        System.out.println(sum);
    }
}
