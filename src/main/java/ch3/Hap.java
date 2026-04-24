package ch3;

import java.util.Scanner;

/*
n이 주어졌을 때, 1부터 n까지 합을 구하는 프로그램을 작성하시오.
 */
public class Hap {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        int sum = 0;
        n = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
