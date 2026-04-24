package ch4;


import java.util.Scanner;

public class n5597 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int student[] = new int[31];

        for (int i = 1; i <= 28; i++) {
            int num = scan.nextInt();
            student[num] = 1;
        }
        for (int i = 1; i < student.length; i++) {
            if (student[i] != 1) {
                System.out.println(i);
            }
        }


    }
}
