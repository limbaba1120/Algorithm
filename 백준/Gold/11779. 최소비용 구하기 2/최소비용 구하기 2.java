
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int n, m, v1, v2;
	static ArrayList<Node>[] graph;
	static int[] cost;
	static boolean[] isVisited;
	static int[] path;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		graph = new ArrayList[n + 1];
		cost = new int[n + 1];
		isVisited = new boolean[n + 1];
		path = new int[n + 1];

		Arrays.fill(cost, Integer.MAX_VALUE);

		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int s = scan.nextInt();
			int d = scan.nextInt();
			int c = scan.nextInt();

			graph[s].add(new Node(d, c));
		}
		v1 = scan.nextInt();
		v2 = scan.nextInt();

		int answer = dijkstra(v1, v2);

		System.out.println(answer);

		Stack<Integer> stack = new Stack<>();
		int now = v2;
		while (now != 0) {
			stack.push(now);
			now = path[now];
		}

		System.out.println(stack.size());

		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		cost[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cost[cur.end] < cur.weight) {
				continue;
			}

			if (!isVisited[cur.end]) {
				isVisited[cur.end] = true;

				for (Node next : graph[cur.end]) {
					if (!isVisited[next.end] && cost[next.end] > cost[cur.end] + next.weight) {
						cost[next.end] = cost[cur.end] + next.weight;
						pq.add(new Node(next.end, cost[next.end]));
						path[next.end] = cur.end;
					}
				}
			}
		}

		return cost[end];
	}

	static class Node implements Comparable<Node> {
		int end;
		int weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node node) {
			return this.weight - node.weight;
		}
	}
}
