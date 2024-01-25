
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> Que = new PriorityQueue<>((o1, o2) -> {
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);

            if (first_abs == second_abs) {
                // 절댓값이 같은 경우 음수 우선
                // o1이 더 크다면 양수 반환 -> o1과 o2의 자리를 바꾼다
                // o1이 더 작다면 음수 반환 -> 지금의 자리를 유지한다
                return o1 > o2 ? 1 : -1;
            }

            // 절댓값 작은 데이터 우선
            // 양수가 반환되면 o1의 절댓값이 더 큰 경우이다 -> o2, o1순으로 정렬
            // 음수가 반환되면 o2의 절대값이 더 큰 경우이다 -> o1, o2 유지
            return first_abs - second_abs;
        });

        for (int i = 0; i < N; i++) {
            int request = Integer.parseInt(br.readLine());
            if (request == 0) {
                if (Que.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(Que.poll());
                }
            } else {
                Que.add(request);
            }
        }
    }
}
