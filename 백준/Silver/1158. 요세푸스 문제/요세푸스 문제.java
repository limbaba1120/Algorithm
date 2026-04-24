import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int K = scan.nextInt();

		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		int[] ans = new int[N];
		int currentIndex = 0;

		for (int i = 0; i < N; i++) {
			currentIndex = (currentIndex + K - 1) % list.size();
			ans[i] = list.remove(currentIndex);
		}

		System.out.print("<");
		for (int i = 0; i < N; i++) {
			if (i == N - 1) {
				System.out.print(ans[i]);
				continue;
			}
			System.out.print(ans[i] + ", ");
		}
		System.out.println(">");

		//System.out.println("<" + Arrays.stream(ans).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + ">");
	}
}
