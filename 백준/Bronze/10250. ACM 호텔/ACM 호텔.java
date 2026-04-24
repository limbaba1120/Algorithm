
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int T = scan.nextInt();

		while (T-- > 0) {
			int H = scan.nextInt();
			int W = scan.nextInt();
			int N = scan.nextInt();

			int floor = (N - 1) % H + 1; // floor level
			int roomNum = (N - 1) / H + 1; // room number

			System.out.printf("%d%02d", floor, roomNum);
			System.out.println();
		}
	}
}
