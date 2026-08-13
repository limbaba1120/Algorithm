import java.util.PriorityQueue;

class PG_42626 {
    public int solution(int[] scoville, int k) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }

        for (int i = 0; i < scoville.length; i++) {
            if (pq.peek() >= k) {
                break;
            }

            if (pq.size() < 2) {
                return -1;
            }

            int firstFood = pq.poll();
            int secondFood = pq.poll();
            int combineFood = firstFood + (secondFood * 2);

            pq.offer(combineFood);
            answer++;
        }

        return answer;
    }
}
