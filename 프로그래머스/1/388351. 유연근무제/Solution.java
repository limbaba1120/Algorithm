class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int employee = 0; employee < schedules.length; employee++) {
            int deadline = toMinutes(schedules[employee]) + 10;
            boolean success = true;

            for (int j = 0; j < 7; j++) {
                int day = (startday - 1 + j) % 7 + 1;

                // 토요일과 일요일의 출근 기록은 이벤트에 영향을 주지 않는다.
                if (day == 6 || day == 7) {
                    continue;
                }

                int arrivalTime = toMinutes(timelogs[employee][j]);
                if (arrivalTime > deadline) {
                    success = false;
                    break;
                }
            }

            if (success) {
                answer++;
            }
        }

        return answer;
    }

    private int toMinutes(int time) {
        int hour = time / 100;
        int minute = time % 100;
        return hour * 60 + minute;
    }
}
