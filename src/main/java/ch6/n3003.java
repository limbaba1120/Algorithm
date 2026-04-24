package ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n3003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] black = new int[]{1, 1, 2, 2, 2, 8};
        int[] white = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 6; i++) {
            white[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < black.length; i++) {
            System.out.print(black[i]-white[i] + " ");
        }
    }

}
