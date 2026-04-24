package ch4;

import java.util.Scanner;

public class n10810 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int res[] = new int[n];
        int input[][] = new int[m][3];

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                input[i][j] = scan.nextInt();
            }
            for (int k = input[i][0] - 1; k <= input[i][1] - 1; k++) {
                res[k] = input[i][2];
            }
        }
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

}
