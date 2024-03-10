import java.util.Map;
import java.util.HashMap;


class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        int sum = 0; 
        
        Map<String, Integer> memory = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            memory.put(name[i], yearning[i]);
        }
        for (int i = 0; i < photo.length; i++) {
            for (String names : photo[i]) {
                if (memory.containsKey(names)) {
                    answer[i] += memory.get(names);
                }
                
            }
        }
        
        return answer;
    }
}