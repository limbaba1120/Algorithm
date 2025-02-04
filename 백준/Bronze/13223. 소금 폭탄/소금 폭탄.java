
import java.util.Scanner;

/*
 * 문제 : HH:MM:SS 포맷의 두 시각의 차이를 HH:MM:SS 포맷으로 출력
 * 1. ':' 문자를 기준으로 시간,분,초를 쪼갠다
 * 2. 두 시간, 분, 초의 차이를 계산한다
 * 3. 구해진 시간을 HH:MM:SS 형태로 출력
 */

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String[] firstTime = scan.nextLine().split(":");
		String[] secondTime = scan.nextLine().split(":");

		int time1 = timeToSeconds(firstTime);
		int time2 = timeToSeconds(secondTime);

		int leftTime = time2 - time1;
		if (leftTime <= 0) {
			leftTime += 24 * 3600;
		}

		String targetTime = secondToTime(leftTime);

		System.out.println(targetTime);
	}

	public static int timeToSeconds(String[] time) {
		int hour = Integer.parseInt(time[0]);
		int min = Integer.parseInt(time[1]);
		int second = Integer.parseInt(time[2]);

		return hour * 3600 + min * 60 + second;
	}

	public static String secondToTime(int time) {

		int leftHour = time / 3600;
		int leftMin = (time % 3600) / 60;
		int leftSecond = time % 60;
		return String.format("%02d:%02d:%02d", leftHour, leftMin, leftSecond);
	}
}
