
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = scan.nextInt();
		}

		Arrays.sort(arr);

		int[] sums = new int[N * N];
		int sumIndex = 0;

		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				sums[sumIndex++] = arr[i] + arr[j];
			}
		}

		Arrays.sort(sums);  // sums 배열을 정렬해야 이진 탐색 가능

		int ans = -1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) { // 같은 인덱스를 빼는 경우를 제외
					int target = arr[i] - arr[j];
					if (isExist(sums, target)) {
						ans = Math.max(ans, arr[i]);
					}
				}
			}
		}

		System.out.println(ans);
	}

	static boolean isExist(int[] arr, int k) {
		int l = 0;
		int r = arr.length - 1;

		while (l <= r) {
			int m = (l + r) / 2;
			if (arr[m] < k) {
				l = m + 1;
			} else if (arr[m] > k) {
				r = m - 1;
			} else return true;
		}
		return false;
	}
}