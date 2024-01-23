import java.util.Scanner;

public class Main {
    public static int first = 10000;
    public static int last = 10000;
    public static int[] arr;
    public static int size = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        arr = new int[20001];



        int N = scan.nextInt();

        for (int i = 0; i < N; i++) {
            switch (scan.next()) {
                case "push_front":
                    push_front(scan.nextInt());
                    break;
                case "push_back":
                    push_back(scan.nextInt());
                    break;
                case "pop_front":
                    sb.append(pop_front()).append('\n');
                    break;
                case "pop_back":
                    sb.append(pop_back()).append('\n');
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


    private static void push_front(int num) {
        arr[first] = num;
        first--;
        size++;
    }

    private static void push_back(int num) {
        last++;
        size++;
        arr[last] = num;
    }

    private static int pop_front() {
        if (size == 0) {
            return -1;
        }
        int res = arr[first + 1];
        first++;
        size--;
        return res;
    }

    private static int size() {
        return size;
    }


    private static int pop_back() {
        if (size == 0) {
            return - 1;
        }
        int res = arr[last];
        last--;
        size--;
        return res;
    }

    private static int back() {
        if (size == 0) {
            return -1;
        } else {
            return arr[last];
        }
    }

    private static int front() {
        if (size == 0) {
            return -1;
        } else {
            return arr[first + 1];
        }
    }

    private static int empty() {
        if (size == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
