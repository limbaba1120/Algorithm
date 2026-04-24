
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		scan.nextLine();

		// HashSet을 사용하면 자동으로 중복제거 해줌
		// Set<String> set = new HashSet<>();
		// set.add(word)
		// ArrayList<String> list = new ArrayList<>(set);
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String word = scan.nextLine();

			if (!list.contains(word)) {
				list.add(word);
			}
		}

		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					return s1.compareTo(s2);
				}
				return Integer.compare(s1.length(), s2.length());
			}
		});

		for (String s : list) {
			System.out.println(s);
		}
	}
}
