
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		long M = scan.nextInt();

		long[] arr = new long[N];

		for (int i = 0; i < N; i++) {
			arr[i] = scan.nextLong();
		}

		Arrays.sort(arr);

		int rightIndex = 0;
		long ansDiff = arr[N - 1] - arr[0];

		for (int i = 0; i < N; i++) {
			while (arr[rightIndex] - arr[i] < M && rightIndex < N - 1) {
				rightIndex++;
			}
			long diff = arr[rightIndex] - arr[i];
			if (diff >= M) {
				ansDiff = Math.min(ansDiff, diff);
			}
		}

		System.out.println(ansDiff);

		/*for (int i = 0; i < N; i++) {
			int l = i, r = N - 1;
			while (l <= r) {
				int m = (l + r) / 2;
				int diff = arr[m] - arr[i];
				if (diff >= M) {
					ansDiff = Math.min(ansDiff, diff);
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
		}*/
	}
}
