import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class PG_42587 {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> waitingQueue = new ArrayDeque<>();
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            waitingQueue.offer(i);
            priorityQueue.offer(priorities[i]);
        }

        while (!waitingQueue.isEmpty()) {
            int currentIndex = waitingQueue.poll();
            int currentPriority = priorities[currentIndex];

            if (currentPriority == priorityQueue.peek()) {
                priorityQueue.poll();
                answer++;

                if (currentIndex == location) {
                    return answer;
                }
            } else {
                waitingQueue.offer(currentIndex);
            }
        }

        return -1;
    }
}
