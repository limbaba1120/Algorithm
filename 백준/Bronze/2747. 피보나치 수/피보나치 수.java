
import java.util.Scanner;

public class Main {
	static int[] cache = new int[50];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		System.out.println(fibo(n));
	}

	static int fibo(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		if (cache[n] != 0) {
			return cache[n];
		}

		cache[n] = fibo(n - 1) + fibo(n - 2);
		return cache[n];
	}
}
