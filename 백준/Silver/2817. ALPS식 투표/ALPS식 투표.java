
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int X = scan.nextInt(); // 참가자 수
		int N = scan.nextInt(); // 참가한 스태프 수

		// 1. 전체 득표수의 5% 미만의 스태프를 후보에서 제외한다.
		double validCut = X * 0.05;
		boolean[] validCandidate = new boolean[26];
		int[] staffVote = new int[26];
		int candidateCount = 0;

		for (int i = 0; i < N; i++) {
			String name = scan.next();
			int vote = scan.nextInt();
			if (vote >= validCut) {
				int index = name.charAt(0) - 'A';
				validCandidate[index] = true;
				staffVote[index] = vote;
				candidateCount++;
			}
		}

		// 2. 남은 스태프마다 받은 득표수를 1~14로 나눈 점수 집합을 구한다.
		Score[] scores = new Score[candidateCount * 14];
		int scoreIndex = 0;
		for (int i = 0; i < 26; i++) {
			if (validCandidate[i]) {
				for (int j = 1; j <= 14; j++) {
					scores[scoreIndex++] = new Score(i, (double)staffVote[i] / j);
				}
			}
		}

		// 3. 전체 점수 집합에서 점수가 큰 1~14 번째 스태프에게 칩을 1개씩 지급한다.
		sortScoresDescendingOrder(scores);

		int[] ans = new int[26];

		for (int i = 0; i < 14; i++) {
			ans[scores[i].staffIndex]++;
		}

		// 4. 스태프 이름에 대해 사전순으로 후보 스태프와 받은 칩의 수를 출력
		for (int i = 0; i < 26; i++) {
			if (validCandidate[i]) {
				System.out.println((char)(i + 'A') + " " + ans[i]);
			}
		}
	}

	public static void sortScoresDescendingOrder(Score[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i].scr > arr[j].scr) {
					Score cur = arr[i];
					for (int k = i; k > j; k--) {
						arr[k] = arr[k - 1];
					}
					arr[j] = cur;
				}
			}
		}
	}
	static class Score {
		int staffIndex;
		double scr;

		public Score(int staffIndex, double scr) {
			this.staffIndex = staffIndex;
			this.scr = scr;
		}
	}
}


