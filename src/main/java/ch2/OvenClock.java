package ch2;

import java.util.Scanner;

public class OvenClock {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int H, M, C;
        H = scan.nextInt();
        M = scan.nextInt();
        C = scan.nextInt();

        H += C / 60;
        M += C % 60;

        if (M >= 60) {
            H += 1;
            M -= 60;
        }
        if (H >= 24) {
            H -= 24;
        }
        System.out.println(H + " " + M);

    }
}
