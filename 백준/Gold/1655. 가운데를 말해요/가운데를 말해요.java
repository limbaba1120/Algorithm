
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 문제 요약
 * - 정수가 들어올 때마다, 모든 수들의 중앙값을 출력하는 문제
 * - 정수의 개수가 짝수개라면 작은 값을 출력
 * - 정수의 개수: N ( 1 <= N <= 100000)
 * 문제 분석
 * - 최대힙 / 최소힙은 모든 수 중에서 최소 / 최대 값을 빠르게 획득하는 자료구조인데 어떻게 중앙값을 얻냐?
 * 1. 두 개의 힙에 수를 번갈아가며 넣는다 (사이즈가 같다면 최대 힙 먼저
 * 2. 각 힙의 루트에서 수를 뽑은 뒤, 대소관계를 비교
 * 3. 오른쪽힙에 더 작은값이 있다면 위치를 서로 바꿈
 * 4. 중앙 값은 최대힙의 ROOT에 위치
 */
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		PriorityQueue<Integer> small = new PriorityQueue<>((o1, o2) -> {
			return o2 - o1;
		});

		PriorityQueue<Integer> big = new PriorityQueue<>();

		int N = scan.nextInt();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			int num = scan.nextInt();

			if (small.size() == big.size())
				small.offer(num);
			else
				big.offer(num);

			if (!small.isEmpty() && !big.isEmpty()) {
				if (small.peek() > big.peek()) {
					int tmp = big.poll();
					big.offer(small.poll());
					small.offer(tmp);
				}
			}
			sb.append(small.peek()).append("\n");
		}

		System.out.println(sb);
	}
}
