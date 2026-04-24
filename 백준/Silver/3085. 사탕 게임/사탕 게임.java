
import java.util.Scanner;

/*
 * 문제: 색이 다른 사탕이 인접한 두 칸을 골라 사탕을 서로 교환할 때,
 *      같은 색으로 이루어진 가장 긴 연속 부분 행/열의 최댓값
 * 1. 가능한 모든 쌍의 사탕을 서로 교환한다.
 * 2. 교환한 상태에서 가장 긴 연속 부분 행/열을 찾는다.
 * 3. 교환한 사탕을 원복한다.
 */
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();

		char[][] map = new char[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = scan.next().toCharArray();
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j + 1 < N && map[i][j] != map[i][j + 1]) {
					swapCandy(map, i, j, i, j + 1);
					ans = Math.max(ans, Math.max(maxColumn(map), maxRow(map)));
					swapCandy(map, i, j, i, j + 1);
				}

				if (i + 1 < N && map[i][j] != map[i + 1][j]) {
					swapCandy(map, i, j, i + 1, j);
					ans = Math.max(ans, Math.max(maxColumn(map), maxRow(map)));
					swapCandy(map, i, j, i+1, j);
				}
			}
		}
		System.out.println(ans);
	}

	public static void swapCandy(char[][] map, int nowX, int nowY, int nextX, int nextY) {
		char tmp = map[nowX][nowY];
		map[nowX][nowY] = map[nextX][nextY];
		map[nextX][nextY] = tmp;
	}

	// 행에서 연속된 같은 사탕의 최대 개수를 구하는 메서드
	public static int maxRow(char[][] map) {
		int N = map.length;
		int max = 1;  // 최소 1개는 있음
		for (int i = 0; i < N; i++) {
			int len = 1;
			for (int j = 1; j < N; j++) {
				if (map[i][j] == map[i][j - 1]) {
					len++;
				} else {
					len = 1;
				}
				max = Math.max(max, len);
			}
		}
		return max;
	}

	// 열에서 연속된 같은 사탕의 최대 개수를 구하는 메서드
	public static int maxColumn(char[][] map) {
		int N = map.length;
		int max = 1;
		for (int j = 0; j < N; j++) {
			int len = 1;
			for (int i = 1; i < N; i++) {
				if (map[i][j] == map[i - 1][j]) {
					len++;
				} else {
					len = 1;
				}
				max = Math.max(max, len);
			}
		}
		return max;
	}



}
