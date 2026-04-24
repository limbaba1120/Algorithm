package algorithm.DataStructure;

import java.io.*;
import java.util.StringTokenizer;

public class n2293 {

    public static int count = 0;
    public static int[] coin, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        int k= Integer.parseInt(st.nextToken());

        coin = new int[n];
        dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= coin[i]) {
                    dp[j] += dp[j - coin[i]];
                }
            }
        }

        bw.write(dp[k] + "\n");
        bw.flush();






    }
}
