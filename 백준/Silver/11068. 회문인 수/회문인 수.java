
import java.util.Scanner;

/*
 * 문제 : 주어진 숫자가 [2, 64] 진법으로 표현했을 때 회문이 될 수 있는가?
 * 1. 가능한 모든 진법에 대해 진법을 변환한다
 * 2. 변환된 수가 회문이 될 수 있는지 확인한다
 */
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();

		while (T-- > 0) {
			int x = scan.nextInt();
			boolean ans = false;
			for (int i = 2; i <= 64; i++) {
				int[] digit = new int[20];
				int len = convertBase(x, i, digit);

				if (isPalindrome(digit, len)) {
					ans = true;
					break;
				}
			}
			System.out.println(ans ? "1" : "0");
		}
	}

	public static int convertBase(int x, int B, int[] digit) {
		int len = 0;
		while (x > 0) {
			digit[len++] = x % B;
			x /= B;
		}
		return len;
	}

	public static boolean isPalindrome(int[] digit, int length) {
		for (int i = 0; i < length / 2; i++) {
			if (digit[i] != digit[length - i - 1]) {
				return false;
			}
		}
		return true;
	}
}
