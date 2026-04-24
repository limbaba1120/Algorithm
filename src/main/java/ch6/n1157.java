package ch6;

import java.util.Scanner;

public class n1157 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[26];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        String s = scan.next().toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }
        int max = -1;
        char ch = '?';
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                ch = (char) (i + 65);
            } else if (arr[i] == max) {
                ch = '?';
            }
        }
        System.out.println(ch);
    }
}
