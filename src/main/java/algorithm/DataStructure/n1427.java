package algorithm.DataStructure;

import java.util.Scanner;

public class n1427 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String s = scan.nextLine();
        int A[] = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            A[i] = Integer.parseInt(s.substring(i, i + 1));
        }

        for (int i = 0; i < s.length(); i++) {
            int max = i;
            for (int j = i + 1; j < s.length(); j++) {
                if (A[max] < A[j]) {
                    max = j;
                }
            }
            if (A[i] < A[max]) {
                int tmp = A[i];
                A[i] = A[max];
                A[max] = tmp;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            System.out.print(A[i]);
        }

    }
}
