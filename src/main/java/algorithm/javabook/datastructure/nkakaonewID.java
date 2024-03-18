package algorithm.javabook.datastructure;

public class nkakaonewID {
    class Solution {
        public String solution(String new_id) {
            String answer = "";

            //1단계
            String step1 = new_id.toLowerCase();

            //2단계
            char[] step2_ch = step1.toCharArray();
            StringBuilder step2 = new StringBuilder();
            for (char c : step2_ch) {
                if (('a' <= c && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c =='.') {
                    step2.append(c);
                }
            }
            //3단계
            String step3 = step2.toString().replace("..", ".");
            while (step3.contains("..")) {
                step3 = step3.replace("..", ".");
            }
            //4단계
            String step4 = step3;
            while (!step4.isEmpty() && (step4.startsWith(".") || step4.endsWith("."))) {
                if (step4.startsWith(".")) {
                    step4 = step4.substring(1);
                }
                if (step4.endsWith(".")) {
                    step4 = step4.substring(0, step4.length() - 1);
                }
            }
            //5단계
            String step5 = step4;
            if (step5.isEmpty()) {
                step5 = "a";
            }

            //6단계
            String step6 = step5;
            if (step6.length() >= 16 ) {
                step6 = step6.substring(0, 15);
                if (step6.endsWith(".")) {
                    step6 = step6.substring(0, 14);
                }
            }
            //7단계
            StringBuilder step7 = new StringBuilder(step6);
            while (step7.length() <= 2) {
                char last = step7.charAt(step7.length()-1);
                step7.append(last);
            }
            answer = step7.toString();

            return answer;
        }
    }

}
