
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int X = Integer.parseInt(br.readLine());

		boolean[] exist = new boolean[10000001];
		for (int i = 0; i < N; i++) {
			exist[arr[i]] = true;
		}

		int count = 0;

		for (int i = 0; i < N; i++) {
			int pairValue = X - arr[i];

			if (1 <= pairValue && pairValue <= 1000000) {
				if (exist[pairValue]) {
					count++;
				}
			}
		}
		System.out.println(count/2);
	}
}
