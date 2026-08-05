import java.util.HashMap;
import java.util.Map;

class PG_118666 {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> pointMap = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            char first = survey[i].charAt(0);
            char last = survey[i].charAt(1);

            if (choices[i] < 4) {
                pointMap.put(
                        first,
                        pointMap.getOrDefault(first, 0) + (4 - choices[i]));
            } else if (choices[i] > 4) {
                pointMap.put(
                        last,
                        pointMap.getOrDefault(last, 0) + (choices[i] - 4));
            }
        }

        String[] types = {"RT", "CF", "JM", "AN"};
        StringBuilder answer = new StringBuilder();

        for (String type : types) {
            char first = type.charAt(0);
            char last = type.charAt(1);

            int firstPoint = pointMap.getOrDefault(first, 0);
            int lastPoint = pointMap.getOrDefault(last, 0);

            if (firstPoint >= lastPoint) {
                answer.append(first);
            } else {
                answer.append(last);
            }
        }

        return answer.toString();
    }
}
