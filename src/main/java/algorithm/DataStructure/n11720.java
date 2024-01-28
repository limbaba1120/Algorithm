package algorithm.DataStructure;

import java.util.Scanner;

public class n11720 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        String sNum = scan.next();
        char[] cNum = sNum.toCharArray();
        int sum = 0;

        for (int i = 0; i < cNum.length; i++) {
            sum += cNum[i] - '0'; // or -48
        }
        System.out.println(sum);
    }
}
