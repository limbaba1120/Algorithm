
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] a = new int[N];
		int[] b = new int[M];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		int[] ans = new int[N + M];

		int aIndex = 0;
		int bIndex = 0;
		int ansIndex = 0;

		while (aIndex < N && bIndex < M) {
			if (a[aIndex] > b[bIndex]) {
				ans[ansIndex++] = b[bIndex++];
			} else {
				ans[ansIndex++] = a[aIndex++];
			}
		}

		while (aIndex < N) {
			ans[ansIndex++] = a[aIndex++];
		}

		while (bIndex < M) {
			ans[ansIndex++] = b[bIndex++];
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i : ans) {
			bw.write(i + " ");
		}
		bw.flush();
		
	}
}
