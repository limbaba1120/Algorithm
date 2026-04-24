
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n, m;
	static boolean[] check;
	static int[] arr;
	static int[] output;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		n = scan.nextInt();
		m = scan.nextInt();

		check = new boolean[n];
		arr = new int[n];
		output = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scan.nextInt();
		}

		Arrays.sort(arr);

		permutation(0, 0);
	}

	public static void permutation(int depth, int start) {
		if (depth == m) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < m; i++) {
				sb.append(output[i] + " ");
			}
			System.out.println(sb);
			return;
		}

		for (int i = start; i < n; i++) {
			output[depth] = arr[i];
			permutation(depth + 1, i + 1);
		}


	}
}
