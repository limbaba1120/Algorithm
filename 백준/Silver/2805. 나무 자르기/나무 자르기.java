
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int M = scan.nextInt();
		int[] h = new int[N];

		for (int i = 0; i < N; i++) {
			h[i] = scan.nextInt();
		}

		// 1. 절단기 높이의 탐색 범위를 정한다
		int l = 0, r = 1000000000, ans = -1;

		while (l <= r) {
			// 2. 임의의 절단기 높이에 대해
			// 2-1. 원하는만큼 나무를 가져갈 수 있다면 높이를 높여본다
			// 2-2. 원하는만큼 가져갈 수 없다면 높이를 낮춰준다
			int m = (l + r) / 2;
			if (isPossible(h, m, M)) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		// 3. 원하는만큼 가져갈 수 있었던 높이 중 최댓값을 출력한다
		System.out.println(ans);
	}

	static boolean isPossible(int[] ht, int cutH, int threshold) {
		long sum = 0;
		for (int h : ht) {
			if (h > cutH) {
				sum = sum + h - cutH;
			}
		}
		return sum >= threshold;
	}
}
