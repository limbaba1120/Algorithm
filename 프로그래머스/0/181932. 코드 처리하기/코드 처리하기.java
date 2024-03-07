class Solution {
    public String solution(String code) {
        String answer = "";
        
        int mode = 0;
        int idx = 0;
        int length = code.length();
        
        while (idx != length) {
            
            char c = code.charAt(idx);
            if (mode == 0) {
                if (c == '1'){
                    ++mode;
                } else {
                    if (idx % 2 == 0) {
                        answer += c;
                    }
                }
            } else {
                if (c == '1') {
                    --mode;
                } else {
                    if (idx % 2 == 1) {
                        answer += c;
                    }
                }
            }
            idx++;
        }
        
        if (answer.isEmpty()) {
            return "EMPTY";
        }
        
        
        return answer;
    }
}