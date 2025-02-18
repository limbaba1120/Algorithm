
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Map<String, Integer> map = new HashMap<>();

		int N = scan.nextInt();

		for (int i = 0; i < N; i++) {
			String name = scan.next();
			map.put(name, map.getOrDefault(name, 0) + 1);
		}

		String bestSeller = "";
		int maxCount = 0;

		for (Map.Entry<String, Integer> entrySet : map.entrySet()) {
			if (entrySet.getValue() > maxCount
				|| (entrySet.getValue() == maxCount) && entrySet.getKey().compareTo(bestSeller) < 0) {
				maxCount = entrySet.getValue();
				bestSeller = entrySet.getKey();
			}
		}

		System.out.println(bestSeller);

	}
}
