
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String str1 = scan.nextLine();
		String str2 = scan.nextLine();

		int startIndex = 0;
		int count = 0;

		while (true) {
			int findIndex = str1.indexOf(str2, startIndex);

			if (findIndex < 0) {
				break;
			}

			startIndex = findIndex + str2.length();
			count++;
		}

		System.out.println(count);
	}
}
