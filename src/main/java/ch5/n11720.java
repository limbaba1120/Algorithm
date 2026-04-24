package ch5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int sum = 0;
        String[] N = br.readLine().split("");
        for (int i = 0; i < num; i++) {
            sum += Integer.parseInt(N[i]);
        }
        System.out.println(sum);
    }
}
