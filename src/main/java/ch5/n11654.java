package ch5;

import java.util.Scanner;

public class n11654 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        int[] resultArr = new int[a.length()];
        resultArr[0] = a.charAt(0);
        System.out.println(resultArr[0]);
    }
}
