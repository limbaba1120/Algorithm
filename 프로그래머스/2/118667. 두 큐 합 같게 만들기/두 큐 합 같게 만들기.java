import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        long total = 0; //두 큐의 합
        long sum_q1 = 0; //1번큐의 합
        long sum_q2 = 0;
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        
        for (int i = 0; i < queue1.length; i++) {
            total += queue1[i] + queue2[i];
            sum_q1 += queue1[i];
            sum_q2 += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        
        if (total % 2 != 0) return -1; // 두 큐의 합이 홀수면 -1
        
        long target = total/2;
        
        while (sum_q1 != sum_q2) {
            if (answer >= queue1.length * 3) return -1;
            
            if (sum_q1 > sum_q2) {
                sum_q1 -= q1.peek();
                q2.add(q1.peek());
                sum_q2 += q1.poll();
            } else {
                sum_q2 -= q2.peek();
                q1.offer(q2.peek());
                sum_q1 += q2.poll();
            }
            answer++;
        }
        return answer;
    }
}