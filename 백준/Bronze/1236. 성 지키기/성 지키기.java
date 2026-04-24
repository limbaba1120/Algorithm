
import java.sql.SQLOutput;
import java.util.Scanner;

/*
 * 모든 행과 열에 경비원이 최소 한 명씩 있어야할 때
 * 추가로 필요한 경비원의 최소 수
 * 1. 각 행/열에 대해 경비원이 있는지 확인
 * 2. 보호받지 못하는 행/열의 개수를 구한다
 * 3. 둘 중 큰 값을 출력
 */
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int M = scan.nextInt();

		char[][] map = new char[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = scan.next().toCharArray();
		}

		int existRowCount = 0;
		for (int i = 0; i < N; i++) {
			boolean exist = false;
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'X') {
					exist = true;
					break;
				}
			}
			if (exist)
				existRowCount++;
		}

		int existColCount = 0;
		for (int i = 0; i < M; i++) {
			boolean exist = false;
			for (int j = 0; j < N; j++) {
				if (map[j][i] == 'X') {
					exist = true;
					break;
				}
			}
			if (exist)
				existColCount++;
		}

		int needRowCount = N - existRowCount;
		int needColCount = M - existColCount;

		System.out.println(Math.max(needColCount, needRowCount));

	}
}
