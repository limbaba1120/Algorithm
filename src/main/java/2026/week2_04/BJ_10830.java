package main.java.week2_04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ_10830 {

    static int N;
    static final int MOD = 1000;

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
        return result;
    }

    static long[][] power(long[][] matrix, long exp) {
        if (exp == 1) return matrix;

        long[][] half = power(matrix, exp / 2);
        long[][] result = multiply(half, half);

        if (exp % 2 == 1)
            result = multiply(result, matrix);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[][] matrix = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken()) % MOD;
            }
        }

        long[][] result = power(matrix, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j > 0) sb.append(' ');
                sb.append(result[i][j]);
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
