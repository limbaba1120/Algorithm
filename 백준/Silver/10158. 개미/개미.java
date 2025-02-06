
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 문제: W x H 격자 공간에서 대각선으로 이동하는 개미의 T 시간 후 위치
 * 제한 : 2 < w, h <= 40000
 * 		1 <= t <= 200,000,000
 *
 * 시간 제한: 0.15초 (추가 시간 없음)
 */
public class Main {
	/*public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int W = scan.nextInt();
		int H = scan.nextInt();
		int P = scan.nextInt();
		int Q = scan.nextInt();
		int T = scan.nextInt();

		int currentX = (T + P) % (2 * W);
		int currentY = (T + Q) % (2 * H);
		if (currentX > W) currentX = 2 * W - currentX;
		if (currentY > H) currentY = 2 * H - currentY;

		System.out.println(currentX + " " + currentY);
	}
*/

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());


		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());

		int timeX = T % (2 * W);
		int currentX = P;
		int deltaX = 1;
		while (timeX-- > 0) {
			if (currentX == W) {
				deltaX = -1;
			} else if (currentX == 0) {
				deltaX = 1;
			}
			currentX += deltaX;
		}

		int timeY = T % (2 * H);
		int currentY = Q;
		int deltaY = 1;
		while (timeY-- > 0) {
			if (currentY == H) {
				deltaY = -1;
			} else if (currentY == 0) {
				deltaY = 1;
			}
			currentY += deltaY;
		}

		System.out.println(currentX + " " + currentY);



	}


}

/*
 * 시간복잡도: 입력 크기와 알고리즘간의 관계
 * - Big-O
 * - 알고리즘의 복잡도를 나타내는 지표 중 하나
 * - 입력 크기에 대해 프로그램의 동작시간을 가늠해볼 수 있는 수단
 */