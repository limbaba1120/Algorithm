
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		ArrayList<Meeting> list = new ArrayList<>();

		int N = scan.nextInt();

		for (int i = 0; i < N; i++) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			list.add(new Meeting(start, end));
		}

		Collections.sort(list);

		int count = 0;
		int end = -1;

		for (int i = 0; i < N; i++) {
			Meeting nowMeeting = list.get(i);

			if (nowMeeting.start >= end) {
				count++;
				end = nowMeeting.end;
			}
		}

		System.out.println(count);
	}

	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;

		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if (this.end == o.end) {
				return this.start - o.start;
			}
			return this.end - o.end;
		}
	}
}
