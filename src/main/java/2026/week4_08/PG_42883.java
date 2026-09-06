import java.util.Stack;

class PG_42883 {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);

            // 더 큰 숫자가 나오면 앞에 있는 작은 숫자를 계속 제거한다.
            while (
                    k > 0
                    && !stack.isEmpty()
                    && stack.peek() < current
            ) {
                stack.pop();
                k--;
            }

            stack.push(current);
        }

        // 내림차순처럼 제거 횟수가 남았다면 뒤쪽부터 제거한다.
        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder answer = new StringBuilder();

        for (char digit : stack) {
            answer.append(digit);
        }

        return answer.toString();
    }
}
