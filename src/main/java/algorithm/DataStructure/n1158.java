package algorithm.DataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

        public class n1158 {

            public static void main(String[] args) {
                Scanner scan = new Scanner(System.in);
                StringBuilder sb = new StringBuilder();

                int N = scan.nextInt();
                int K = scan.nextInt();

                Queue<Integer> que = new LinkedList<>();

                for (int i = 1; i <= N; i++) {
                    que.offer(i);
                }

                sb.append("<");

                while (que.size() != 1) {
                    for (int i = 0; i < K - 1; i++) {
                        que.offer(que.poll());
                    }
                    sb.append(que.poll()).append(", ");
                }
                // 마지막 값
                sb.append(que.poll()).append(">");
                System.out.println(sb);

            }
        }
