package ch3;

import java.util.Scanner;
/*
* 2739번
* N을 입력받은 뒤, 구구단 N단을 출력하는 프로그램을 작성하시오. 출력 형식에 맞춰서 출력하면 된다.
* */
public class GuGuDan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N;

        N = scan.nextInt();

        for (int i = 1; i < 10; i++) {
            System.out.printf("%d * %d = %d\n", N, i, N * i);
        }
    }
}
