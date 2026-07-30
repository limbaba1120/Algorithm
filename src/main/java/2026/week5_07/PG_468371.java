class PG_468371 {
    public int solution(int[][] signals) {
        int limit = 1;

        // 모든 신호등 주기의 최소공배수를 구한다.
        for (int i = 0; i < signals.length; i++) {
            int green = signals[i][0];
            int yellow = signals[i][1];
            int red = signals[i][2];
            int cycle = green + yellow + red;

            limit = lcm(limit, cycle);
        }

        // 모든 신호등의 상태 조합이 한 번 반복될 때까지만 확인한다.
        for (int time = 1; time <= limit; time++) {
            boolean allYellow = true;

            for (int i = 0; i < signals.length; i++) {
                int green = signals[i][0];
                int yellow = signals[i][1];
                int red = signals[i][2];
                int cycle = green + yellow + red;

                // 현재 시각이 이 신호등의 한 주기에서 어느 위치인지 구한다.
                int position = (time - 1) % cycle;
                boolean isYellow =
                        green <= position && position < green + yellow;

                if (!isYellow) {
                    allYellow = false;
                    break;
                }
            }

            if (allYellow) {
                return time;
            }
        }

        return -1;
    }

    // 유클리드 호제법으로 최대공약수를 구한다.
    private int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }

    // 두 수의 최소공배수를 구한다.
    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
