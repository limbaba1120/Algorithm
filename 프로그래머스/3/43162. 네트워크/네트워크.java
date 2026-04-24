import java.util.*;

class Solution {
    static boolean[] isVisited;
    static Queue<Integer> queue = new LinkedList<>();
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        isVisited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) { // i 대신 0을 사용하면 오류 발생 가능
                BFS(i, computers, n);
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void BFS(int i, int[][] computers, int n) {
        queue.offer(i);
        isVisited[i] = true;
        
        while (!queue.isEmpty()) {
            int value = queue.poll();
            
            for (int j = 0; j < n; j++) {
                if (!isVisited[j] && computers[value][j] == 1) {
                    isVisited[j] = true;
                    queue.offer(j); // queue.offer()에 j 값을 추가해야 함
                }
            }
        }
    }
}
