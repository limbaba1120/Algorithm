
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int answer = 0;

		String str1 = scan.nextLine();
		String str2 = scan.nextLine();

		int[] countA = getAlphabetCount(str1);
		int[] countB = getAlphabetCount(str2);


		int ans = 0;

		for (int i = 0; i < 26; i++) {
			ans += Math.abs(countA[i] - countB[i]);
		}

		System.out.println(ans);
	}

	public static int[] getAlphabetCount(String str) {
		int[] count = new int[26];

		for (int i = 0; i < str.length(); i++) {
			count[str.charAt(i)-'a']++;
		}

		return count;
	}
}
