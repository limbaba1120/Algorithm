


/*
 * 문제 요약
 * - N개의 회의의 {시작, 종료} 시각이 주어진다
 * - 한 회의실에서 동시에 두개의 회의가 진행될 수 없다
 * - 최소 몇 개의 회의실이 필요한지 카운트
 * - 단, 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다
 * 문제 분석
 * - 아직 회의가 끝나지 않았다면, 새로운 회의실이 필요
 * - 회의가 끝나는 시각을 기준으로, 가장 먼저 끝나는 회의를 찾을 수 있는 최소 힙을 만들어보자
 * - 새로운 회의의 시작 시각이, 가장 먼저 끝날 회의보다 뒤라면, 회의실을 재사용 가능
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		ArrayList<Meeting> meetings = new ArrayList<>();

		int N = scan.nextInt();

		for (int i = 0; i < N; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();

			meetings.add(new Meeting(a, b));
		}

		meetings.sort(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				if (o1.start == o2.start)
					return o1.end - o2.end;
				return o1.start - o2.start;
			}
		});

		PriorityQueue<Meeting> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.end - o2.end;
		});

		int answer = 1;


		for (Meeting m : meetings) {
			if (pq.isEmpty()) {
				pq.offer(m);
				continue;
			}
			while (!pq.isEmpty() && pq.peek().end <= m.start) {
				pq.poll();
			}

			pq.offer(m);
			answer = Math.max(answer, pq.size());
		}
		System.out.println(answer);

	}

	static class Meeting {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}
