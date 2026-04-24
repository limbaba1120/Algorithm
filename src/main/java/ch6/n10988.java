package ch6;

import java.util.Scanner;

public class n10988 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        char[] c = s.toCharArray();
        int pelen = 1;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == c[c.length-i-1]) {
                continue;
            } else {
                pelen = 0;
            }
        }
        System.out.println(pelen);
    }
}
