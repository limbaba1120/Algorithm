
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제: 삼각수 Tn = N(N+1)/2로 정의 될 때, 주어진 K가 정확히 3개의 삼각수의 합으로 표현될 수 있는가?
 * 1. 모든 삼각수를 구한다
 * 2. 주어진 숫자를 세 개의 삼각수의 합으로 표현할 수 있는지 확인
 *
 */

public class Main {

	static boolean[] isEurekaNumber = new boolean[1001];

	public static void preprocess() {
		// 1. K보다 작은 삼각수를 모두 구한다
		int[] triangleNumbers = new int[50];
		int triangleNumberCount = 0;

		for (int i = 1; ; i++) {
			int triangleNumber = i * (i + 1) / 2;
			if (triangleNumber >= 1000) break;

			triangleNumbers[triangleNumberCount++] = triangleNumber;
		}
		// 2. 구해진 삼각수 세개의 합으로 K를 나타낼 수 있는지 확인

		boolean[] isSumOfTriangleNumber = new boolean[1001];
		for (int i = 0; i < triangleNumberCount; i++) {
			for (int j = 0; j < triangleNumberCount; j++) {
				int sum = triangleNumbers[i] + triangleNumbers[j];
				if (sum < 1000) isSumOfTriangleNumber[sum] = true;
			}
		}
		for (int i = 1; i < 1000; i++) {
			if (!isSumOfTriangleNumber[i]) {
				continue;
			}
			for (int j = 0; j < triangleNumberCount; j++) {
				int sum = i + triangleNumbers[j];
				if (sum <= 1000) isEurekaNumber[sum] = true;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// [3, 1000]
		// isEurekaNumber[]

		preprocess();

		while (N-- > 0) {
			int K = Integer.parseInt(br.readLine());

			System.out.println(isEurekaNumber[K] ? "1" : "0");
		}

	}

}
