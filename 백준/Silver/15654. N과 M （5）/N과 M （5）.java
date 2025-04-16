
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
	static boolean[] check;
	static int[] arr;
	static int[] output;
	static int n, m;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();

		arr = new int[n];
		check = new boolean[n];
		output = new int[m];

		for (int i = 0; i < n; i++) {
			arr[i] = scan.nextInt();
		}

		Arrays.sort(arr);

		permutation(0);
	}

	public static void permutation(int depth) {
		// base case
		if (depth == m) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < m; i++) {
				sb.append(output[i] + " ");
			}
			System.out.println(sb);
			return;
		}

		// recursive case
		for (int i = 0; i < n; i++) {
			if (!check[i]) {
				check[i] = true;
				output[depth] = arr[i];
				permutation(depth + 1);
				check[i] = false;
			}
		}






	}
}
