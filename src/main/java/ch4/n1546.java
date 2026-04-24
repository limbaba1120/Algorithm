package ch4;

import java.util.Scanner;

public class n1546 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double arr[] = new double[scan.nextInt()];

        double avg = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextDouble();
        }
        double max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        for (int i = 0; i < arr.length; i++)
            arr[i] = arr[i]/max * 100;

        for (int i = 0; i < arr.length; i++)
            avg += arr[i];

        System.out.println(avg/arr.length);

    }
}
/*
or

double sum = 0;

Arrays.sort(arr);
for (int i = 0; i < arr.length; i++) {
    sum += ((arr[i]/arr[arr.length-1]) *100);
}
 */