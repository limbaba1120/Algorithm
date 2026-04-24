
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int WARNING_ZONE = 1;
	static int DEATH_ZONE = -1;
	static int[][] zone = new int[501][501];
	static boolean[][] visited = new boolean[501][501];

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		setZone(WARNING_ZONE, br);

		setZone(DEATH_ZONE, br);

		int answer = bfs();

		System.out.println(visited[500][500] ? answer : -1);
	}

	private static int bfs() {
		Queue<Node> queue = new PriorityQueue<>();

		queue.add(new Node(0, 0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			if (node.x == 500 && node.y == 500) {
				return node.cost;
			}

			for (int k = 0; k < 4; k++) {
				int newX = node.x + dx[k];
				int newY = node.y + dy[k];

				if (newX < 0 || newY <0 || newX >= 501 || newY >= 501) {
					continue;
				}

				if (!visited[newX][newY] && zone[newX][newY] != DEATH_ZONE) {
					visited[newX][newY] = true;
					queue.add(new Node(newX, newY, node.cost + (zone[newX][newY] == WARNING_ZONE ? 1 : 0)));
				}
			}
		}
		return -1;
	}

	private static void setZone(int zoneNum, BufferedReader br) throws IOException {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
				for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
					zone[j][k] = zoneNum;
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

}