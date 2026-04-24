
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int S = scan.nextInt();

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = scan.nextInt();
		}

		int ansLength = N + 1;
		int rightIndex = 0;
		int currentSum = arr[0];

		for (int i = 0; i < N; i++) {
			while (currentSum < S && rightIndex < N - 1) {
				currentSum += arr[++rightIndex];
			}
			if (currentSum >= S) {
				ansLength = Math.min(ansLength, rightIndex - i + 1);
			}
			currentSum -= arr[i];
		}
		if (ansLength > N) {
			System.out.println(0);
		} else {
			System.out.println(ansLength);
		}
	}
}
