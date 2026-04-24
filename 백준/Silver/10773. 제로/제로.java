
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int K = scan.nextInt();

        int arr[] = new int[K];

        int count = 0;
        int sum = 0;

        for (int i = 0; i < K; i++) {
            int N = scan.nextInt();

            if (N != 0) {
                arr[count] = N;
                count++;
            } else {
                arr[count-1] = 0;
                count--;
            }
        }

        for (int j = 0; j < K; j++) {
            sum += arr[j];
        }

        System.out.println(sum);
    }
}
