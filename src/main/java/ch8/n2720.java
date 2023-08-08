package ch8;

import java.util.Scanner;

public class n2720 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for (int i = 0; i < T; i++) {
            int C = scan.nextInt();
            int Q = C / 25;
            C %= 25;
            int D = C / 10;
            C %= 10;
            int N = C / 5;
            C %= 5;
            int P = C / 1;
            C %= 1;
            System.out.print(Q + " " + D + " " + N + " " + P);
            System.out.println();
        }
    }
}
