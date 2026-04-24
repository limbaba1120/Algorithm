package ch3;

import java.util.Scanner;

/*
10950번
두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
 */
public class AB3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T, num1, num2;
        T = scan.nextInt();
        int a[] = new int[T];

        for (int i = 0; i < T; i++) {
            num1 = scan.nextInt();
            num2 = scan.nextInt();
            a[i] = num1 + num2;
        }
        scan.close();
        for (int i = 0; i < T; i++) {
            System.out.println(a[i]);
        }

    }
}
