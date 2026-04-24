import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static boolean isAvailable;

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        visited = new boolean[5][5];
        
        for (int i = 0; i < places.length; i++) {
            String[] p = places[i];
            isAvailable = true;
            
            for (int r = 0; r < 5 && isAvailable; r++) {
                for (int c = 0; c < 5 && isAvailable; c++) {
                    if (p[r].charAt(c) == 'P') {
                        if (!bfs(r, c, p))
                            isAvailable = false;
                    }
                }
            }
            answer[i] = isAvailable ? 1 : 0;
            
        }
        return answer;
    }
    
    public static boolean bfs(int r, int c, String[] p) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        
        while(!queue.isEmpty()) {
            int[] position = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = position[0] + dx[i];
                int nc = position[1] + dy[i];
                
                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || (nr==r && nc == c))
                    continue;
                
                int d = Math.abs(nr - r) + Math.abs(nc - c);
                
                if (p[nr].charAt(nc) == 'P' && d <= 2) 
                    return false;
                else if (p[nr].charAt(nc) == 'O' && d < 2)
                    queue.offer(new int[] {nr, nc});
            }
        }
        return true;
    }
}