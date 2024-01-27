
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int A[] = new int[N];

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 이진 탐색으로 M개의 수가 N개의 정수 배열에 존재하는지 확인 (이렇게 바로 해도됨)
            //            int in = Arrays.binarySearch(arr, num);

            if (binarySearch(A, num) >= 0) {
                sb.append(1 + "\n");
            } else {
                sb.append(0 + "\n");
            }
        }
        System.out.println(sb);
    }

    private static int binarySearch(int[] A, int target) {
        int low = 0;
        int high = A.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (target < A[mid]) {
                high = mid - 1;
            } else if (target > A[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
