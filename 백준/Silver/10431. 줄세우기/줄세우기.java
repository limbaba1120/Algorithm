
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int T = scan.nextInt();
		int[] answer = new int[T];

		for (int t = 0; t < T; t++) {
			int[] arr = new int[20];
			int step = scan.nextInt();
			for (int i = 0; i < 20; i++) {
				arr[i] = scan.nextInt();
			}

			int count = 0;
			int[] sorted = new int[20];
			for (int i = 0; i < 20; i++) {
				// 1. 줄 서있는 학생 중에 자신보다 큰 학생을 찾는다
				// 1-1. 찾지 못했다면, 줄의 가장 뒤에 선다
				boolean find = false;
				for (int j = 0; j < i; j++) {
					if (sorted[j] > arr[i]) {
						// 2. 찾았다면, 그 학생 앞에 선다
						// 3. 그 학생과 그 뒤의 학생이 모두 한칸씩 물러난다
						find =true;
						for (int k = i - 1; k >= j; k--) {
							sorted[k + 1] = sorted[k];
							count++;
						}
						sorted[j] = arr[i];
						break;
					}
				}
				if (!find)
					sorted[i] = arr[i];
			}
			System.out.println(t + 1 + " " + count);

		}
	}
}
