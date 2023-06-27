package ch3;

import java.io.*;
import java.util.StringTokenizer;

public class n11022 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int a;
        int b;
        for (int i = 1; i <= num; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write("Case #" + i + ": " + a + " + " +  b + "=" + (a + b) + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
/*
BufferedWriter 없이 bw.write 안해도 되고 System.out.println으로 바로 출력 가능
 */
