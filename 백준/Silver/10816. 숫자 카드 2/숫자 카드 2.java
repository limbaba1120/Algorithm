

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int A[] = new int[N];
        int count = 0;

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);


        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < M; i++) {
            int key = Integer.parseInt(st.nextToken());

            sb.append(upperBound(A, key) - lowerBound(A, key)).append('\n');
        }
        System.out.println(sb);


    }

    private static int lowerBound(int[] A, int key) {
        int lo = 0;
        int hi = A.length;

        // lo 가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (key <= A[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static int upperBound(int[] A, int key) {
        int lo = 0;
        int hi = A.length;

        // lo 가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (key < A[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
