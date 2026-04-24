import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String str = scan.nextLine();

		char[] ch = str.toCharArray();

		for (int i = 0; i < str.length(); i++) {
			if (ch[i] >= 'a' && ch[i] <= 'z') {
				ch[i] = (char)(ch[i] + 'A' - 'a');
			} else {
				ch[i] = (char)(ch[i] + 'a' - 'A');
			}

		}

		str = new String(ch);

		System.out.println(str);
	}
}
