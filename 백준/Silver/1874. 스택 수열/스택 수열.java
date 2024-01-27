
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = scan.nextInt();

        Stack<Integer> st = new Stack<>();

        int start = 0;

        while (N-- > 0) {
            int value = scan.nextInt();

            if (value > start) {
                for (int i = start + 1; i <= value; i++) {
                    st.push(i);
                    sb.append('+').append('\n');
                }
                start = value;
            } else if (st.peek() != value) {
                System.out.println("NO");
                return;
            }
            st.pop();
            sb.append('-').append('\n');
        }
        System.out.println(sb);
    }
}
