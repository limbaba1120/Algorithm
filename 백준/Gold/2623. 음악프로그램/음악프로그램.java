
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n, m;
	static List<Integer>[] list;
	static int[] inDegree;
	static boolean[] isVisited;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		n = scan.nextInt();
		m = scan.nextInt();

		list = new ArrayList[n + 1];
		inDegree = new int[n + 1];
		isVisited = new boolean[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int cnt = scan.nextInt();
			int front = scan.nextInt();

			for (int j = 2; j <= cnt; j++) {
				int back = scan.nextInt();
				inDegree[back]++;
				list[front].add(back);
				front = back;
			}
		}

		ArrayList<Integer> answer = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();
			isVisited[now] = true;
			answer.add(now);

			if (answer.size() > n) {
				System.out.println(0);
				return;
			}

			for (int next : list[now]) {
				if (isVisited[next]) {
					continue;
				}
				inDegree[next]--;
				if (inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			if (!isVisited[i]) {
				System.out.println(0);
				return;
			}
		}

		for (int ans : answer) {
			System.out.println(ans);
		}



	}
}
