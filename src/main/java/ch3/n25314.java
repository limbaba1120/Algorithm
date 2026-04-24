package ch3;

import java.util.Scanner;

public class n25314 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        int div = num / 4;

        for (int i = 0; i < div; i++) {
            System.out.printf("long ");
        }
        System.out.println("int");
    }
}
