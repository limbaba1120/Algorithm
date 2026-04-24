import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) {
        Main sol = new Main();

        System.out.println(sol.longestSubarray(new int[]{8, 2, 4, 7}, 4));              // 2
        System.out.println(sol.longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));       // 4
        System.out.println(sol.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));  // 3
    }

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDq = new ArrayDeque<>();
        Deque<Integer> minDq = new ArrayDeque<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            while (!maxDq.isEmpty() && nums[maxDq.getLast()] < nums[right]) {
                maxDq.removeLast();
            }

            while (!minDq.isEmpty() && nums[minDq.getLast()] > nums[right]) {
                minDq.removeLast();
            }

            maxDq.addLast(right);
            minDq.addLast(right);
            
            while (nums[maxDq.getFirst()] - nums[minDq.getFirst()] > limit) {
                if (maxDq.getFirst() == left) {
                    maxDq.removeFirst();
                }
                if (minDq.getFirst() == left) {
                    minDq.removeFirst();
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}