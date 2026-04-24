package ch3;

import java.util.Scanner;

public class n25304 {
        public static void main(String[] args) {
            int total_price = 0;
            int item_price = 0;
            int total_num = 0;
            int item_num = 0;
            int sum = 0;
            Scanner scanner = new Scanner(System.in);

            total_price = scanner.nextInt();
            total_num = scanner.nextInt();

            for (int i = 0; i < total_num; i++) {
                item_price = scanner.nextInt();
                item_num = scanner.nextInt();
                sum += item_price * item_num;
            }
            if (total_price == sum) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
}
/*
코드를 줄일려면 total_price를 바로 int 로 선언 하면서 하기.
total_price 에서 item_price * item_num을 빼면서 total_price 가 0 이 되면 YES를 출력함.
 */
