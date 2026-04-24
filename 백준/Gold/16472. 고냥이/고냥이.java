
import java.util.Scanner;

public class Main {
	static int alphabetCount = 0;
	static int[] alphabetFrequency = new int[26];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();

		int nextIndex = 0;
		char[] alph = scan.next().toCharArray();
		int ans = 0;
		int L = alph.length;

		for (int i = 0; i < L; i++) {
			while (nextIndex < L) {
				increaseFrequency(alph[nextIndex++]);
				if (alphabetCount > N) {

					decraseFrequency(alph[--nextIndex]);
					break;
				}
			}
			ans = Math.max(ans, nextIndex - i);
			decraseFrequency(alph[i]);
		}

		/*for (int i = 0; i < length; i++) {

			while (nextIndex < length) {
				alphabetFrequency[alph[nextIndex++] - 'a']++;
				if (getAlphabetCount(alphabetFrequency) > N) {
					alphabetFrequency[alph[--nextIndex] - 'a']--;
					break;
				}
			}
			ans = Math.max(ans, nextIndex - i);
			alphabetFrequency[alph[i] - 'a']--;
		}*/

		System.out.println(ans);
	}

	static void increaseFrequency(char alph) {
		if (alphabetFrequency[alph - 'a']++ == 0) {
			alphabetCount++;
		}
	}

	static void decraseFrequency(char alph) {
		if (--alphabetFrequency[alph - 'a'] == 0) {
			alphabetCount--;
		}
	}

	static int getAlphabetCount(int[] alphabetFrequency) {
		int alphabetCount = 0;

		for (int i = 0; i < 26; i++) {
			if (alphabetFrequency[i] > 0) {
				alphabetCount++;
			}
		}
		return alphabetCount;
	}
}
