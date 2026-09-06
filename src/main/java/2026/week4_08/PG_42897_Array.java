class PG_42897_Array {
    public int solution(int[] money) {
        // 첫 번째 집 포함 가능, 마지막 집 제외
        int first = findLargest(money, 0, money.length - 2);

        // 첫 번째 집 제외, 마지막 집 포함 가능
        int second = findLargest(money, 1, money.length - 1);

        return Math.max(first, second);
    }

    private int findLargest(int[] money, int start, int end) {
        // dp[i]: start번부터 i번 집까지 고려했을 때의 최대 금액
        int[] dp = new int[money.length];

        dp[start] = money[start];
        dp[start + 1] = Math.max(money[start], money[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            // 현재 집을 털지 않는 경우와 터는 경우 중 큰 금액
            dp[i] = Math.max(
                    dp[i - 1],
                    dp[i - 2] + money[i]
            );
        }

        return dp[end];
    }
}
