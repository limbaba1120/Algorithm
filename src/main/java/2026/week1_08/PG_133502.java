import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

class PG_133502 {
    public int solution(int[] ingredient) {
        int answer = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int value : ingredient) {
            deque.push(value);

            if (deque.size() >= 4) {
                Iterator<Integer> iterator = deque.iterator();

                if (iterator.next() == 1
                        && iterator.next() == 3
                        && iterator.next() == 2
                        && iterator.next() == 1) {
                    deque.pop();
                    deque.pop();
                    deque.pop();
                    deque.pop();
                    answer++;
                }
            }
        }

        return answer;
    }
}
