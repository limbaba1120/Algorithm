import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1438. 절대 차이가 limit 이하인 가장 긴 연속 부분배열
 *
 * 핵심: 부분배열의 (최댓값 - 최솟값) <= limit 이면 OK.
 * 풀이: 슬라이딩 윈도우 + 단조 덱 2개로 max/min을 O(1)에 유지.
 */
class Main {
    public static void main(String[] args) {
        Main sol = new Main();

        System.out.println(sol.longestSubarray(new int[]{8, 2, 4, 7}, 4));              // 2
        System.out.println(sol.longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));       // 4
        System.out.println(sol.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));  // 3
    }

    public int longestSubarray(int[] nums, int limit) {
        // maxDq: 앞쪽이 윈도우 최댓값 (값이 큰 순)
        // minDq: 앞쪽이 윈도우 최솟값 (값이 작은 순)
        // 값이 아니라 "인덱스"를 저장 → 윈도우 밖으로 나갔는지 판단하기 위해
        Deque<Integer> maxDq = new ArrayDeque<>();
        Deque<Integer> minDq = new ArrayDeque<>();

        int left = 0; // 윈도우 왼쪽 끝
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            // 새 값보다 작은 뒤쪽 값들은 더이상 max 후보가 될 수 없음 → 제거
            while (!maxDq.isEmpty() && nums[maxDq.getLast()] < nums[right]) {
                maxDq.removeLast();
            }
            // 새 값보다 큰 뒤쪽 값들은 더이상 min 후보가 될 수 없음 → 제거
            while (!minDq.isEmpty() && nums[minDq.getLast()] > nums[right]) {
                minDq.removeLast();
            }

            // 새 인덱스를 뒤에 추가
            maxDq.addLast(right);
            minDq.addLast(right);

            // 윈도우의 max - min이 limit 초과면 왼쪽을 좁힘
            while (nums[maxDq.getFirst()] - nums[minDq.getFirst()] > limit) {
                // left가 빠져나가면 덱 앞에서도 제거
                if (maxDq.getFirst() == left) maxDq.removeFirst();
                if (minDq.getFirst() == left) minDq.removeFirst();
                left++;
            }

            // 현재 윈도우 길이로 정답 갱신
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
