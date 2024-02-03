package algorithm.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int A[] = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (K >= A[i]) {
                count = count + (K / A[i]);
                K = K % A[i];
            }
        }
        System.out.println(count);
    }


}
