
/*
 * 문제: 원형으로 연결된 N개의 지점 중 두 곳에 탑을 세울 때 가능한 거리의 최댓값
 */

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();

		int[] distance = new int[N];
		int distanceSum = 0;
		for (int i = 0; i < N; i++) {
			distance[i] = scan.nextInt();
			distanceSum += distance[i];
		}

		int pairIndex = 0;
		int distClockWise = 0;
		int distCounterClockWise = distanceSum - distClockWise;
		int ans = 0;
		for (int i = 0; i < N; i++) {
			while (distClockWise < distCounterClockWise) {
				distClockWise += distance[pairIndex];
				distCounterClockWise -= distance[pairIndex];
				pairIndex = (pairIndex + 1) % N;
			}
			ans = Math.max(ans, distCounterClockWise);

			distClockWise -= distance[i];
			distCounterClockWise += distance[i];
		}
		System.out.println(ans);
	}
}

