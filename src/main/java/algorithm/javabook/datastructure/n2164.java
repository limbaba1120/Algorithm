package algorithm.javabook.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class n2164 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        Queue<Integer> myQ = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            myQ.add(i);
        }
        while (myQ.size() > 1) {
            myQ.poll();
            int s = myQ.poll();
            myQ.add(s);
        }
        System.out.println(myQ.poll());
    }
}
