package algorithm.javabook.datastructure;

import java.io.*;
import java.util.Stack;

public class n17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] Ans = new int[n];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> myStack = new Stack<>();
        myStack.push(0);
        for (int i = 1; i < n; i++) {
            while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
                Ans[myStack.pop()] = A[i];
            }
            myStack.push(i);
        }
        while (!myStack.isEmpty()) {
            Ans[myStack.pop()] = -1;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            bw.write(Ans[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
