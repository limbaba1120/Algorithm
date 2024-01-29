
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static LinkedList<int[]> q;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = scan.nextInt();

        while (T-- > 0) {
            int N = scan.nextInt();
            int M = scan.nextInt();

            q = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                q.offer(new int[]{i, scan.nextInt()}); // i: 문서의 개수(초기위치), scan.nextInt: 중요도
            }

            int count = 0;

            while (!q.isEmpty()) {

                int[] front = q.poll(); //가장 첫 원소
                boolean isMax = true; // front 원소가 가장 큰 원소인지 확인

                for (int i = 0; i < q.size(); i++) {
                    // 처음 뽑은 원소보다 큐에 있는 i번째 원소가 중요도가 클 경우
                    if (front[1] < q.get(i)[1]) {
                        //뽑은 원소 및 i 이전의 원소들을 뒤로 보냄
                        q.offer(front);
                        for (int j = 0; j < i; j++) {
                            q.offer(q.poll());
                        }

                        //front 원소가 가장 큰 원소가 아니므로 false하고 탐색을 마침
                        isMax = false;
                        break;
                    }
                }
                //  front 원소가 가장 큰 원소가 아니므로 다음 반복문으로 넘어감
                if (isMax == false) {
                    continue;
                }

                // front 원소가 가장 큰 원소이면
                count++;
                // 찾고자 하는 문서의 중요도라면 테스트케이스 종료
                if (front[0] == M) {
                    break;
                }
            }
            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }

}
