

import java.util.*;

public class Main {
    static int visited[];
    static ArrayList<Integer>[] A;
    static List<Integer> answer;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt(); // 도시의 개수
        int M = scan.nextInt(); // 도로의 개수
        int K = scan.nextInt(); // 거리 정보
        int X = scan.nextInt(); // 출발 도시의 번호
        A = new ArrayList[N + 1];
        answer = new ArrayList<>();
        visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= M; i++) {
            int S = scan.nextInt();
            int E = scan.nextInt();
            A[S].add(E);
        }

        for (int i = 0; i <= N; i++) {
            visited[i] = -1;
        }
        BFS(X);

        for (int i = 0; i <= N; i++) {
            if (visited[i] == K) {
                answer.add(i);
            }
        }

        if (answer.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(answer);
            for (int temp : answer) {
                System.out.println(temp);
            }
        }

    }

    public static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        visited[Node]++;
        while (!queue.isEmpty()) {
            int now_Node = queue.poll();
            for (int i : A[now_Node]) {
                if (visited[i] == -1) {
                    visited[i] = visited[now_Node] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
