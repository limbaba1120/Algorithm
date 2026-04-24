package ch2;
/*
* 두 정수 A와 B가 주어질때, A와 B를 비교하는 프로그램 작성
* */

import java.util.Scanner;

public class TwoNumberCompare {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int A, B;
        A = scan.nextInt();
        B = scan.nextInt();

        if (A > B) {
            System.out.println(">");
        } else if (A < B) {
            System.out.println("<");
        } else {
            System.out.println("==");
        }
    }
}
