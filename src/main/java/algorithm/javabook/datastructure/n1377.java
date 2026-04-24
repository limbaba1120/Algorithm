package algorithm.javabook.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Data[] A = new Data[N];

        for (int i = 0; i < N; i++) {
            A[i] = new Data(Integer.parseInt(br.readLine()), i);
        }
        Arrays.sort(A);

        int Max = 0;
        for (int i = 0; i < N; i++) {
            if (Max < A[i].index - i) {
                Max = A[i].index - i;
            }
        }
        System.out.println(Max+1);
    }

    static class Data implements Comparable<Data> {

        int value;
        int index;

        public Data(int value, int index) {
            super();
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Data o) {
            return this.value - o.value;
        }
    }


}
