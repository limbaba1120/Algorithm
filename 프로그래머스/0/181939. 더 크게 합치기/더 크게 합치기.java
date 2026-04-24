class Solution {
    public int solution(int a, int b) {
        String s = String.valueOf(a) + String.valueOf(b);
        String e = String.valueOf(b) + String.valueOf(a);
        
        if (Integer.parseInt(s) > Integer.parseInt(e)) {
            return Integer.parseInt(s);
        } else if (Integer.parseInt(e) > Integer.parseInt(s)) {
            return Integer.parseInt(e);
        } else {
            return Integer.parseInt(s);
        }
        
        
    }
}