package ch8;

import java.util.Scanner;

public class n2292 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        int count = 1;
        int limit = 2;

        if (N == 1) {
            System.out.println(1);
        } else {
            while (limit <= N) {
                limit = limit + (count * 6);
                count++;
            }
            System.out.println(count);
        }
    }
}

