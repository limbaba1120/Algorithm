import java.util.ArrayDeque;
import java.util.Deque;

class PG_42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int previousIndex = stack.pop();

                answer[previousIndex] = i - previousIndex;
            }

            stack.push(i);
        }

        int lastIndex = prices.length - 1;

        while (!stack.isEmpty()) {
            int previousIndex = stack.pop();

            answer[previousIndex] = lastIndex - previousIndex;
        }

        return answer;
    }
}
