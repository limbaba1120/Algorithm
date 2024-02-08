package algorithm.graph;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class n2252 {
     static ArrayList<Integer>[] A;
//    static ArrayList<ArrayList<Integer>> A;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        A = new ArrayList[N + 1];
//        A = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

//        for (int i = 0; i <= N; i++) {
//            A.add(new ArrayList<>());
//        }

        int[] indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            int S = scan.nextInt();
            int E = scan.nextInt();
            A[S].add(E);
//            A.get(S).add(E);
            indegree[E]++; //진입차수 배열 데이터 저장 부분
        }
        //위상정렬 실행
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");
            //A.get(now)
            for (int next : A[now]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}
