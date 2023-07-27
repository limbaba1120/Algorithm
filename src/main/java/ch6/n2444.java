package ch6;

import java.util.Scanner;

public class n2444 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= num-i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2*i-1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 1; i < num; i++) {
            for (int j = num - i; j < num; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2 * num - 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
