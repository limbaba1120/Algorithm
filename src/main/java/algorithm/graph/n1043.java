package algorithm.graph;

import java.util.ArrayList;
import java.util.Scanner;

public class n1043 {
    static int[] parent;
    static int[] trueP;
    static ArrayList<Integer>[] party;
    static int result;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int T = scan.nextInt();
        result = 0;
        trueP = new int[T];
        for (int i = 0; i < T; i++) {
            trueP[i] = scan.nextInt();
        }
    }
}
