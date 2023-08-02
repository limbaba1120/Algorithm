package ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class n10798 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String arr[][] = new String[5][15];
        int col = 0;
        for (int i = 0; i < 5; i++) {
            String s = scan.nextLine();
            for (int j = 0; j < s.length(); j++) {
                arr[i][j] = String.valueOf(s.charAt(j));
            }
            if (col < s.length()) {
                col = s.length();
            }
        }
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[j][i] == null) {
                    continue;
                }
                System.out.print(arr[j][i]);
            }
        }
    }
}
