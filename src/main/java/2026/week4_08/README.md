# 보물 찾기

- 문제 번호: 468378
- 난이도: Level 3
- 풀이 날짜: 2026-08-23
- 핵심 알고리즘: 구간 DP, 최악의 경우 최소화
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/468378

## 아주 간단한 핵심

각 열은 굴착 비용이 다르다. 따라서 단순히 가운데 열을 파는 이분 탐색 대신, 각 구간에서 다음 질문의 답을 미리 계산한다.

```text
이 구간에서는 어느 열을 먼저 파야
보물이 어디에 있더라도 필요한 비용이 가장 작을까?
```

## 두 배열의 의미

```text
dp[left][right]
→ left부터 right 사이의 보물을 반드시 찾는 최소 비용

choice[left][right]
→ 그 구간에서 가장 먼저 굴착할 인덱스
```

## 한 칸만 남은 경우

가능한 열이 `i` 하나뿐이면 그 열을 반드시 파야 한다.

```java
dp[i][i] = depth[i];
choice[i][i] = i;
```

## 한 후보의 비용

구간 `[left, right]`에서 `mid`를 먼저 굴착한다고 생각한다.

```text
              mid
               │
[left ... mid-1] [mid+1 ... right]
       왼쪽             오른쪽
```

보물이 어느 방향에 있을지 모르므로 왼쪽과 오른쪽 중 더 비싼 경우를 준비해야 한다.

```java
candidateCost = depth[mid] + Math.max(leftCost, rightCost);
```

즉 다음 두 비용을 더한다.

```text
mid를 지금 파는 비용
+
왼쪽과 오른쪽 중 더 비싼 추가 탐색 비용
```

구간 안의 모든 `mid`를 후보로 확인하고 `candidateCost`가 가장 작은 열을 `choice`에 저장한다.

## 실제 굴착

DP 계산 후에는 전체 구간부터 `choice`를 따라간다.

```text
excavate 결과가 0  → 보물 발견
excavate 결과가 -1 → 왼쪽 구간으로 이동
excavate 결과가 1  → 오른쪽 구간으로 이동
```

Java 배열 인덱스는 `0`부터 시작하고 실제 열 번호는 `1`부터 시작하므로 다음처럼 호출한다.

```java
excavate.apply(mid + 1);
```

## 전체 흐름

```text
한 칸 구간의 비용 계산
        ↓
길이 2, 3, ... 순서로 구간 계산
        ↓
각 구간에서 모든 첫 굴착 후보 비교
        ↓
최악의 비용이 가장 작은 후보 저장
        ↓
전체 구간부터 choice를 따라 실제 굴착
```

## 복잡도

- 시간 복잡도: `O(w³)`
- 공간 복잡도: `O(w²)`

`w`가 최대 200이므로 구간마다 모든 첫 굴착 후보를 확인할 수 있다.

## 전체 코드

```java
import java.util.function.Function;

class PG_468378 {
    public int solution(
            int[] depth,
            int money,
            Function<Integer, Integer> excavate
    ) {
        int w = depth.length;

        int[][] dp = new int[w][w];
        int[][] choice = new int[w][w];

        for (int i = 0; i < w; i++) {
            dp[i][i] = depth[i];
            choice[i][i] = i;
        }

        for (int length = 2; length <= w; length++) {
            for (int left = 0; left + length - 1 < w; left++) {
                int right = left + length - 1;
                int bestCost = Integer.MAX_VALUE;
                int bestMid = -1;

                for (int mid = left; mid <= right; mid++) {
                    int leftCost = mid > left ? dp[left][mid - 1] : 0;
                    int rightCost = mid < right ? dp[mid + 1][right] : 0;

                    int candidateCost =
                            depth[mid] + Math.max(leftCost, rightCost);

                    if (candidateCost < bestCost) {
                        bestCost = candidateCost;
                        bestMid = mid;
                    }
                }

                dp[left][right] = bestCost;
                choice[left][right] = bestMid;
            }
        }

        int left = 0;
        int right = w - 1;

        while (left <= right) {
            int mid = choice[left][right];
            int result = excavate.apply(mid + 1);

            if (result == 0) {
                return mid + 1;
            }

            if (result == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return 0;
    }
}
```

---

# 여행경로

- 문제 번호: 43164
- 난이도: Level 3
- 풀이 날짜: 2026-08-25
- 핵심 자료구조: 방문 배열, `ArrayList`
- 핵심 알고리즘: DFS, 백트래킹, 문자열 사전순 정렬
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/43164

## 문제 접근

모든 항공권을 정확히 한 번씩 사용해 `ICN`에서 출발하는 경로를 만든다. 가능한 경로가 여러 개면 사전순으로 가장 빠른 경로를 반환해야 한다.

```text
항공권을 사전순으로 정렬
        ↓
ICN에서 DFS 시작
        ↓
현재 공항에서 출발하는 미사용 항공권 선택
        ↓
도착 공항에서 다음 항공권 탐색
        ↓
모든 항공권을 사용하면 경로 저장
```

## 항공권 방문 배열

```java
static boolean[] isVisited;
```

`isVisited[i]`는 공항의 방문 여부가 아니라 `tickets[i]` 항공권의 사용 여부다.

```text
false → 아직 사용하지 않은 항공권
true  → 이미 사용한 항공권
```

동일한 공항은 여러 번 방문할 수 있지만 동일한 항공권은 한 번만 사용할 수 있다.

```text
ICN → ATL → ICN → SFO
```

위 경로는 ICN을 두 번 방문하지만 다음과 같이 서로 다른 항공권을 사용한다.

```text
ICN → ATL
ATL → ICN
ICN → SFO
```

## `equals()`

`equals()`는 두 문자열의 내용이 같은지 확인한다.

```java
now.equals(tickets[i][0])
```

현재 공항과 항공권의 출발 공항이 같은지 검사하는 코드다.

```text
now = "ICN"
tickets[i][0] = "ICN"

now.equals(tickets[i][0]) → true
```

문자열 내용 비교에는 `==`가 아니라 `equals()`를 사용한다.

```java
first[0].equals(second[0])
```

위 코드는 비교할 두 항공권의 출발 공항이 같은지 확인한다.

## `contentEquals()`

`contentEquals()`도 문자열의 문자 내용이 같은지 확인할 수 있다.

```java
first[0].contentEquals(second[0])
```

`equals()`는 일반적으로 다른 `String`과 비교할 때 사용하고, `contentEquals()`는 `StringBuffer`나 다른 `CharSequence`와 문자 내용을 비교할 때도 사용할 수 있다.

현재 코드는 `String`과 `String`을 비교하므로 다음 표현이 더 간단하고 자연스럽다.

```java
first[0].equals(second[0])
```

## `compareTo()`

`compareTo()`는 두 문자열의 사전순 순서를 비교한다.

```java
first.compareTo(second)
```

반환 규칙은 다음과 같다.

```text
음수 → first가 second보다 사전순으로 앞
0   → 두 문자열이 같음
양수 → first가 second보다 사전순으로 뒤
```

예를 들어:

```java
"ATL".compareTo("SFO") // 음수
"ICN".compareTo("ICN") // 0
"SFO".compareTo("ATL") // 양수
```

`equals()`와의 차이는 다음과 같다.

```text
equals()    → 내용이 같은지 확인하여 boolean 반환
compareTo() → 사전순 앞뒤를 비교하여 int 반환
```

## `Arrays.sort()`

```java
Arrays.sort(tickets, comparator);
```

배열을 주어진 비교 기준에 따라 정렬한다. `tickets`의 원소는 `String[]`이므로 정렬 기준을 `Comparator<String[]>`로 전달한다.

```java
Arrays.sort(tickets, new Comparator<String[]>() {
    @Override
    public int compare(String[] first, String[] second) {
        if (first[0].equals(second[0])) {
            return first[1].compareTo(second[1]);
        }

        return first[0].compareTo(second[0]);
    }
});
```

정렬 기준은 다음과 같다.

```text
출발 공항이 다름
→ 출발 공항을 사전순으로 비교

출발 공항이 같음
→ 도착 공항을 사전순으로 비교
```

예를 들어:

```text
정렬 전
[SFO, ATL]
[ICN, SFO]
[ICN, ATL]

정렬 후
[ICN, ATL]
[ICN, SFO]
[SFO, ATL]
```

## 익명 `Comparator` 클래스

```java
new Comparator<String[]>() {
    @Override
    public int compare(String[] first, String[] second) {
        // 정렬 기준
    }
}
```

`Comparator<String[]>` 인터페이스를 이름 없는 클래스로 즉시 구현한다. `compare()`는 정렬 중 비교할 항공권 두 개를 받아 어느 항공권이 먼저 와야 하는지 반환한다.

같은 코드를 람다식으로 줄이면 다음과 같이 작성할 수 있다.

```java
Arrays.sort(tickets, (first, second) -> {
    if (first[0].equals(second[0])) {
        return first[1].compareTo(second[1]);
    }

    return first[0].compareTo(second[0]);
});
```

## DFS 인자의 의미

```java
dfs(0, "ICN", "ICN", tickets);
```

| 인자 | 의미 |
|---|---|
| `depth` | 현재까지 사용한 항공권 개수 |
| `now` | 현재 위치한 공항 |
| `path` | 지금까지 만든 여행 경로 |
| `tickets` | 전체 항공권 정보 |

모든 항공권을 사용하면 완성 경로를 저장한다.

```java
if (depth == tickets.length) {
    route.add(path);
    return;
}
```

## 다음 항공권 선택

```java
if (!isVisited[i] && now.equals(tickets[i][0])) {
```

다음 두 조건을 모두 만족해야 한다.

```text
아직 사용하지 않은 항공권
+
출발 공항이 현재 공항과 같은 항공권
```

항공권을 선택하면 사용 처리하고 도착 공항을 다음 현재 공항으로 전달한다.

```java
isVisited[i] = true;

dfs(
        depth + 1,
        tickets[i][1],
        path + " " + tickets[i][1],
        tickets
);
```

재귀 호출이 끝나면 사용 처리를 취소하여 다른 경로를 탐색한다.

```java
isVisited[i] = false;
```

이 과정을 백트래킹이라고 한다.

## 결과 반환

완성 경로는 다음처럼 공백으로 연결된 문자열로 저장한다.

```text
"ICN ATL ICN SFO"
```

사전순으로 정렬된 항공권부터 탐색했으므로 첫 번째 완성 경로를 가져와 공백으로 나눈다.

```java
return route.get(0).split(" ");
```

결과:

```text
["ICN", "ATL", "ICN", "SFO"]
```

## 전체 코드

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PG_43164 {
    static boolean[] isVisited;
    static List<String> route = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        route.clear();

        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] first, String[] second) {
                if (first[0].equals(second[0])) {
                    return first[1].compareTo(second[1]);
                }

                return first[0].compareTo(second[0]);
            }
        });

        isVisited = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        return route.get(0).split(" ");
    }

    public void dfs(
            int depth,
            String now,
            String path,
            String[][] tickets
    ) {
        if (depth == tickets.length) {
            route.add(path);
            return;
        }

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i] && now.equals(tickets[i][0])) {
                isVisited[i] = true;

                dfs(
                        depth + 1,
                        tickets[i][1],
                        path + " " + tickets[i][1],
                        tickets
                );

                isVisited[i] = false;
            }
        }
    }
}
```

## 주의할 점

`route`가 `static`이므로 같은 객체에서 `solution()`이 다시 호출될 경우 이전 결과가 남지 않도록 시작할 때 비운다.

```java
route.clear();
```

원본 코드의 `toString()`은 필요하지 않다. `first[0]`, `second[0]` 등은 이미 `String`이기 때문이다.

---

# 아이템 줍기

- 문제 번호: 87694
- 난이도: Level 3
- 풀이 날짜: 2026-08-25
- 핵심 자료구조: 큐(`ArrayDeque`), 2차원 배열
- 핵심 알고리즘: BFS, 좌표 확대, 테두리 추출
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/87694

## 문제 설명

여러 직사각형이 겹쳐 만들어진 도형의 바깥 테두리를 따라 캐릭터가 이동한다. 캐릭터의 위치에서 아이템 위치까지 이동하는 최단거리를 구한다.

```text
직사각형 내부 → 이동 불가능
직사각형 외부 → 이동 불가능
도형의 테두리 → 이동 가능
```

따라서 직사각형의 테두리만 2차원 배열에 남긴 뒤 BFS로 최단거리를 계산한다.

## 전체 접근

```text
모든 좌표를 2배로 확대
        ↓
직사각형 전체 영역을 1로 표시
        ↓
직사각형 내부를 0으로 제거
        ↓
map에 테두리만 1로 남음
        ↓
캐릭터 위치에서 BFS
        ↓
확대된 거리를 2로 나누어 반환
```

## 좌표를 2배로 확대하는 이유

원래 좌표를 그대로 격자에 표시하면 복잡하게 꺾인 부분에서 서로 가까울 뿐인 테두리가 붙어 있는 것처럼 보일 수 있다. 그러면 BFS가 실제 테두리를 돌아가지 않고 가까운 테두리로 건너가는 잘못된 지름길을 선택할 수 있다.

모든 좌표를 2배로 만들면 원래 좌표 사이에 중간 좌표가 생긴다.

```text
원래 좌표:
0 ─── 1 ─── 2

2배 좌표:
0 ─ 1 ─ 2 ─ 3 ─ 4
```

원래 좌표는 `0, 2, 4`로 이동하고, 새로 생긴 홀수 좌표가 연결된 선과 가까울 뿐인 선을 구분한다.

```java
private void doubleRectangles(int[][] rectangle) {
    for (int i = 0; i < rectangle.length; i++) {
        rectangle[i][0] *= 2;
        rectangle[i][1] *= 2;
        rectangle[i][2] *= 2;
        rectangle[i][3] *= 2;
    }
}
```

캐릭터와 아이템도 같은 지도에 표시해야 하므로 함께 확대한다.

```java
characterX = doubleCoordinate(characterX);
characterY = doubleCoordinate(characterY);
itemX = doubleCoordinate(itemX);
itemY = doubleCoordinate(itemY);
```

## 직사각형 전체 영역 표시

확대된 각 직사각형의 좌측 하단부터 우측 상단까지 모두 `1`로 채운다.

```java
for (int x = x1; x <= x2; x++) {
    for (int y = y1; y <= y2; y++) {
        map[x][y] = 1;
    }
}
```

현재는 테두리와 내부가 모두 `1`이다.

```text
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
```

## 내부를 제거하여 테두리만 남기기

양 끝 좌표는 테두리이므로 제외하고 내부 좌표만 `0`으로 변경한다.

```java
for (int x = x1 + 1; x < x2; x++) {
    for (int y = y1 + 1; y < y2; y++) {
        map[x][y] = 0;
    }
}
```

결과:

```text
1 1 1 1 1
1 0 0 0 1
1 1 1 1 1
```

```text
map[x][y] == 1 → 이동 가능한 테두리
map[x][y] == 0 → 내부 또는 외부
```

모든 영역을 먼저 채운 뒤 내부를 제거해야 겹친 직사각형 내부에 불필요한 이동 경로가 남지 않는다.

## BFS 거리 배열

방문하지 않은 좌표와 시작점 거리 `0`을 구분하기 위해 거리 배열을 `-1`로 초기화한다.

```java
for (int i = 0; i < distance.length; i++) {
    Arrays.fill(distance[i], -1);
}
```

```text
-1 → 아직 방문하지 않음
 0 → 캐릭터 시작 위치
 1 이상 → 시작점부터 이동한 거리
```

## 테두리를 따라 BFS

캐릭터의 확대된 좌표를 큐에 넣는다.

```java
queue.offer(new int[]{characterX, characterY});
distance[characterX][characterY] = 0;
```

상하좌우의 다음 좌표가 다음 조건을 모두 만족하면 이동한다.

```text
배열 범위 안
+
map 값이 1인 테두리
+
아직 방문하지 않은 좌표
```

```java
if (map[nextX][nextY] != 1) {
    continue;
}

if (distance[nextX][nextY] != -1) {
    continue;
}
```

다음 위치의 거리는 현재 거리보다 1 크다.

```java
distance[nextX][nextY] =
        distance[currentX][currentY] + 1;
```

BFS는 가까운 위치부터 탐색하므로 아이템을 처음 만났을 때의 거리가 최단거리다.

## 최종 거리를 2로 나누기

원래 거리 1은 확대된 지도에서 거리 2가 된다.

```text
원래: (1, 1) → (2, 1)       거리 1
확대: (2, 2) → (3, 2) → (4, 2) 거리 2
```

따라서 BFS 결과를 `2`로 나눈다.

```java
return doubleDistance / 2;
```

## 복잡도

확대된 지도 크기는 최대 `102 × 102`로 고정되어 있다.

- 시간 복잡도: `O(rectangle.length × 100² + 100²)`
- 공간 복잡도: `O(100²)`

## 전체 코드

전체 코드는 [PG_87694.java](./PG_87694.java)에 정리했다.

---

# 정수 삼각형

- 문제 번호: 43105
- 난이도: Level 3
- 풀이 날짜: 2026-08-26
- 핵심 알고리즘: 동적 계획법(DP)
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/43105

## DP 정의

```text
dp[i][j]
→ [i][j]까지 도착했을 때 얻을 수 있는 최대 누적합
```

같은 칸에 여러 경로로 도착할 수 있지만, 그 이후의 선택지는 동일하다. 따라서 각 경로를 모두 저장하지 않고 해당 칸까지의 최대 누적합만 저장한다.

## 시작점

시작점에 도착하는 방법은 하나뿐이다.

```java
dp[0][0] = triangle[0][0];
```

## 각 칸의 계산

왼쪽 끝은 바로 위 칸에서만 내려올 수 있다.

```text
dp[i][0] = dp[i - 1][0] + triangle[i][0]
```

오른쪽 끝은 왼쪽 위 칸에서만 내려올 수 있다.

```text
dp[i][i] = dp[i - 1][i - 1] + triangle[i][i]
```

가운데 칸은 왼쪽 위와 오른쪽 위 중 누적합이 큰 부모를 선택한다.

```text
dp[i][j]
= max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j]
```

## 정답

맨 아래의 어느 칸에서 끝나도 되므로 마지막 행의 최댓값이 정답이다.

## 복잡도

- 시간 복잡도: `O(n²)`
- 공간 복잡도: `O(n²)`

## 전체 코드

전체 코드는 [PG_43105.java](./PG_43105.java)에 정리했다.

---

# 도둑질

- 문제 번호: 42897
- 난이도: Level 4
- 풀이 날짜: 2026-08-26
- 핵심 알고리즘: 동적 계획법(DP), 원형 배열
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42897

## 문제 접근

인접한 두 집은 함께 털 수 없다. 집이 일렬로 놓여 있다면 현재 집에서 다음 두 경우를 비교한다.

```text
현재 집을 털지 않는다
→ 한 단계 전까지의 최대 금액

현재 집을 턴다
→ 두 단계 전까지의 최대 금액 + 현재 집의 돈
```

점화식은 다음과 같다.

```text
dp[i] = max(dp[i - 1], dp[i - 2] + money[i])
```

## 원형 구조 처리

첫 번째 집과 마지막 집도 서로 인접하므로 동시에 털 수 없다. 이를 보장하기 위해 계산 범위를 두 경우로 나눈다.

```text
경우 1: 0번 집부터 마지막 직전 집까지
        → 첫 번째 집 포함 가능, 마지막 집 제외

경우 2: 1번 집부터 마지막 집까지
        → 첫 번째 집 제외, 마지막 집 포함 가능
```

두 구간의 최대 금액을 각각 계산한 뒤 더 큰 값을 반환한다.

## 배열 DP

각 집까지의 최대 금액을 `dp` 배열에 모두 저장한다.

```text
dp[i]
→ 주어진 시작 집부터 i번 집까지 고려했을 때의 최대 금액
```

- 시간 복잡도: `O(n)`
- 공간 복잡도: `O(n)`
- 코드: [PG_42897_Array.java](./PG_42897_Array.java)

## 공간 최적화 DP

현재 값을 계산할 때는 `dp[i - 2]`와 `dp[i - 1]`만 필요하다. 따라서 전체 배열 대신 변수 두 개만 저장할 수 있다.

```text
twoBefore → dp[i - 2]
oneBefore → dp[i - 1]
current   → dp[i]
```

계산 후 다음 집을 위해 값을 한 단계씩 이동한다.

```text
twoBefore = oneBefore
oneBefore = current
```

- 시간 복잡도: `O(n)`
- 공간 복잡도: `O(1)`
- 코드: [PG_42897.java](./PG_42897.java)

---

# 체육복

- 문제 번호: 42862
- 난이도: Level 1
- 풀이 날짜: 2026-08-27
- 핵심 알고리즘: 그리디, `HashSet`
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42862

## 문제 접근

도난 학생과 여벌 체육복이 있는 학생을 각각 집합으로 관리한다. 두 집합에 모두 포함된 학생은 여벌 체육복으로 자신의 도난을 해결하므로 먼저 양쪽 대상에서 제외한다.

```text
도난 학생 집합 생성
        ↓
여벌도 있는 도난 학생 제거
        ↓
1번 학생부터 순서대로 확인
        ↓
앞번호의 여벌 확인 → 없으면 뒷번호 확인
```

앞번호 학생부터 빌리는 이유는 작은 번호부터 처리할 때 아직 처리하지 않은 다음 학생이 사용할 가능성을 남기기 위해서다.

체육복을 빌려준 학생은 여벌 집합에서 즉시 제거하여 한 벌을 중복으로 빌려주지 않도록 한다.

## 정답

```text
전체 학생 수 - 끝까지 체육복이 없는 학생 수
```

## 복잡도

- 시간 복잡도: `O(n)`
- 공간 복잡도: `O(n)`

## 전체 코드

전체 코드는 [PG_42862.java](./PG_42862.java)에 정리했다.

---

# 큰 수 만들기

- 문제 번호: 42883
- 난이도: Level 2
- 풀이 날짜: 2026-08-27
- 핵심 알고리즘: 그리디, 스택
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42883

## 문제 접근

큰 수를 만들려면 가능한 한 큰 숫자가 앞자리에 와야 한다. 숫자를 왼쪽부터 확인하면서 현재 숫자보다 작은 앞자리 숫자를 제거한다.

```text
현재 숫자를 확인
        ↓
제거 횟수가 남아 있고
스택의 마지막 숫자가 현재 숫자보다 작다면 제거
        ↓
조건을 만족하는 동안 반복
        ↓
현재 숫자를 스택에 추가
```

작은 숫자를 하나만 제거하는 `if`가 아니라, 현재 숫자보다 작은 앞자리 숫자를 연속해서 제거할 수 있도록 `while`을 사용해야 한다.

## 뒤쪽 숫자 제거

`98765`처럼 숫자가 내림차순이면 탐색 중 제거 조건이 한 번도 성립하지 않는다. 제거 횟수가 남았다면 자릿값의 영향이 가장 작은 뒤쪽 숫자부터 제거한다.

```text
98765, k = 2
→ 뒤의 5와 6 제거
→ 987
```

숫자 문자 `'0'`부터 `'9'`까지는 문자 코드 순서도 숫자 순서와 같으므로 `char` 상태로 크기를 비교할 수 있다.

## 복잡도

- 시간 복잡도: `O(n)`
- 공간 복잡도: `O(n)`

## 전체 코드

전체 코드는 [PG_42883.java](./PG_42883.java)에 정리했다.
