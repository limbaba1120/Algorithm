package ch5;

import java.util.Scanner;

public class n9086 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for (int i = 0; i < T; i++) {
            String a = scan.next();
            System.out.print(a.charAt(0) + "" + a.charAt(a.length() - 1));
            System.out.println();
        }
    }
}
