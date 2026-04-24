
import java.util.Scanner;

/*
 * 문제 : N X M 바둑판에서 1시간마다 각 칸의 중심으로 주위 (2K+1)*(2K+1) 생명에 따라 아래 상황이 발생할 때, T 시간 뒤 생명현황
 * 생존 : 만약 현재 칸에 생명이 있고, 주위에 a개 이상 b개 이하의 생명이 있다면 현재 칸의 생명은 다음 단계에 살아남는다.
 * 고독 : 만약 현재 칸에 생명이 있고, 주위에 a개 미만의 생명이 있다면 현재 칸의 생명은 외로워서 죽는다.
 * 과밀 : 만약 현재 칸에 생명이 있고, 주위에 b개 초과의 생명이 있다면 현재 칸의 생명은 과밀로 죽는다.
 * 탄생 : 만약 현재 칸에 생명이 없고, 주위에 a개 초과 b개 이하의 생명이 있다면 다음 단계에서 현재 칸에 생명이 생긴다.
 */

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int M = scan.nextInt();
		int T = scan.nextInt();
		int K = scan.nextInt();
		int a = scan.nextInt();
		int b = scan.nextInt();

		char[][] map = new char[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String rowMap = scan.next();
			for (int j = 1; j <= M; j++) {
				map[i][j] = rowMap.charAt(j - 1);
			}
		}

		// T 시간동안 각 칸은 시간마다 반복
		while (T-- > 0) {
			int[][] acc = getPrefixSum(map);
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					int nearAlive = getRangeSum(acc, i, j, K);
					if (map[i][j] == '*') {
						nearAlive--;
						if (nearAlive < a || b < nearAlive) {
							map[i][j] = '.';
						}
					} else if (a < nearAlive && nearAlive <= b) {
						map[i][j] = '*';
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	static int[][] getPrefixSum(char[][] map) {
		int N = map.length - 1;
		int M = map[0].length - 1;

		int[][] acc = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				acc[i][j] = acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1] + (map[i][j] == '*' ? 1 : 0);
			}
		}
		return acc;
	}

	static int getRangeSum(int[][] acc, int r, int c, int k) {
		int r1 = Math.max(r - k, 1);
		int c1 = Math.max(c - k, 1);
		int r2 = Math.min(r + k, acc.length - 1);
		int c2 = Math.min(c + k, acc[0].length - 1);
		return acc[r2][c2] - acc[r2][c1-1] - acc[r1-1][c2] + acc[r1-1][c1-1];
	}
}
