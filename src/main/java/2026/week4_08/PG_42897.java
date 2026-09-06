class PG_42897 {
    public int solution(int[] money) {
        // 첫 번째 집 포함 가능, 마지막 집 제외
        int first = findLargest(money, 0, money.length - 2);

        // 첫 번째 집 제외, 마지막 집 포함 가능
        int second = findLargest(money, 1, money.length - 1);

        return Math.max(first, second);
    }

    private int findLargest(int[] money, int start, int end) {
        // 배열 DP의 dp[i - 2] 역할
        int twoBefore = 0;

        // 배열 DP의 dp[i - 1] 역할
        int oneBefore = 0;

        for (int i = start; i <= end; i++) {
            // 현재 집을 털지 않는 경우와 터는 경우 중 큰 금액
            int current = Math.max(
                    oneBefore,
                    twoBefore + money[i]
            );

            // 다음 집의 계산을 위해 DP 결과를 한 단계씩 이동
            twoBefore = oneBefore;
            oneBefore = current;
        }

        return oneBefore;
    }
}
