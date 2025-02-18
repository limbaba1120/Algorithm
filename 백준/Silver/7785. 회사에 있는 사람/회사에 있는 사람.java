
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		scan.nextLine();

		Set<String> set = new HashSet<>();

		while (N-- > 0) {
			String name = scan.next();
			String cond = scan.next();

			if (cond.equals("enter")) {
				set.add(name);
			}

			if (cond.equals("leave")) {
				set.remove(name);
			}
		}

		ArrayList<String> list = new ArrayList<>(set);

		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});

		for (String name : list) {
			System.out.println(name);
		}
	}
}
