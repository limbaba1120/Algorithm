class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        int over_length = overwrite_string.length();
        int my_length = my_string.length();
        answer = my_string.substring(0, s) + overwrite_string + my_string.substring(s+over_length, my_length);
        return answer;
    }
}