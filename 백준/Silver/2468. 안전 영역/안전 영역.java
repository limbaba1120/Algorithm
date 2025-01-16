import java.util.Scanner;

public class Main {

	static int N;
	static int[][] floodArea;
	static boolean[][] isVisited;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		floodArea = new int[N + 1][N + 1];

		int maxHeight = setHeightInfo(scan);

		int maxArea = 0;

		for (int height = 0; height <= maxHeight; height++) { // height = 0부터 시작
			isVisited = new boolean[N + 1][N + 1];
			int count = 0;

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (!isVisited[i][j] && floodArea[i][j] > height) {
						DFS(i, j, height);
						count++;
					}
				}
			}
			maxArea = Math.max(maxArea, count);
		}

		System.out.println(maxArea);
	}

	private static void DFS(int x, int y, int height) {
		isVisited[x][y] = true;

		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];

			if (nx < 1 || ny < 1 || nx > N || ny > N) {
				continue;
			}

			if (!isVisited[nx][ny] && floodArea[nx][ny] > height) {
				DFS(nx, ny, height);
			}
		}
	}

	private static int setHeightInfo(Scanner scan) {
		int max = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				floodArea[i][j] = scan.nextInt();
				max = Math.max(max, floodArea[i][j]);
			}
		}

		return max;
	}
}
