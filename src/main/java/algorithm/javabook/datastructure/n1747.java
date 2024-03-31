package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n1747 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        int[] A = new int[10000001];

        for (int i = 0; i < A.length; i++) {
            A[i] = i;
        }

        for (int i = 2; i < Math.sqrt(A.length); i++) {
            if (A[i] == 0) continue;

            for (int j = i + i; j < A.length; j+=i) {
                A[j] = 0;
            }
        }

        int i = N;

        while (true) {
            if (A[i] != 0) {
                int result = A[i];
                if (isPalindrome(result)) {
                    System.out.println(result);
                    break;
                }
            }
            i++;
        }

    }

    private static boolean isPalindrome(int result) {
        String str = String.valueOf(result);
        char[] c = str.toCharArray();

        int s = 0;
        int e = c.length-1;

        while (s < e) {
            if (c[s] == c[e]) {
                s++;
                e--;
            } else {
                return false;
            }
        }
        return true;
    }
}
