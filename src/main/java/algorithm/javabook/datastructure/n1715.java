package algorithm.javabook.datastructure;

import java.util.PriorityQueue;
import java.util.Scanner;

public class n1715 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int num = scan.nextInt();
            queue.add(num);
        }

        int data1 = 0;
        int data2 = 0;

        int sum = 0;

        while (queue.size() != 1) {
            data1 = queue.remove();
            data2 = queue.remove();

            sum += data1 + data2;
            queue.add(data1+data2);
        }

        System.out.println(sum);


    }
}
