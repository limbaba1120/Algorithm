package ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int fac = 1;
        int sum = 0;

        for (int i = N.length() - 1; i >= 0; i--) {
            char C = N.charAt(i);
            if ('A' <= C && C <= 'Z') {
                sum += (C - 'A' + 10)*fac;
            } else {
                sum += (C - '0') * fac;
            }
            fac *= B;
        }
        System.out.println(sum);

    }
}
