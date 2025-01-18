
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 1, 0, -1, 0, 0};
	static int[] dy = {0, 0, 1, 0, -1, 0};
	static int[] dh = {1, 0, 0, 0, 0, -1};
	static int h, n, m;
	static int[][][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		graph = new int[h][n][m];
		Queue<int[]> queue = new LinkedList<>();

		boolean allRipe = true; // 모든 토마토가 익어있는 상태인지 확인

		// 그래프 입력 및 익은 토마토 위치 추가
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < m; k++) {
					graph[i][j][k] = Integer.parseInt(st.nextToken());
					if (graph[i][j][k] == 1) {
						queue.offer(new int[]{i, j, k}); // 익은 토마토 위치 저장
					} else if (graph[i][j][k] == 0) {
						allRipe = false; // 익지 않은 토마토가 있으면 false로 설정
					}
				}
			}
		}

		// 저장될 때 모든 토마토가 익어있는 경우
		if (allRipe) {
			System.out.println(0);
			return;
		}

		// BFS 실행
		int days = bfs(queue);

		// 익지 않은 토마토가 있는지 확인
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (graph[i][j][k] == 0) {
						System.out.println(-1); // 익지 않은 토마토가 있으면 -1 출력
						return;
					}
				}
			}
		}

		// 결과 출력
		System.out.println(days);
	}

	private static int bfs(Queue<int[]> queue) {
		int maxDays = 0;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int nowH = current[0];
			int nowN = current[1];
			int nowM = current[2];

			for (int i = 0; i < 6; i++) {
				int nextH = nowH + dh[i];
				int nextN = nowN + dx[i];
				int nextM = nowM + dy[i];

				if (isValid(nextH, nextN, nextM) && graph[nextH][nextN][nextM] == 0) {
					graph[nextH][nextN][nextM] = graph[nowH][nowN][nowM] + 1;
					maxDays = Math.max(maxDays, graph[nextH][nextN][nextM]);
					queue.offer(new int[]{nextH, nextN, nextM});
				}
			}
		}

		return maxDays - 1; // 시작값이 1이므로 1을 빼줌
	}

	private static boolean isValid(int h, int n, int m) {
		return h >= 0 && h < graph.length && n >= 0 && n < graph[0].length && m >= 0 && m < graph[0][0].length;
	}
}
