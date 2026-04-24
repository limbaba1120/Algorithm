
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int C = scan.nextInt();
		int[] xs = new int[N];
		for (int i = 0; i < N; i++) {
			xs[i] = scan.nextInt();
		}

		Arrays.sort(xs);

		// 1. 공유기 사이의 거리 탐색 범위를 정한다
		int l = 1, r = xs[xs.length - 1] - xs[0], ans = -1;
		while (l <= r) {
			// 2. 인접한 공유기 사이의 거리에 대해
			// 2-1. 해당 거리를 지키면서 모든 공유기를 설치할 수 있다면 공유기 사이 거리를 늘려본다
			// 2-2. 모든 공유기를 설치하는게 불가능하다면 공유기 사이 거리를 좁혀본다
			int m = (l + r) / 2;
			if (calculateCount(xs, m) >= C) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		// 3. 가능한 공유기 사이의 최대 거리를 출력
		System.out.println(ans);
	}

	static int calculateCount(int[] xs, int distance) {
		int count = 1;
		int pastX = xs[0];

		for (int i = 1; i < xs.length; i++) {
			if (xs[i] - pastX >= distance) {
				count++;
				pastX = xs[i];
			}
		}


		return count;
	}
}
