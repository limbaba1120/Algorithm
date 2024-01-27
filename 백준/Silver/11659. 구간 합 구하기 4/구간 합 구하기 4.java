
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 배열 크기
        int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

        int A[] = new int[N + 1];
        int S[] = new int[N + 1];

        S[0] = 0;

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            S[i] = S[i - 1] + A[i];
        }


        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine(), " ");
            int I = Integer.parseInt(st.nextToken());
            int J = Integer.parseInt(st.nextToken());

            sb.append(S[J] - S[I - 1]).append('\n');
        }
        System.out.println(sb);
    }
}
