class PG_43105 {
    public int solution(int[][] triangle) {
        int answer = 0;
        int length = triangle.length;

        // dp[i][j]: [i][j]까지 도착했을 때의 최대 누적합
        int[][] dp = new int[length][length];

        // 시작점 초기화
        dp[0][0] = triangle[0][0];

        // 위에서 아래로 각 칸의 최대 누적합 계산
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 맨 왼쪽
                    dp[i][j] = dp[i - 1][0] + triangle[i][0];
                } else if (j == i) {
                    // 맨 오른쪽
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    // 가운데
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j])
                            + triangle[i][j];
                }
            }
        }

        // 마지막 행에서 가장 큰 값 찾기
        for (int i = 0; i < length; i++) {
            answer = Math.max(dp[length - 1][i], answer);
        }

        return answer;
    }
}
