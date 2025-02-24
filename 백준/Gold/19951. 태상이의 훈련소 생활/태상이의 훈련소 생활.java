
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] origin = new int[N + 2];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			origin[i] = Integer.parseInt(st.nextToken());
		}

		int[] delta = new int[N + 2];
		int[] accDelta = new int[N + 2];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			delta[start] += height;
			delta[end + 1] -= height;
		}

		for (int i = 1; i <= N; i++) {
			accDelta[i] = accDelta[i - 1] + delta[i];
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(origin[i] + accDelta[i] + " ");
		}
	}
}
