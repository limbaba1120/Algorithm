import java.util.function.Function;

class PG_468378 {
    public int solution(
            int[] depth,
            int money,
            Function<Integer, Integer> excavate
    ) {
        int w = depth.length;

        // dp[left][right]: 이 구간에서 보물을 반드시 찾는 데 필요한 최소 비용
        int[][] dp = new int[w][w];

        // choice[left][right]: 이 구간에서 가장 먼저 굴착할 인덱스
        int[][] choice = new int[w][w];

        // 후보가 한 칸뿐이면 그 칸을 한 번 굴착해야 한다.
        for (int i = 0; i < w; i++) {
            dp[i][i] = depth[i];
            choice[i][i] = i;
        }

        // 작은 구간의 결과를 이용할 수 있도록 길이 2부터 차례대로 계산한다.
        for (int length = 2; length <= w; length++) {
            for (int left = 0; left + length - 1 < w; left++) {
                int right = left + length - 1;
                int bestCost = Integer.MAX_VALUE;
                int bestMid = -1;

                // [left, right] 안의 모든 열을 첫 굴착 후보로 비교한다.
                for (int mid = left; mid <= right; mid++) {
                    // mid가 구간 끝이면 그 방향에는 더 탐색할 곳이 없으므로 비용은 0이다.
                    int leftCost = mid > left ? dp[left][mid - 1] : 0;
                    int rightCost = mid < right ? dp[mid + 1][right] : 0;

                    // mid 굴착 비용 + 왼쪽·오른쪽 중 더 비싼 최악의 경우
                    int candidateCost =
                            depth[mid] + Math.max(leftCost, rightCost);

                    // 최악의 비용이 가장 작은 열을 이 구간의 첫 선택으로 저장한다.
                    if (candidateCost < bestCost) {
                        bestCost = candidateCost;
                        bestMid = mid;
                    }
                }

                dp[left][right] = bestCost;
                choice[left][right] = bestMid;
            }
        }

        // 전체 구간에서 시작하여 choice가 알려 주는 열을 실제로 굴착한다.
        int left = 0;
        int right = w - 1;

        while (left <= right) {
            int mid = choice[left][right];

            // 배열 인덱스는 0부터, 실제 열 번호는 1부터 시작한다.
            int result = excavate.apply(mid + 1);

            if (result == 0) {
                return mid + 1;
            }

            if (result == -1) {
                // 보물이 현재 열보다 왼쪽에 있다.
                right = mid - 1;
            } else {
                // 보물이 현재 열보다 오른쪽에 있다.
                left = mid + 1;
            }
        }

        return 0;
    }
}
