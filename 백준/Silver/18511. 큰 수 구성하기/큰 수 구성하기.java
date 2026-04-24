
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] num;
	static int N;
	static int K;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");

		num = new int[K];

		for (int i = 0; i < K; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(num);
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int now) {
		if (now > N) return;

		if (answer < now) answer = now;

		for (int i = K - 1; i > -1; i--) {
			dfs(now * 10 + num[i]);
		}
	}

}
