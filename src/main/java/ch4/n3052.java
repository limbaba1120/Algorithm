package ch4;

import java.util.HashSet;
import java.util.Scanner;

public class n3052 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = 0;
        HashSet<Integer> store = new HashSet<Integer>();
        for (int i = 0; i < 10; i++) {
            num = scan.nextInt();
            store.add(num%42);
        }
        System.out.println(store.size());
    }
}
