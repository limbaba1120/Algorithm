# 기차 선로 - Java 풀이

- 문제: [프로그래머스 2025 카카오 하반기 2차 - 기차 선로](https://school.programmers.co.kr/learn/courses/30/lessons/468381)
- 핵심 알고리즘: 백트래킹, 가지치기, 시뮬레이션, 비트마스크

## 1. 문제를 간단히 바꾸어 말하면

격자의 각 칸은 다음 중 하나이다.

- `0`: 빈칸
- `-1`: 장애물
- `1~7`: 이미 놓여 있는 선로

빈칸에는 선로를 놓지 않거나, 1~7번 선로 중 하나를 놓을 수 있다.
선로를 모두 놓은 뒤 기차는 왼쪽 위 `(0, 0)`에서 오른쪽으로 출발한다.

다음 조건을 모두 만족하는 선로 배치의 수를 구해야 한다.

1. 기차가 오른쪽 아래 `(n-1, m-1)`에 도착한다.
2. 기차가 격자에 놓인 모든 선로를 한 번 이상 지난다.
3. 이웃한 두 선로의 연결 방향이 서로 맞아야 한다.
4. 3번 십자 선로는 상하좌우 모두 연결되어야 한다.

## 2. 풀이를 두 단계로 나누기

이 문제는 다음 두 단계로 생각하면 쉽다.

```text
1단계: 빈칸마다 무엇을 놓을지 결정한다.
2단계: 완성된 선로에서 기차를 실제로 움직인다.
```

1단계에서는 백트래킹을 사용한다.

```text
현재 빈칸
 ├─ 빈칸으로 둔다.
 ├─ 1번 선로를 놓는다.
 ├─ 2번 선로를 놓는다.
 ├─ ...
 └─ 7번 선로를 놓는다.
```

선로 하나를 놓을 때마다 주변과 연결할 수 있는지 확인한다. 연결할 수 없다면 그 선택에서 시작되는 경우는 더 확인하지 않는다. 이것을 **가지치기**라고 한다.

모든 칸의 선택이 끝나면 2단계에서 기차를 직접 움직여 최종 정답인지 확인한다.

## 3. 왜 백트래킹이 가능한가?

빈칸 하나에는 8가지 선택지가 있다.

```text
선로를 놓지 않기 + 선로 7종류 = 8가지
```

단순히 모든 조합을 끝까지 만들면 경우의 수가 많다. 하지만 현재 칸에 선로를 놓는 순간 다음과 같은 잘못된 선택을 제거할 수 있다.

- 선로가 격자 밖을 향한다.
- 선로가 장애물을 향한다.
- 위쪽 선로와 연결이 맞지 않는다.
- 왼쪽 선로와 연결이 맞지 않는다.
- 처음부터 놓여 있던 고정 선로와 연결이 맞지 않는다.

예를 들어 위쪽 칸이 아래로 연결되어 있는데 현재 칸이 위로 연결되어 있지 않다면, 남은 칸에 무엇을 놓아도 이 연결은 고칠 수 없다.

```text
위쪽 선로
    │

현재 선로 ──    연결이 끊겼으므로 즉시 실패
```

격자는 최대 20칸이므로 이런 가지치기를 적용한 백트래킹으로 해결할 수 있다.

## 4. 선로를 숫자로 표현하는 이유

### 4-1. 먼저 필요한 질문

선로를 놓을 때마다 다음 질문을 자주 해야 한다.

```text
이 선로는 위쪽으로 열려 있는가?
이 선로는 오른쪽으로 열려 있는가?
이 선로는 아래쪽으로 열려 있는가?
이 선로는 왼쪽으로 열려 있는가?
```

선로마다 `boolean[4]`를 만들어도 되지만, 하나의 정수에 네 방향 정보를 모두 저장하면 코드가 간단해진다.

각 방향에 서로 겹치지 않는 값을 준다.

```text
위쪽   = 1 = 0001
오른쪽 = 2 = 0010
아래쪽 = 4 = 0100
왼쪽   = 8 = 1000
```

오른쪽과 왼쪽이 열린 가로 선로는 두 값을 합친다.

```text
오른쪽 0010
왼쪽   1000
-------------
가로   1010 = 10
```

위쪽과 아래쪽이 열린 세로 선로는 다음과 같다.

```text
위쪽   0001
아래쪽 0100
-------------
세로   0101 = 5
```

이 숫자를 **비트마스크**라고 부른다.

```java
private static final int UP = 1;
private static final int RIGHT = 2;
private static final int DOWN = 4;
private static final int LEFT = 8;
```

### 4-2. 선로 번호를 비트마스크로 바꾸기

```java
private static final int[] TYPE = {
    0,                              // 0: 빈칸
    RIGHT | LEFT,                   // 1번: 가로
    UP | DOWN,                      // 2번: 세로
    UP | RIGHT | DOWN | LEFT,       // 3번: 십자
    UP | LEFT,                      // 4번
    UP | RIGHT,                     // 5번
    RIGHT | DOWN,                   // 6번
    DOWN | LEFT                     // 7번
};
```

`|`는 두 방향 정보를 합친다고 이해하면 된다.

```java
RIGHT | LEFT
```

위 코드는 “오른쪽과 왼쪽으로 연결된 선로”라는 뜻이다.

## 5. 특정 방향이 열려 있는지 확인하기

### 5-1. 방향 번호

배열에서 사용할 방향 번호를 다음과 같이 정한다.

```text
0: 위
1: 오른쪽
2: 아래
3: 왼쪽
```

```java
private static final int[] DR = {-1, 0, 1, 0};
private static final int[] DC = {0, 1, 0, -1};
```

예를 들어 `d = 1`이면 다음 칸은 오른쪽 칸이다.

```java
int nr = r + DR[1]; // r + 0
int nc = c + DC[1]; // c + 1
```

### 5-2. `hasDirection()` 이해하기

```java
private boolean hasDirection(int mask, int direction) {
    return (mask & (1 << direction)) != 0;
}
```

처음에는 이 코드가 낯설 수 있다. 의미는 단순하다.

```text
hasDirection(선로, 방향)
= 그 선로가 해당 방향으로 열려 있는가?
```

예를 들어 가로 선로는 `1010`이다. 오른쪽은 방향 번호 1이므로 `1 << 1`은 `0010`이다.

```text
가로 선로     1010
오른쪽 확인값 0010
AND 결과      0010  → 0이 아니므로 오른쪽으로 열려 있음
```

위쪽은 방향 번호 0이다.

```text
가로 선로   1010
위쪽 확인값 0001
AND 결과    0000  → 0이므로 위쪽으로 열려 있지 않음
```

따라서 호출하는 쪽에서는 비트 계산을 신경 쓰지 않고 다음처럼 읽으면 된다.

```java
if (hasDirection(mask, d)) {
    // 이 선로는 d 방향으로 연결되어 있다.
}
```

## 6. 현재 칸에 선로를 놓을 수 있는지 확인하기

두 이웃 칸 사이의 연결은 양쪽이 같아야 한다.

```text
현재 칸의 오른쪽 O  ↔ 오른쪽 칸의 왼쪽 O : 정상
현재 칸의 오른쪽 O  ↔ 오른쪽 칸의 왼쪽 X : 실패
현재 칸의 오른쪽 X  ↔ 오른쪽 칸의 왼쪽 O : 실패
현재 칸의 오른쪽 X  ↔ 오른쪽 칸의 왼쪽 X : 정상
```

방향 `d`의 반대 방향은 다음 식으로 구할 수 있다.

```java
int opposite = (d + 2) % 4;
```

```text
위(0)     ↔ 아래(2)
오른쪽(1) ↔ 왼쪽(3)
```

격자를 왼쪽 위부터 행 순서대로 채우면 현재 칸의 위쪽과 왼쪽 칸은 이미 결정되어 있다.

```text
이미 결정  이미 결정  이미 결정
이미 결정  현재 칸    아직 결정 전
아직 결정 전 ...
```

따라서 위쪽과 왼쪽 연결은 즉시 검사할 수 있다. 아래쪽과 오른쪽도 장애물, 격자 바깥, 고정 선로라면 즉시 검사할 수 있다.

```java
private boolean canPlace(int r, int c, int mask) {
    for (int d = 0; d < 4; d++) {
        // 현재 선로가 d 방향으로 열려 있는가?
        boolean currentConnected = hasDirection(mask, d);

        int nr = r + DR[d];
        int nc = c + DC[d];

        // 격자 밖을 향하는 선로는 놓을 수 없다.
        if (!isInside(nr, nc)) {
            if (currentConnected) {
                return false;
            }
            continue;
        }

        // 장애물을 향하는 선로도 놓을 수 없다.
        if (original[nr][nc] == -1) {
            if (currentConnected) {
                return false;
            }
            continue;
        }

        // 행 순서상 위쪽과 왼쪽 칸은 이미 결정되어 있다.
        boolean alreadyDecided = nr < r || (nr == r && nc < c);

        // 처음부터 놓여 있던 선로는 위치와 관계없이 모양을 안다.
        boolean fixedTrack = original[nr][nc] > 0;

        if (alreadyDecided || fixedTrack) {
            int neighborMask = fixedTrack
                ? TYPE[original[nr][nc]]
                : board[nr][nc];

            // 이웃 칸에서는 반대 방향이 열려 있어야 한다.
            int opposite = (d + 2) % 4;
            boolean neighborConnected =
                hasDirection(neighborMask, opposite);

            // 한쪽만 열려 있으면 서로 연결되지 않는다.
            if (currentConnected != neighborConnected) {
                return false;
            }
        }
    }

    return true;
}
```

## 7. 백트래킹으로 모든 칸 결정하기

`dfs(index)`의 뜻은 다음과 같다.

```text
index번째 칸부터 가능한 선로를 놓아 보고,
조건을 만족하는 전체 배치가 몇 개인지 반환한다.
```

`index`를 행과 열로 바꾸는 방법은 다음과 같다.

```java
int r = index / m;
int c = index % m;
```

가로 길이가 3인 격자라면 다음 순서로 진행한다.

```text
index: 0 1 2
       3 4 5
       6 7 8
```

현재 칸이 빈칸이면 `0~7`을 하나씩 시도한다. 선택한 선로가 주변과 맞지 않으면 다음 선택으로 넘어간다.

```java
private int dfs(int index) {
    // 모든 칸을 결정했다면 기차를 실제로 움직여 본다.
    if (index == n * m) {
        return canTravelAllTracks() ? 1 : 0;
    }

    int r = index / m;
    int c = index % m;

    // 장애물에는 놓을 것이 없으므로 다음 칸으로 간다.
    if (original[r][c] == -1) {
        return dfs(index + 1);
    }

    // 처음부터 있던 선로는 변경하지 않고 다음 칸으로 간다.
    // 출발점 선로는 왼쪽 연결이 격자 밖을 향할 수 있으므로
    // 새 선로에 사용하는 canPlace 검사를 적용하지 않는다.
    if (original[r][c] > 0) {
        return dfs(index + 1);
    }

    int count = 0;

    // 원래 빈칸에는 빈칸 또는 1~7번 선로를 시도한다.
    for (int type = 0; type <= 7; type++) {
        int mask = TYPE[type];

        // 주변과 연결되지 않는 선택은 즉시 버린다.
        if (!canPlace(r, c, mask)) {
            continue;
        }

        // 현재 선택을 board에 기록한다.
        board[r][c] = mask;

        // 다음 칸에서 나오는 정답 수를 더한다.
        count += dfs(index + 1);
    }

    // 이전 단계로 돌아가기 전에 현재 칸을 원상 복구한다.
    board[r][c] = 0;

    return count;
}
```

## 8. 완성된 선로에서 기차 움직이기

선로의 연결이 모두 맞더라도 정답이 아닐 수 있다.

```text
출발 ───── 목적지

       ┌──┐
       └──┘
```

기차는 목적지에 도착하지만 아래의 별도 선로를 방문하지 않는다. 문제에서는 모든 선로를 지나야 하므로 이런 배치는 실패다.

따라서 완성된 배치마다 기차를 직접 움직여 본다.

### 8-1. 기차의 상태

기차의 상태는 다음 세 값으로 표현한다.

```text
현재 행, 현재 열, 현재 진행 방향
```

진행 방향까지 기록하는 이유는 십자 선로 때문이다.

```text
    │
────┼────
    │
```

같은 십자 칸이어도 가로로 통과하는 상태와 세로로 통과하는 상태는 다르다.

```java
boolean[][][] visitedState = new boolean[n][m][4];
```

같은 칸에 같은 진행 방향으로 다시 도착하면 이후에도 똑같이 움직이므로 무한 반복이다.

### 8-2. 들어온 방향 구하기

기차가 오른쪽으로 이동하여 현재 칸에 왔다면 현재 칸의 왼쪽에서 들어온 것이다.

```text
이전 칸  →  현재 칸
          왼쪽 면으로 들어옴
```

따라서 들어온 면은 진행 방향의 반대 방향이다.

```java
int enteredSide = (direction + 2) % 4;
```

### 8-3. 다음 방향 구하기

일반 선로는 두 방향으로 연결되어 있다. 들어온 방향을 제외한 나머지 방향으로 나가면 된다.

```text
기차 → ┐
       ↓

왼쪽에서 들어왔으므로 아래쪽으로 나간다.
```

십자 선로에서는 현재 진행 방향을 그대로 유지한다.

```text
→ ┼ →

  ↓
  ┼
  ↓
```

## 9. 기차 이동 코드

```java
private boolean canTravelAllTracks() {
    // 각 선로 칸을 한 번이라도 지났는지 기록한다.
    boolean[][] visitedCell = new boolean[n][m];

    // 같은 칸이라도 진행 방향이 다르면 다른 상태이다.
    boolean[][][] visitedState = new boolean[n][m][4];

    // 기차는 (0, 0)에서 오른쪽으로 출발한다.
    int r = 0;
    int c = 0;
    int direction = 1;

    while (true) {
        // 격자 밖 또는 선로가 없는 칸에 도착하면 실패한다.
        if (!isInside(r, c) || board[r][c] <= 0) {
            return false;
        }

        // 같은 상태가 반복되면 기차가 무한히 순환한다.
        if (visitedState[r][c][direction]) {
            return false;
        }

        visitedState[r][c][direction] = true;
        visitedCell[r][c] = true;

        // 목적지에 도착하면 이동을 끝낸다.
        if (r == n - 1 && c == m - 1) {
            break;
        }

        int mask = board[r][c];

        // 진행 방향의 반대쪽이 기차가 들어온 면이다.
        int enteredSide = (direction + 2) % 4;

        // 들어온 면에 선로가 없다면 정상적으로 이동할 수 없다.
        if (!hasDirection(mask, enteredSide)) {
            return false;
        }

        int nextDirection = -1;

        if (mask == TYPE[3]) {
            // 십자 선로에서는 현재 방향으로 직진한다.
            nextDirection = direction;
        } else {
            // 일반 선로에서는 들어온 면이 아닌 연결 방향으로 나간다.
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

        // 다음 칸으로 이동한다.
        r += DR[nextDirection];
        c += DC[nextDirection];
        direction = nextDirection;
    }

    // 목적지 도착 후 모든 선로를 방문했는지 확인한다.
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (board[i][j] > 0 && !visitedCell[i][j]) {
                return false;
            }
        }
    }

    return true;
}
```

## 10. 전체 Java 코드

```java
class Solution {

    // 방향별 비트 값
    private static final int UP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 4;
    private static final int LEFT = 8;

    // 방향 번호: 0=위, 1=오른쪽, 2=아래, 3=왼쪽
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    // 선로 번호를 연결 방향 비트마스크로 변환한다.
    private static final int[] TYPE = {
        0,
        RIGHT | LEFT,
        UP | DOWN,
        UP | RIGHT | DOWN | LEFT,
        UP | LEFT,
        UP | RIGHT,
        RIGHT | DOWN,
        DOWN | LEFT
    };

    private int n;
    private int m;
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
                    board[r][c] = -1;
                } else if (grid[r][c] > 0) {
                    board[r][c] = TYPE[grid[r][c]];
                }
            }
        }

        return dfs(0);
    }

    private int dfs(int index) {
        if (index == n * m) {
            return canTravelAllTracks() ? 1 : 0;
        }

        int r = index / m;
        int c = index % m;

        if (original[r][c] == -1) {
            return dfs(index + 1);
        }

        if (original[r][c] > 0) {
            return dfs(index + 1);
        }

        int count = 0;

        for (int type = 0; type <= 7; type++) {
            int mask = TYPE[type];

            if (!canPlace(r, c, mask)) {
                continue;
            }

            board[r][c] = mask;
            count += dfs(index + 1);
        }

        board[r][c] = 0;
        return count;
    }

    private boolean canPlace(int r, int c, int mask) {
        for (int d = 0; d < 4; d++) {
            boolean currentConnected = hasDirection(mask, d);

            int nr = r + DR[d];
            int nc = c + DC[d];

            if (!isInside(nr, nc)) {
                if (currentConnected) {
                    return false;
                }
                continue;
            }

            if (original[nr][nc] == -1) {
                if (currentConnected) {
                    return false;
                }
                continue;
            }

            boolean alreadyDecided = nr < r || (nr == r && nc < c);
            boolean fixedTrack = original[nr][nc] > 0;

            if (alreadyDecided || fixedTrack) {
                int neighborMask = fixedTrack
                    ? TYPE[original[nr][nc]]
                    : board[nr][nc];

                int opposite = (d + 2) % 4;
                boolean neighborConnected =
                    hasDirection(neighborMask, opposite);

                if (currentConnected != neighborConnected) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean canTravelAllTracks() {
        boolean[][] visitedCell = new boolean[n][m];
        boolean[][][] visitedState = new boolean[n][m][4];

        int r = 0;
        int c = 0;
        int direction = 1;

        while (true) {
            if (!isInside(r, c) || board[r][c] <= 0) {
                return false;
            }

            if (visitedState[r][c][direction]) {
                return false;
            }

            visitedState[r][c][direction] = true;
            visitedCell[r][c] = true;

            if (r == n - 1 && c == m - 1) {
                break;
            }

            int mask = board[r][c];
            int enteredSide = (direction + 2) % 4;

            if (!hasDirection(mask, enteredSide)) {
                return false;
            }

            int nextDirection = -1;

            if (mask == TYPE[3]) {
                nextDirection = direction;
            } else {
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

            r += DR[nextDirection];
            c += DC[nextDirection];
            direction = nextDirection;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0 && !visitedCell[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasDirection(int mask, int direction) {
        return (mask & (1 << direction)) != 0;
    }

    private boolean isInside(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}
```

## 11. 마지막 요약

이 문제에서 기억해야 할 핵심은 세 가지다.

1. 빈칸마다 `빈칸 또는 1~7번 선로`를 시도한다.
2. 선로를 놓을 때마다 주변 연결을 확인해 잘못된 경우를 즉시 버린다.
3. 모든 칸을 결정한 후 기차를 직접 움직여 목적지 도착과 전체 선로 방문을 확인한다.

```text
백트래킹으로 선로 배치
          ↓
canPlace로 잘못된 연결 제거
          ↓
기차 시뮬레이션
          ↓
목적지 도착 + 모든 선로 방문
          ↓
정답 1개로 계산
```
