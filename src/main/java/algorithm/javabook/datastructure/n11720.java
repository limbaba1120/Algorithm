package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n11720 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String m = scan.next();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(String.valueOf(m.charAt(i)));
        }
        System.out.println(sum);

    }
}
