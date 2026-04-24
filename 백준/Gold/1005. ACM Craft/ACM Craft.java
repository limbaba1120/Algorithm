
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
 * 문제 요구사항: 특정 건물을 짓는데 걸리는 시간
 * - dp[next] : next번째 건물이 완성되는데 필요한 시간
 * - dp[next] = max(dp[now]) + time[i];
 * - now 까지의 건물이 완성되는데 걸리는 시간 + {i}번째 건물이 완성되는데 걸리는 시간
 * - next와 now의 값은? -> 위상정렬을 통해 획득
 * - now: indegree가 0이라 큐에 들어가서 뽑힌 건물 번호
 * - next: now와 연결된 다른 건물들
 */
public class Main {

	static int T, n, m;
	static int[] time;
	static int[] dp;
	static int[] inDegree;
	static List<Integer>[] graph;


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		T = scan.nextInt();

		for (int t = 0; t < T; t++) {
			n = scan.nextInt();
			m = scan.nextInt();
			time = new int[n + 1];
			dp = new int[n + 1];
			inDegree = new int[n + 1];
			graph = new ArrayList[n + 1];

			for (int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<>();
				time[i] = scan.nextInt();
			}

			for (int i = 0; i < m; i++) {
				int start = scan.nextInt();
				int end = scan.nextInt();

				graph[start].add(end);
				inDegree[end]++;
			}

			Queue<Integer> queue = new LinkedList<>();
			for (int i = 1; i <= n; i++) {
				if (inDegree[i] == 0) {
					queue.add(i);
					dp[i] = time[i];
				}
			}
			while (!queue.isEmpty()) {
				int now = queue.poll();

				for (int next : graph[now]) {
					inDegree[next]--;
					dp[next] = Math.max(dp[next], dp[now] + time[next]);

					if (inDegree[next] == 0) {
						queue.add(next);
					}
				}
			}
			int w = scan.nextInt();
			System.out.println(dp[w]);
		}
	}

}
