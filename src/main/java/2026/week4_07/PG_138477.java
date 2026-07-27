import java.util.PriorityQueue;

class PG_138477 {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        PriorityQueue<Integer> hallOfFame = new PriorityQueue<>();

        for (int day = 0; day < score.length; day++) {
            // 오늘의 점수를 후보에 추가한다.
            hallOfFame.offer(score[day]);

            // k명을 초과하면 가장 낮은 점수를 제외한다.
            if (hallOfFame.size() > k) {
                hallOfFame.poll();
            }

            // 최소 힙의 맨 앞은 현재 명예의 전당 최하위 점수다.
            answer[day] = hallOfFame.peek();
        }

        return answer;
    }
}
