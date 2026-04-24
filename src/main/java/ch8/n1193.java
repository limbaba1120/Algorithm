package ch8;

import java.util.Scanner;

public class n1193 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        int cross_count = 1, prev_count_sum = 0;

        while (true) {

            // 직전 대각선 누적합 + 해당 대각선 개수 이용한 범위 판별
            if (N <= prev_count_sum + cross_count) {
                // 분자가 큰 수부터 시작
                // 분자는 대각선상 내의 블럭 개수 - (X 번째 - 직전 대각선까지의 블럭 개수 - 1)
                // 분모는 X 번째 - 직전 대각선까지의 블럭 개수
                if (cross_count % 2 == 1) {
                    System.out.print((cross_count - (N - prev_count_sum - 1)) + "/" + (N - prev_count_sum));
                    break;
                }
                else {
                    System.out.println((N - prev_count_sum) + "/" + (cross_count - (N - prev_count_sum - 1)));
                    break;
                }
            } else {
                prev_count_sum += cross_count;
                cross_count++;
            }
        }
    }
}
