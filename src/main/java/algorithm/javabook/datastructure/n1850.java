package algorithm.javabook.datastructure;

import java.io.*;
import java.util.StringTokenizer;

public class n1850 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        long N = Long.parseLong((st.nextToken()));
        long M = Long.parseLong(st.nextToken());

        int result = (int) gcd(N, M);

        for (int i = 0; i < result; i++) {
            sb.append(1);
        }
        System.out.println(sb.toString());
    }

    public static long gcd(long N, long M) {
        if (M == 0) {
            return N;
        } else {
            return gcd(M, N % M);
        }
    }
}
