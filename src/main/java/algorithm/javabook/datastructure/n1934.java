package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n1934 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for (int i = 0; i < T; i++) {
            int s = scan.nextInt();
            int e = scan.nextInt();

            int result = GCD(s, e);

            System.out.println(s*e/result);
        }
    }

    public static int GCD(int s, int e) {
        if (e == 0) {
            return s;
        } else {
            return GCD(e, s % e);
        }
    }
}
