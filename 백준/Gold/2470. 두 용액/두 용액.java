import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int ansAbs = 2000000000;
		int ans1 = -1;
		int ans2 = -1;

		// 1. 첫 번째 용액에 대해 최적쌍이 되는 두 번째 용액을 찾는다.
		for (int i = 0; i < N - 1; i++) {
			int pairValue = findOptimalPairValue(arr, i + 1, N - 1, arr[i]);
			int sumAbs = Math.abs(arr[i] + pairValue);
			if (ansAbs > sumAbs) {
				ansAbs = sumAbs;
				ans1 = arr[i];
				ans2 = pairValue;
			}
		}

		// 2. 두 용액의 합이 0에 가까운 쌍을 출력
		System.out.println(ans1 + " " + ans2);
	}

	static int findOptimalPairValue(int[] arr, int fromIndex, int toIndex, int value) {
		int optimalPairValue = 0;
		int optimalPairAbs = 2000000000;
		int l = fromIndex, r = toIndex;

		while (l <= r) {
			int m = (l + r) / 2;
			int sum = value + arr[m];
			int sumAbs = Math.abs(sum);
			if (sumAbs < optimalPairAbs) {
				optimalPairValue = arr[m];
				optimalPairAbs = sumAbs;
			}

			if (sum < 0) {
				l = m + 1;
			} else if (sum > 0) {
				r = m - 1;
			} else {
				return arr[m];
			}
		}
		return optimalPairValue;
	}
}
