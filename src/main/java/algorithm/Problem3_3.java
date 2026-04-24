package algorithm;

import java.util.Scanner;

public class Problem3_3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        int maxsum = 0;
        int realMax = 0;
        int a = 0, b = 0;
        //arr 에 하나만 있으면 arr[0] 리턴한다.
        if (arr.length == 1) {
            System.out.println(arr[0]);
        }
        for (int i = 0; i < arr.length; i++) {
            //maxsum을 현재 최대값 + arr[i]를 더한값을 넣는다.
            maxsum = Math.max(0, maxsum) + arr[i];
            //만약 음수가 나와서 maxsum 과 0의 비교값이 0이되면 a를 i의 다음 인덱스를 넣
            if (Math.max(0, maxsum) == 0) {
                a = i+1;
            }
            //realMax값과 더한값의 maxsum을 비교해서 더 큰값을 realMax(출력할값)에 넣는다.
            realMax = Math.max(realMax, maxsum + arr[i]);

            //realMax가 maxsum보다 크면 b 마지막 index를 i로 대입한다.
            if (realMax > maxsum) {
                b = i;
            }
        }
        System.out.printf("%d, %d, %d" , a, b, realMax);
    }

}
