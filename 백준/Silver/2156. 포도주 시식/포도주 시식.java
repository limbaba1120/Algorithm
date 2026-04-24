
/*
 * N개의 잔에 포도주가 담겨있음 (포도주 양: <= 1000)
 * 잔의 개수 (1 <= n <= 10000)
 *
 * 규칙
 * - 잔을 고르면 모두 마시고 원래위치에 두어야 함
 * - 연속으로 놓여있는 3잔을 마실 수 없다
 *
 * 목표: 최대한 합이 커지도록 선택
 *
 * 입력
 * 6
 * 6
 * 10
 * 13
 * 9
 * 8
 * 1
 * 출력
 * 33
 *
 * 문제 요구사항: 최대 포도주의 양
 * - d[n] = n번째 잔까지 선택한 최대 포도주의 양
 * - 조건에 따라서 점화식의 차원이 더 늘어날 수 있다.
 */

import java.util.Scanner;

public class Main {
	// 연속해서 3잔의 포도주를 선택할 수 없다
	// {N}번을 선택하지 않은 경우
	// {N-1}을 선택하지 않고 {N}번을 선택한 경우
	// {N-1}을 선택하고, {N}번을 선택한 경우
	// {N-2}, {N-1}, {N}을 모두 선택할 수는 없다
	// - [case 0] {N}번을 선택하지 않은 경우 : d[0][n] -> max(d[0][n-1], d[1][n-1], d[2][n-1]
	// - [case 1] {N-1}을 선택하지 않고 {N} 번을 선택한 경우: d[1][n] -> d[0][n-1] + wind[n]
	// - [case 2] {N-1}을 선택하고, {N}번을 선택한 경우: d[2][n] -> d[1][n-1] + wine[n]

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();

		int[] wine = new int[N + 1];
		int[][] d = new int[3][N + 1];
		for (int i = 1; i <= N; i++) {
			wine[i] = scan.nextInt();
		}

		d[0][1] = 0;
		d[1][1] = wine[1];
		d[2][1] = wine[1];

		for (int i = 2; i <= N; i++) {
			d[0][i] = Math.max(d[0][i - 1], Math.max(d[1][i - 1], d[2][i - 1]));
			d[1][i] = d[0][i - 1] + wine[i];
			d[2][i] = d[1][i - 1] + wine[i];
		}

		System.out.println(Math.max(d[0][N], Math.max(d[1][N], d[2][N])));
	}

}
