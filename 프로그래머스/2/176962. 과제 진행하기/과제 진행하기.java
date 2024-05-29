import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        
        int idx = 0;
        PriorityQueue<Subject> q = new PriorityQueue<>();

        for (String[] p : plans) {
            q.add(new Subject(p[0], convertTime(p[1]), Integer.parseInt(p[2])));
        }
        
        Subject s = q.poll();
        int now = s.start;
        
        Stack<Subject> stack = new Stack<>();
        while (true) {
            if (!q.isEmpty() && now+s.playtime > q.peek().start) {
                stack.push(new Subject(s.name, s.start, s.playtime-(q.peek().start - now)));
                
                now = q.peek().start;
                s = q.poll();
            } else {
                answer[idx++] = s.name;
                now += s.playtime;
                
                if (!q.isEmpty() && now == q.peek().start) {
                    s = q.poll();
                } else if (!stack.isEmpty()){
                    s = stack.pop();
                } else if (!q.isEmpty()) {
                    s = q.poll();
                    now = s.start;
                } else break;
            }
        }
        return answer;
        
    }
    
    public static int convertTime(String t) {
        String[] str = t.split(":");
        int min = Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
        return min;
    }
 
    static class Subject implements Comparable<Subject> {
        String name;
        int start;
        int playtime;
        
        Subject (String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        
        @Override
        public int compareTo(Subject s) {
            return this.start - s.start;
        }
    }
}