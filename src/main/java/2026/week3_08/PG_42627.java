import java.util.Arrays;
import java.util.PriorityQueue;

class PG_42627 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        int currentTime = 0;
        int totalTime = 0;
        int completed = 0;
        int index = 0;

        while (completed < jobs.length) {
            while (index < jobs.length && jobs[index][0] <= currentTime) {
                pq.offer(jobs[index]);
                index++;
            }

            if (!pq.isEmpty()) {
                int[] currentJob = pq.poll();

                currentTime += currentJob[1];
                totalTime += currentTime - currentJob[0];
                completed++;
            } else {
                currentTime = jobs[index][0];
            }
        }

        return totalTime / jobs.length;
    }
}
