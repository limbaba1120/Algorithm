class PG_131128 {
    public String solution(String X, String Y) {
        int[] xCount = new int[10];
        int[] yCount = new int[10];

        for (int i = 0; i < X.length(); i++) {
            xCount[X.charAt(i) - '0']++;
        }

        for (int i = 0; i < Y.length(); i++) {
            yCount[Y.charAt(i) - '0']++;
        }

        StringBuilder answer = new StringBuilder();

        for (int number = 9; number >= 0; number--) {
            int count = Math.min(xCount[number], yCount[number]);

            for (int i = 0; i < count; i++) {
                answer.append(number);
            }
        }

        if (answer.length() == 0) {
            return "-1";
        }

        if (answer.charAt(0) == '0') {
            return "0";
        }

        return answer.toString();
    }
}
