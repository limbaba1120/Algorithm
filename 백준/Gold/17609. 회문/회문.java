
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();

		for (int i = 0; i < N; i++) {
			char[] c = scan.next().toCharArray();

			int firstIndex = 0;
			int lastIndex = c.length - 1;
			int count = 0;

			while (firstIndex < lastIndex) {
				if (c[firstIndex] != c[lastIndex]) {
					if (isPalindrome(firstIndex + 1, lastIndex, c) || isPalindrome(firstIndex, lastIndex - 1, c)) {
						count = 1;
						break;
					} else {
						count = 2;
						break;
					}
				}
				firstIndex++;
				lastIndex--;
			}

			if (count == 0) {
				System.out.println(0);
			} else if (count == 1) {
				System.out.println(1);
			} else {
				System.out.println(2);
			}
		}
	}

	static boolean isPalindrome(int l, int r, char[] c) {
		while (l < r) {
			if (c[l] != c[r]) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
}
