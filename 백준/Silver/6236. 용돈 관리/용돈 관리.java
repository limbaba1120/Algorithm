
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int M = scan.nextInt();
		int[] useAmounts = new int[N];
		for (int i = 0; i < N; i++) {
			useAmounts[i] = scan.nextInt();
		}

		// 1. 한 번에 인출한 금액의 탐색 범위를 정한다.
		int l = 1, r = N * 10000, ans = -1;
		while (l <= r) {
			int m = (l + r) / 2;
			// 2. 임의의 인출 금액에 대해
			// 2-1. 해당 금액으로 M번 이하로 출금할 수 있다면 인출액을 줄여본다
			// 2-2. M번 이하로 출금하는게 불가능하다면 인출액을 늘려본다
			if (isPossible(useAmounts, m, M)) {
				r = m - 1;
				ans = m;
			} else {
				l = m + 1;
			}
		}

		// 3. 가능한 인출액 중 최소 금액을 출력
		System.out.println(ans);
	}

	static boolean isPossible(int[] useAmounts, int drawAmount, int maxDrawCount) {
		int drawCount = 1;
		int currentAmount = drawAmount;
		for (int useAmount : useAmounts) {
			if (useAmount > drawAmount) {
				return false;
			}

			if (useAmount > currentAmount) {
				if (drawCount == maxDrawCount) {
					return false;
				}
				drawCount++;
				currentAmount = drawAmount;
			}
			currentAmount -= useAmount;
		}
		return true;
	}
}
