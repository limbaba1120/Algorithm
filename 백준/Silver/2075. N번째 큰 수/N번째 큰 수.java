
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			return o2 - o1;
		});

		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int m = scan.nextInt();
				pq.add(m);
			}
		}

		int now = 0;

		for (int i = 0; i < n; i++) {
			now = pq.poll();
		}

		System.out.println(now);

	}
}
