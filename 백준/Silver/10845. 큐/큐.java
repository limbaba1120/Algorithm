
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {

    public static int[] stack = new int[10000];
    public static int last = 0;
    public static int first = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();


        int N = scan.nextInt();


        for (int i = 0; i < N; i++) {

            switch (scan.next()) {
                case "push":
                    push(scan.nextInt());
                    break;
                case "pop":
                    sb.append(pop()).append('\n');
                    break;
                case "size":
                    sb.append(size()).append('\n');
                    break;
                case "empty":
                    sb.append(empty()).append('\n');
                    break;
                case "front":
                    sb.append(front()).append('\n');
                    break;
                case "back":
                    sb.append(back()).append('\n');
                    break;
            }
        }
        System.out.println(sb);
    }

    private static void push(int num) {
        stack[last] = num;
        last++;
    }

    private static int pop() {
        if (last - first == 0) {
            return -1;
        } else {
            int res = stack[first];
            first++;
            return res;
        }
    }

    private static int size() {
        return last-first;
    }
    private static int empty() {
        if (last - first == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int front() {
        if (last - first == 0) {
            return -1;
        } else {
            int res = stack[first];
            return res;
        }
    }

    private static int back() {
        if (last - first == 0) {
            return -1;
        } else {
            int res = stack[last-1];
            return res;
        }
    }



}
