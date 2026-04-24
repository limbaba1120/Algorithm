package algorithm.javabook.datastructure;

import java.util.Scanner;

public class n1541 {
    static int answer = 0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String str = scan.next();

        String[] data = str.split("-");

        for (int i = 0; i < data.length; i++) {
            int result = mySum(data[i]);
            if (i == 0) {
                answer += result;
            } else {
                answer -= result;
            }
        }
        System.out.println(answer);
    }

    public static int mySum(String data) {
        int sum = 0;
        String[] temp = data.split("[+]");
        for (int i = 0; i < temp.length; i++) {
            sum += Integer.parseInt(temp[i]);
        }
        return sum;
    }
}
