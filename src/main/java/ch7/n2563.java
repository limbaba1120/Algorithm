package ch7;

import java.util.Scanner;

public class n2563 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int arr[][] = new int[100][100];

        int num = scan.nextInt();

        for (int i = 0; i < num; i++) {
            int X = scan.nextInt();
            int Y = scan.nextInt();
            for (int x = X; x < X + 10; x++) {
                for (int y = Y; y < Y + 10; y++) {
                    arr[x][y] = 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr[i][j] == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
