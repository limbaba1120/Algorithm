import java.util.ArrayList;
import java.util.List;

class PG_42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();

        int releaseDay = calculateDays(progresses[0], speeds[0]);
        int count = 1;

        for (int i = 1; i < progresses.length; i++) {
            int countDay = calculateDays(progresses[i], speeds[i]);

            if (releaseDay >= countDay) {
                count++;
            } else {
                result.add(count);
                releaseDay = countDay;
                count = 1;
            }
        }

        result.add(count);

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private int calculateDays(int progress, int speed) {
        return (100 - progress + speed - 1) / speed;
    }
}
