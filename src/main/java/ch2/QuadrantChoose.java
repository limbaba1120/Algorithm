package ch2;

import java.util.Scanner;

/*
* 흔한 수학 문제 중 하나는 주어진 점이 어느 사분면에 속하는지 알아내는 것이다. 사분면은 아래 그림처럼 1부터 4까지 번호를 갖는다. "Quadrant n"은 "제n사분면"이라는 뜻이다.
* */
public class QuadrantChoose {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x, y;
        x = scan.nextInt();
        y = scan.nextInt();

        if ((x >= -1000 && x <= 1000 && x != 0) && (y <= 1000 && y >= -1000 && y != 0)) {
            if (x > 0 && y > 0) {
                System.out.println("1");
            } else if (x > 0 && y < 0) {
                System.out.println("4");
            } else if (x < 0 && y > 0) {
                System.out.println("2");
            } else {
                System.out.println("3");
            }
        }

    }

}
