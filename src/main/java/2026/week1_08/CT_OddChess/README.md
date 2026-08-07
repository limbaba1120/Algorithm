# 술래잡기 체스

- 풀이 날짜: 2026-08-07
- 플랫폼: Codetree
- 유형: 시뮬레이션, DFS, 백트래킹, 완전 탐색
- 핵심 내용: 말 이동, 상태 복사, 술래의 모든 선택 탐색
- 문제 링크: https://www.codetree.ai/training-field/frequent-problems/problems/odd-chess2/description

## 1. 문제를 크게 두 부분으로 나누기

한 턴은 다음 순서로 진행된다.

```text
1번부터 16번까지 살아 있는 말이 순서대로 이동
                       ↓
술래가 자신의 방향에 있는 말 중 하나를 선택해서 잡음
                       ↓
잡은 말의 방향을 술래의 새로운 방향으로 사용
                       ↓
다음 턴 반복
```

말은 정해진 규칙대로 이동하므로 **시뮬레이션**으로 처리한다. 술래는 잡을 수 있는 말이 여러 개일 수 있으므로 모든 선택을 **DFS**로 탐색한다.

## 2. 게임 상태를 두 자료구조로 저장하는 이유

### `board`: 위치로 말 번호 찾기

```java
int[][] board = new int[4][4];
```

`board[row][col]`에는 해당 칸에 있는 말 번호를 저장한다.

```text
board[1][2] == 7 → (1, 2)에 7번 말이 있음
board[1][2] == 0 → (1, 2)는 말이 없는 빈칸
```

말이 이동하려는 목적지에 다른 말이 있는지 바로 확인할 수 있다.

```java
int otherNumber = board[nextRow][nextCol];
```

### `pieces`: 말 번호로 상태 찾기

```java
Piece[] pieces = new Piece[17];
```

말은 반드시 1번부터 16번까지 순서대로 이동한다. 따라서 말 번호를 알 때 위치와 방향을 바로 찾을 수 있어야 한다.

```java
Piece piece = pieces[number];
```

정리하면 두 자료구조의 역할은 서로 반대다.

```text
board   : 위치 → 말 번호
pieces  : 말 번호 → 위치, 방향, 생존 여부
```

말이 움직일 때는 `board`와 `pieces`를 반드시 함께 수정해야 두 정보가 계속 일치한다.

## 3. 방향 배열 이해하기

```java
static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
static final int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
```

같은 인덱스의 `dr`, `dc`가 방향 하나를 나타낸다.

| 인덱스 | `dr` | `dc` | 방향 |
|---:|---:|---:|---|
| 0 | -1 | 0 | 위 |
| 1 | -1 | -1 | 왼쪽 위 |
| 2 | 0 | -1 | 왼쪽 |
| 3 | 1 | -1 | 왼쪽 아래 |
| 4 | 1 | 0 | 아래 |
| 5 | 1 | 1 | 오른쪽 아래 |
| 6 | 0 | 1 | 오른쪽 |
| 7 | -1 | 1 | 오른쪽 위 |

입력 방향은 1부터 8까지이지만 배열 인덱스는 0부터 7까지이므로 입력할 때 1을 뺀다.

```java
int dir = Integer.parseInt(st.nextToken()) - 1;
```

## 4. 말 한 마리 이동하기

말은 현재 방향을 먼저 확인한다. 이동할 수 없다면 반시계 방향으로 45도씩 회전하며 최대 8방향을 확인한다.

```java
for (int rotation = 0; rotation < 8; rotation++) {
    int nextDir = (piece.dir + rotation) % 8;
}
```

`rotation`은 방향 번호가 아니라 현재 방향에서 회전한 횟수다.

```text
rotation = 0 → 회전하지 않고 현재 방향 확인
rotation = 1 → 45도 회전한 방향 확인
...
rotation = 7 → 마지막 남은 방향 확인
```

다음 두 경우에는 이동할 수 없다.

- 목적지가 체스판 밖인 경우
- 목적지에 술래가 있는 경우

목적지에 다른 말이 있으면 두 말의 위치를 바꾼다. 목적지가 빈칸이면 현재 말만 빈칸으로 이동한다.

```text
다른 말이 있는 경우: [7번][12번] → [12번][7번]
빈칸인 경우:          [7번][ 0번] → [ 0번][7번]
```

이를 같은 코드로 처리할 수 있다.

```java
board[currentRow][currentCol] = otherNumber;
board[nextRow][nextCol] = number;
```

목적지에 실제 말이 있을 때만 상대 말 객체의 좌표도 바꾼다.

```java
if (otherNumber != 0) {
    pieces[otherNumber].row = currentRow;
    pieces[otherNumber].col = currentCol;
}
```

현재 이동한 말의 정보도 바꾼다.

```java
piece.row = nextRow;
piece.col = nextCol;
piece.dir = nextDir;
```

이동에 성공하면 `return`해야 한다. 그렇지 않으면 같은 말이 한 턴에 여러 번 이동하게 된다.

## 5. 술래 이동을 DFS로 처리하는 이유

술래의 방향에 말이 여러 마리 있으면 어느 말을 잡아야 최대 점수를 얻는지 바로 결정할 수 없다.

```text
현재 상태
 ├─ 7번 말을 잡는 경우
 ├─ 12번 말을 잡는 경우
 └─ 4번 말을 잡는 경우
```

따라서 가능한 선택을 전부 실행하고 가장 큰 점수를 구한다.

```java
dfs(board, pieces, hunterRow, hunterCol, hunterDir, score);
```

DFS에 전달되는 값은 현재 게임을 완전히 설명한다.

- `board`: 현재 체스판
- `pieces`: 모든 말의 현재 상태
- `hunterRow`, `hunterCol`: 술래 위치
- `hunterDir`: 술래 방향
- `score`: 지금까지 잡은 말 번호의 합

## 6. 배열을 복사하는 이유

배열 복사는 게임을 저장한 뒤 서로 다른 선택을 시험하는 것과 같다.

```text
현재 게임을 저장
 ├─ 복사본 A에서 7번 말 잡기
 └─ 복사본 B에서 12번 말 잡기
```

복사하지 않으면 7번 말을 잡으며 변경한 체스판과 말 위치가 12번 말을 잡는 경우에도 남는다. 그러면 두 경우가 같은 출발 상태에서 시작하지 않게 된다.

### `board` 복사

Java의 2차원 배열은 행 배열들을 담은 배열이다. 바깥 배열만 `clone()`하면 각 행은 여전히 원본과 공유된다. 따라서 행도 하나씩 복사한다.

```java
for (int row = 0; row < SIZE; row++) {
    copy[row] = board[row].clone();
}
```

### `pieces` 복사

`Piece[]`만 복사하면 배열 내부의 `Piece` 객체는 원본과 공유된다. 따라서 말 객체도 하나씩 새로 생성한다.

```java
copy[number] = new Piece(
        piece.row,
        piece.col,
        piece.dir,
        piece.alive
);
```

## 7. 술래가 여러 칸을 확인하는 방법

술래는 현재 방향으로 한 칸만 움직이는 것이 아니라 1칸, 2칸, 3칸 떨어진 말을 잡을 수 있다.

```java
int nextRow = hunterRow + dr[hunterDir] * distance;
int nextCol = hunterCol + dc[hunterDir] * distance;
```

`dr`, `dc`는 한 칸의 이동량이므로 `distance`를 곱해 같은 방향의 더 먼 칸을 구한다.

술래는 빈칸을 통과할 수 있지만 빈칸에 멈출 수는 없다.

```java
if (targetNumber == 0) {
    continue;
}
```

체스판 밖으로 나가면 더 먼 칸도 모두 체스판 밖이므로 반복을 종료한다.

```java
if (!isInRange(nextRow, nextCol)) {
    break;
}
```

## 8. 말을 잡은 뒤 바뀌는 상태

술래가 말을 잡으면 다음 세 가지가 바뀐다.

```java
nextBoard[nextRow][nextCol] = 0; // 체스판에서 말 제거
target.alive = false;            // 잡힌 상태로 변경
```

다음 DFS에서는 잡은 말의 위치와 방향을 술래가 이어받고, 잡은 말 번호를 점수에 더한다.

```java
dfs(
        nextBoard,
        nextPieces,
        nextRow,
        nextCol,
        target.dir,
        score + targetNumber
);
```

## 9. 전체 실행 흐름

```text
(0, 0)의 말을 잡고 최초 점수와 방향 설정
                    ↓
                  DFS 시작
                    ↓
     현재 점수로 최대 점수 answer 갱신
                    ↓
       1번부터 16번까지 말 이동
                    ↓
 술래 방향의 1칸, 2칸, 3칸 위치 확인
                    ↓
      말이 있는 각 위치마다 상태 복사
                    ↓
        해당 말을 잡고 다음 DFS 호출
                    ↓
       더 잡을 말이 없으면 현재 경로 종료
```

## 10. 자주 실수할 수 있는 부분

- 입력 방향에서 1을 빼지 않는 실수
- 잡힌 말을 이동시키는 실수
- 말 이동 성공 후 `return`하지 않는 실수
- `board`만 수정하고 `pieces` 좌표를 수정하지 않는 실수
- `pieces`만 수정하고 `board`를 수정하지 않는 실수
- 술래가 있는 칸으로 말을 이동시키는 실수
- 빈칸을 만났을 때 `break`하여 뒤쪽 말을 확인하지 않는 실수
- DFS 분기마다 상태를 복사하지 않는 실수
- `Piece[]` 배열만 복사하고 내부 객체를 공유하는 얕은 복사 실수

## 11. 복잡도

체스판과 말의 수가 각각 4×4, 16개로 고정되어 있다. 각 DFS에서 말 16마리를 이동하고 술래의 후보 위치를 최대 3개 확인한다. 술래가 선택할 수 있는 모든 경로를 탐색하지만 문제 크기가 작아 완전 탐색이 가능하다.

이 문제에서는 일반적인 입력 크기 `N`이 커지는 형태가 아니므로 단순한 하나의 빅오 표현보다, **작은 고정 크기의 상태를 복사하며 가능한 술래 이동 경로를 모두 탐색한다**고 이해하는 것이 중요하다.
