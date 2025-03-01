
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int M = scan.nextInt();

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = scan.nextInt();
		}

		long l = 1, r = (1L << 31) - 1, ans = -1;

		while (l <= r) {
			long m = (l + r) / 2;
			if (isPossible(arr, m) >= M) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		System.out.println(ans);
	}

	static int isPossible(int[] arr, long m) {
		int count = 0;

		for (int a : arr) {
			count += a / m;
		}
		return count;
	}
}
