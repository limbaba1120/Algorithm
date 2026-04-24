package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n1427 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();

        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = 0; i < str.length(); i++) {
            int Max = i;
            for (int j = i + 1; j < str.length(); j++) {
                if (arr[j] > arr[Max]) {
                    Max = j;
                }
            }
            if (arr[i] < arr[Max]) {
                int temp = arr[i];
                arr[i] = arr[Max];
                arr[Max] = temp;
            }
        }
        for (int i : arr) {
            System.out.print(i);
        }
    }
}
