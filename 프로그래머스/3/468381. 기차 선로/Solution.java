class Solution {

    /*
     * 선로가 연결된 방향을 4개의 비트로 표현한다.
     *
     * 위쪽   = 0001 = 1
     * 오른쪽 = 0010 = 2
     * 아래쪽 = 0100 = 4
     * 왼쪽   = 1000 = 8
     *
     * 예를 들어 가로 선로는 오른쪽과 왼쪽으로 연결되므로
     * 0010 | 1000 = 1010으로 표현할 수 있다.
     */
    private static final int UP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 4;
    private static final int LEFT = 8;

    /*
     * 방향 번호는 다음과 같다.
     *
     * 0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽
     *
     * 예: 현재 위치가 (r, c)이고 d가 1이면
     *     (r + DR[1], c + DC[1])은 오른쪽 칸이다.
     */
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    /*
     * 문제의 선로 번호를 비트마스크로 변환한 배열이다.
     *
     * TYPE[0]은 선로를 놓지 않은 빈칸이다.
     * TYPE[1]부터 TYPE[7]까지는 문제에 나온 선로 모양이다.
     */
    private static final int[] TYPE = {
        0,                              // 0: 빈칸
        RIGHT | LEFT,                   // 1번: 가로
        UP | DOWN,                      // 2번: 세로
        UP | RIGHT | DOWN | LEFT,       // 3번: 십자
        UP | LEFT,                      // 4번: 위 + 왼쪽
        UP | RIGHT,                     // 5번: 위 + 오른쪽
        RIGHT | DOWN,                   // 6번: 오른쪽 + 아래
        DOWN | LEFT                     // 7번: 아래 + 왼쪽
    };

    private int n;
    private int m;

    /*
     * original: 문제에서 처음 주어진 격자
     *
     * board: 백트래킹으로 선로를 하나씩 놓고 있는 격자
     *        선로 번호가 아닌 TYPE의 비트마스크가 저장된다.
     */
    private int[][] original;
    private int[][] board;

    public int solution(int[][] grid) {
        n = grid.length;
        m = grid[0].length;

        original = new int[n][m];
        board = new int[n][m];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                original[r][c] = grid[r][c];

                if (grid[r][c] == -1) {
                    // 장애물은 board에서도 -1로 표시한다.
                    board[r][c] = -1;
                } else if (grid[r][c] > 0) {
                    // 주어진 선로 번호를 비트마스크로 바꾼다.
                    board[r][c] = TYPE[grid[r][c]];
                }
            }
        }

        // 0번째 칸부터 차례대로 선로 배치를 결정한다.
        return dfs(0);
    }

    /*
     * index번째 칸부터 가능한 선로를 놓아 보고,
     * 조건을 만족하는 완성된 배치의 수를 반환한다.
     *
     * 격자는 다음 순서로 방문한다.
     *
     * 0 → 1 → 2
     *           ↓
     * 3 → 4 → 5
     */
    private int dfs(int index) {
        /*
         * 모든 칸의 선택이 끝났다.
         *
         * 선로끼리 연결된 것만으로는 충분하지 않으므로
         * 기차를 직접 움직여 최종 조건을 확인한다.
         */
        if (index == n * m) {
            return canTravelAllTracks() ? 1 : 0;
        }

        // 1차원 index를 2차원 행과 열로 바꾼다.
        int r = index / m;
        int c = index % m;

        // 장애물에는 선로를 놓을 수 없으므로 다음 칸으로 간다.
        if (original[r][c] == -1) {
            return dfs(index + 1);
        }

        /*
         * 처음부터 선로가 놓여 있던 칸은 그대로 둔다.
         *
         * 특히 출발점의 1번 선로는 왼쪽 연결이 격자 밖을 향한다.
         * 따라서 고정 선로에는 새 선로용 canPlace 검사를 적용하지 않는다.
         * 고정 선로의 실제 경로 문제는 마지막 기차 이동에서 걸러진다.
         */
        if (original[r][c] > 0) {
            return dfs(index + 1);
        }

        /*
         * 원래 빈칸이었다면 다음 8가지 선택을 모두 시도한다.
         *
         * type == 0: 빈칸으로 둔다.
         * type == 1~7: 해당 번호의 선로를 놓는다.
         */
        int count = 0;

        for (int type = 0; type <= 7; type++) {
            int mask = TYPE[type];

            /*
             * 현재 선로가 주변과 연결될 수 없다면
             * 이후의 칸을 확인할 필요가 없으므로 바로 건너뛴다.
             * 이것이 백트래킹의 가지치기이다.
             */
            if (!canPlace(r, c, mask)) {
                continue;
            }

            // 현재 선택을 기록하고 다음 칸을 결정한다.
            board[r][c] = mask;
            count += dfs(index + 1);
        }

        /*
         * 이전 DFS 단계로 돌아가기 전에 현재 칸을 복구한다.
         * 다른 선택에 현재 선택이 영향을 주지 않게 하기 위해서다.
         */
        board[r][c] = 0;

        return count;
    }

    /*
     * (r, c)에 mask 선로를 놓을 수 있는지 검사한다.
     *
     * 확인할 내용:
     * 1. 선로가 격자 밖으로 나가는가?
     * 2. 선로가 장애물을 향하는가?
     * 3. 이미 결정된 이웃 선로와 연결이 맞는가?
     */
    private boolean canPlace(int r, int c, int mask) {
        // 위, 오른쪽, 아래, 왼쪽을 하나씩 확인한다.
        for (int d = 0; d < 4; d++) {
            // 현재 선로가 d 방향으로 열려 있는지 확인한다.
            boolean currentConnected = hasDirection(mask, d);

            int nr = r + DR[d];
            int nc = c + DC[d];

            // 격자 밖을 향해 선로가 열려 있으면 놓을 수 없다.
            if (!isInside(nr, nc)) {
                if (currentConnected) {
                    return false;
                }
                continue;
            }

            // 장애물을 향해 선로가 열려 있어도 놓을 수 없다.
            if (original[nr][nc] == -1) {
                if (currentConnected) {
                    return false;
                }
                continue;
            }

            /*
             * 격자를 위에서 아래, 왼쪽에서 오른쪽 순서로 채우므로
             * 현재 칸의 위쪽과 왼쪽 칸은 이미 결정되어 있다.
             */
            boolean alreadyDecided = nr < r || (nr == r && nc < c);

            /*
             * 아래쪽 또는 오른쪽에 있는 칸이라도
             * 처음부터 주어진 선로라면 모양을 이미 알고 있다.
             */
            boolean fixedTrack = original[nr][nc] > 0;

            if (alreadyDecided || fixedTrack) {
                int neighborMask;

                if (fixedTrack) {
                    neighborMask = TYPE[original[nr][nc]];
                } else {
                    neighborMask = board[nr][nc];
                }

                /*
                 * 현재 칸에서 오른쪽을 보고 있다면
                 * 이웃 칸에서는 왼쪽을 확인해야 한다.
                 *
                 * (d + 2) % 4를 사용하면 반대 방향을 구할 수 있다.
                 * 위(0) ↔ 아래(2), 오른쪽(1) ↔ 왼쪽(3)
                 */
                int opposite = (d + 2) % 4;
                boolean neighborConnected =
                    hasDirection(neighborMask, opposite);

                /*
                 * 양쪽 모두 열려 있거나, 양쪽 모두 닫혀 있어야 한다.
                 * 한쪽만 열려 있으면 선로가 끊긴다.
                 */
                if (currentConnected != neighborConnected) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * 완성된 board 위에서 기차를 실제로 움직인다.
     *
     * 성공 조건:
     * 1. 목적지에 도착한다.
     * 2. board에 놓인 모든 선로를 한 번 이상 지난다.
     */
    private boolean canTravelAllTracks() {
        // 각 선로 칸을 한 번이라도 지났는지 기록한다.
        boolean[][] visitedCell = new boolean[n][m];

        /*
         * 같은 칸이라도 진행 방향이 다르면 다른 상태이다.
         * 특히 십자 선로는 가로와 세로로 각각 통과할 수 있다.
         */
        boolean[][][] visitedState = new boolean[n][m][4];

        // 기차는 (0, 0)에서 오른쪽으로 출발한다.
        int r = 0;
        int c = 0;
        int direction = 1;

        while (true) {
            // 격자 밖이나 선로가 없는 칸에 도착하면 실패한다.
            if (!isInside(r, c) || board[r][c] <= 0) {
                return false;
            }

            /*
             * 같은 칸에 같은 방향으로 다시 도착했다면
             * 앞으로도 같은 경로를 반복하므로 무한 순환이다.
             */
            if (visitedState[r][c][direction]) {
                return false;
            }

            visitedState[r][c][direction] = true;
            visitedCell[r][c] = true;

            // 목적지에 도착하면 기차 이동을 종료한다.
            if (r == n - 1 && c == m - 1) {
                break;
            }

            int mask = board[r][c];

            /*
             * 기차가 오른쪽으로 이동 중이라면 왼쪽 면으로 들어왔다.
             * 따라서 들어온 면은 진행 방향의 반대 방향이다.
             */
            int enteredSide = (direction + 2) % 4;

            // 기차가 들어온 면에 선로가 없다면 잘못된 이동이다.
            if (!hasDirection(mask, enteredSide)) {
                return false;
            }

            int nextDirection = -1;

            if (mask == TYPE[3]) {
                // 십자 선로에서는 꺾지 않고 현재 방향으로 직진한다.
                nextDirection = direction;
            } else {
                /*
                 * 일반 선로에는 두 개의 연결 방향이 있다.
                 * 들어온 면을 제외한 나머지 방향으로 나간다.
                 */
                for (int d = 0; d < 4; d++) {
                    if (d != enteredSide && hasDirection(mask, d)) {
                        nextDirection = d;
                        break;
                    }
                }
            }

            if (nextDirection == -1) {
                return false;
            }

            // 찾은 방향으로 한 칸 이동한다.
            r += DR[nextDirection];
            c += DC[nextDirection];
            direction = nextDirection;
        }

        /*
         * 목적지에 도착했더라도 방문하지 않은 선로가 있다면 실패다.
         * 문제에서는 격자에 놓인 모든 선로를 지나야 하기 때문이다.
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0 && !visitedCell[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * mask 선로가 direction 방향으로 열려 있는지 확인한다.
     *
     * 예: 가로 선로 1010에서 오른쪽 비트 0010을 AND하면
     *     결과가 0010이므로 오른쪽으로 열려 있다.
     */
    private boolean hasDirection(int mask, int direction) {
        int directionBit = 1 << direction;
        return (mask & directionBit) != 0;
    }

    // (r, c)가 격자 안에 있는지 확인한다.
    private boolean isInside(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}
