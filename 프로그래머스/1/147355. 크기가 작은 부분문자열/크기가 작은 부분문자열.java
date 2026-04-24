class Solution {
    public int solution(String t, String p) {
        int result = 0;
        
        int length = t.length() - p.length() + 1;
        long pnum = Long.parseLong(p);
        
        for (int i = 0; i < length; i++) {
            if (pnum >= Long.parseLong(t.substring(i, i+p.length()))) {
                result++;
            }
        }
        return result;
        
    }
}