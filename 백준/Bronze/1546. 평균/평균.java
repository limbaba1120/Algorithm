import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int A[] = new int[n];
        double sum = 0;
        double max = 0;

        for (int i = 0; i < n; i++) {
            A[i] = scan.nextInt();
        }

        for (int i = 0; i < n; i++) {
            if (max < A[i]) {
                max = A[i];
            }
        }

        for (int i = 0; i < n; i++) {
            sum += (A[i] / max) * 100.0;
        }
        System.out.println(sum/n);


    }
}
