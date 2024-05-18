package algorithm.tree;

import java.util.*;

public class n14425 {
    static int N, M;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        M = scan.nextInt();

        Set<String> array = new HashSet<>();

        for (int i = 0; i < N; i++) {
            array.add(scan.next());
        }

        int count = 0;

        for (int i = 0; i < M; i++) {
            if (array.contains(scan.next())) count++;
        }

        System.out.println(count);


    }



}
