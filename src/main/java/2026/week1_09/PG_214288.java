import java.util.PriorityQueue;

class PG_214288 {
    private int minimumWaitingTime;

    public int solution(int k, int n, int[][] reqs) {
        minimumWaitingTime = Integer.MAX_VALUE;

        // 다른 k - 1개 유형에 최소 한 명씩 배정해야 한다.
        int maxMentorCount = n - k + 1;

        // type번 유형에 멘토를 mentorCount명 배정했을 때의 총 대기시간
        int[][] waitingTimes = new int[k + 1][maxMentorCount + 1];

        for (int type = 1; type <= k; type++) {
            for (int mentorCount = 1;
                 mentorCount <= maxMentorCount;
                 mentorCount++) {

                waitingTimes[type][mentorCount] =
                        calculateWaitingTime(type, mentorCount, reqs);
            }
        }

        // 1번 유형부터 총 n명의 멘토를 배정한다.
        allocateMentors(1, n, 0, k, waitingTimes);

        return minimumWaitingTime;
    }

    // 특정 유형에 멘토를 배정했을 때의 총 대기시간을 계산한다.
    private int calculateWaitingTime(
            int type,
            int mentorCount,
            int[][] reqs
    ) {
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        for (int i = 0; i < mentorCount; i++) {
            endTimes.offer(0);
        }

        int totalWaitingTime = 0;

        for (int[] request : reqs) {
            if (request[2] != type) {
                continue;
            }

            int requestTime = request[0];
            int duration = request[1];
            int earliestEndTime = endTimes.poll();

            int startTime = Math.max(requestTime, earliestEndTime);
            totalWaitingTime += startTime - requestTime;

            int newEndTime = startTime + duration;
            endTimes.offer(newEndTime);
        }

        return totalWaitingTime;
    }

    // 유형별 멘토 인원의 가능한 모든 조합을 탐색한다.
    private void allocateMentors(
            int type,
            int remainingMentors,
            int totalWaitingTime,
            int k,
            int[][] waitingTimes
    ) {
        // 마지막 유형에는 남은 멘토를 모두 배정한다.
        if (type == k) {
            int finalWaitingTime =
                    totalWaitingTime + waitingTimes[type][remainingMentors];

            minimumWaitingTime =
                    Math.min(minimumWaitingTime, finalWaitingTime);

            return;
        }

        // 이후 유형마다 최소 한 명씩 남겨야 한다.
        int remainingTypes = k - type;
        int maxMentorCount = remainingMentors - remainingTypes;

        for (int mentorCount = 1;
             mentorCount <= maxMentorCount;
             mentorCount++) {

            allocateMentors(
                    type + 1,
                    remainingMentors - mentorCount,
                    totalWaitingTime + waitingTimes[type][mentorCount],
                    k,
                    waitingTimes
            );
        }
    }
}
