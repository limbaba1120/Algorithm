# 2026-09-01 알고리즘 학습 정리

## H-Index

- 문제 번호: 42747
- 난이도: Level 2
- 풀이 날짜: 2026-09-01
- 핵심 알고리즘: 정렬, 선형 탐색
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42747

### 핵심 아이디어

인용 횟수를 오름차순으로 정렬한 뒤, 각 위치에서 다음 두 값을 비교한다.

```text
citations[i]  → 현재 논문의 인용 횟수
length - i    → 현재 논문부터 끝까지 남은 논문의 수
```

`citations[i] >= length - i`라면 `length - i`편의 논문이 각각 그 이상 인용되었다는 뜻이다. 앞에서부터 검사했을 때 이 조건을 처음 만족하는 `length - i`가 가장 큰 H-Index다.

### 예시

```text
정렬 전: [3, 0, 6, 1, 5]
정렬 후: [0, 1, 3, 5, 6]

i = 2
citations[2] = 3
length - i = 5 - 2 = 3

3회 이상 인용된 논문이 3편이므로 H-Index는 3
```

### 전체 코드

```java
import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int length = citations.length;

        for (int i = 0; i < length; i++) {
            int paperCount = length - i;

            if (citations[i] >= paperCount) {
                return paperCount;
            }
        }

        return 0;
    }
}
```

### 복잡도

- 시간 복잡도: `O(n log n)` — 배열 정렬
- 공간 복잡도: `O(log n)` — `Arrays.sort()`가 사용하는 정렬용 공간

---

## K번째수

- 문제 번호: 42748
- 난이도: Level 1
- 풀이 날짜: 2026-09-01
- 핵심 알고리즘: 배열 구간 복사, 정렬
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42748

### 핵심 아이디어

각 명령 `[first, last, position]`마다 다음 과정을 반복한다.

```text
first번째부터 last번째까지 자르기
                ↓
         자른 배열 정렬
                ↓
         position번째 값 선택
```

문제의 위치는 `1`부터 시작하지만 Java 배열의 인덱스는 `0`부터 시작한다. 따라서 시작 위치와 선택 위치에서 `1`을 빼야 한다.

### `Arrays.copyOfRange()` 이해하기

```java
int[] copied = Arrays.copyOfRange(array, first - 1, last);
```

`copyOfRange(배열, 시작 인덱스, 끝 인덱스)`는 시작 인덱스는 포함하고 끝 인덱스는 포함하지 않는다.

```text
array = [1, 5, 2, 6, 3, 7, 4]
명령   = [2, 5, 3]

문제의 위치:  1  2  3  4  5  6  7
Java 인덱스:  0  1  2  3  4  5  6
배열의 값:    1  5  2  6  3  7  4

copyOfRange(array, 1, 5)
복사되는 값: [5, 2, 6, 3]
```

- `first - 1`: 문제의 시작 위치를 Java 인덱스로 변경한다.
- `last`: 끝 인덱스는 제외되므로 그대로 사용해야 문제의 `last`번째 값까지 복사된다.

### 직접 복사한다면

```java
int[] copied = new int[last - first + 1];
int copiedIndex = 0;

for (int i = first - 1; i < last; i++) {
    copied[copiedIndex] = array[i];
    copiedIndex++;
}
```

원본 배열에서는 `first - 1`번 인덱스부터 읽고, 새 배열에는 `0`번 인덱스부터 차례대로 넣는다.

```text
array[first - 1] → copied[0]
array[first]     → copied[1]
array[first + 1] → copied[2]
```

이를 인덱스 계산식으로 줄이면 다음과 같다.

```java
for (int i = first - 1; i < last; i++) {
    copied[i - (first - 1)] = array[i];
}
```

### 전체 코드

```java
import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            answer[i] = findPosition(array, commands[i]);
        }

        return answer;
    }

    private int findPosition(int[] array, int[] command) {
        int first = command[0];
        int last = command[1];
        int position = command[2];

        int[] copied = Arrays.copyOfRange(array, first - 1, last);
        Arrays.sort(copied);

        return copied[position - 1];
    }
}
```

### 주의할 점

- 원본 배열 전체 크기로 임시 배열을 만들면 복사하지 않은 칸에 `0`이 들어가 정렬 결과에 영향을 줄 수 있다.
- `commands[i]` 하나만 메서드에 전달하면 `commands` 전체와 현재 위치를 함께 전달할 필요가 없다.
- `position`도 문제에서는 1부터 시작하므로 결과를 꺼낼 때 `position - 1`을 사용한다.

### 복잡도

명령 하나가 자르는 원소의 수를 `k`라고 하면 다음과 같다.

- 시간 복잡도: `O(k log k)` — 잘라낸 배열 정렬
- 공간 복잡도: `O(k)` — 잘라낸 배열 저장

---

## 배달

- 문제 번호: 12978
- 난이도: Level 2
- 풀이 날짜: 2026-09-03
- 핵심 자료구조: 인접 행렬, 거리 배열, `PriorityQueue`
- 핵심 알고리즘: 다익스트라
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/12978

### 핵심 아이디어

1번 마을에서 출발해 각 마을까지 걸리는 최소 시간을 구한 뒤, 그 시간이 `K` 이하인 마을의 수를 센다.

도로마다 이동 시간이 다르므로 간선의 개수만 세는 일반 BFS를 사용할 수 없다. 현재까지 발견한 마을 중 이동 시간이 가장 짧은 마을부터 확인하는 다익스트라 알고리즘을 사용한다.

```text
도로 정보를 양방향 그래프로 저장
              ↓
1번 마을의 거리를 0으로 설정
              ↓
가장 가까운 마을을 우선순위 큐에서 꺼냄
              ↓
주변 마을까지 더 짧은 경로가 있으면 갱신
              ↓
최단 거리가 K 이하인 마을의 수 계산
```

### 인접 행렬 초기화

```java
int[][] town = new int[N + 1][N + 1];

for (int[] row : town) {
    Arrays.fill(row, INF);
}

for (int i = 1; i <= N; i++) {
    town[i][i] = 0;
}
```

마을 번호가 `1`부터 시작하므로 배열을 `N + 1` 크기로 만들고 0번 인덱스는 사용하지 않는다.

```text
town[i][i] = 0   → 자기 자신까지 걸리는 시간은 0
town[i][j] = INF → i와 j 사이에 도로가 없음
```

Java의 `int` 배열 기본값인 `0`을 그대로 사용하면 이동 시간이 0인 것과 도로가 없는 것을 구분할 수 없으므로, 도로가 없는 상태를 `INF`로 표시한다.

```java
private static final int INF = Integer.MAX_VALUE;
```

`INF`는 Java가 제공하는 이름이 아니라 직접 선언한 상수다. 따라서 `import java.INF`는 사용할 수 없다.

### 양방향 도로 저장

```java
town[start][end] = Math.min(town[start][end], time);
town[end][start] = Math.min(town[end][start], time);
```

도로는 양방향이므로 `start → end`와 `end → start`를 모두 저장한다. 같은 두 마을을 연결하는 도로가 여러 개라면 `Math.min()`으로 이동 시간이 가장 짧은 도로만 저장한다.

### 우선순위 큐의 정렬 기준

큐에는 다음 형태의 배열을 넣는다.

```text
{마을 번호, 1번 마을부터 해당 마을까지의 거리}
```

```java
PriorityQueue<int[]> queue = new PriorityQueue<>(
        (a, b) -> Integer.compare(a[1], b[1])
);
```

- `a[0]`, `b[0]`: 마을 번호
- `a[1]`, `b[1]`: 해당 마을까지의 거리

두 번째 값인 거리를 오름차순으로 비교하기 때문에 `poll()`을 호출하면 현재 거리가 가장 짧은 마을이 먼저 나온다.

```text
{2, 5}, {3, 2}, {4, 7}
              ↓ poll()
            {3, 2}
```

### 오래된 거리 건너뛰기

```java
if (currentDistance > distances[currentTown]) {
    continue;
}
```

같은 마을이 서로 다른 거리로 큐에 여러 번 들어갈 수 있다.

```text
큐의 정보:              {2번 마을, 거리 10}
나중에 발견한 최단 거리: distances[2] = 3
```

거리 10은 이미 쓸모없는 정보이므로 주변 마을을 다시 확인하지 않고 건너뛴다.

### 거리 갱신

```java
for (int nextTown = 1; nextTown <= N; nextTown++) {
    int roadTime = town[currentTown][nextTown];

    if (roadTime == INF) {
        continue;
    }

    int newDistance = currentDistance + roadTime;

    if (newDistance < distances[nextTown]) {
        distances[nextTown] = newDistance;
        queue.offer(new int[]{nextTown, newDistance});
    }
}
```

현재 마을과 연결된 모든 마을을 확인한다. 현재 마을을 거쳐 가는 거리가 기존 최단 거리보다 짧으면 거리를 갱신하고, 갱신한 마을을 큐에 넣는다.

```text
새로운 거리 = 현재 마을까지의 거리 + 다음 도로의 시간

새로운 거리 < 기존 최단 거리
        ├─ 아니요 → 변경하지 않음
        └─ 예 → 최단 거리 갱신 후 큐에 추가
```

### 배달 가능한 마을 세기

```java
int deliverableTownCount = 0;

for (int townNumber = 1; townNumber <= N; townNumber++) {
    if (distances[townNumber] <= K) {
        deliverableTownCount++;
    }
}
```

다익스트라가 끝나면 `distances[i]`에는 1번 마을에서 `i`번 마을까지의 최소 시간이 저장된다. 이 값이 `K` 이하인 마을만 센다. 출발지인 1번 마을의 거리도 0이므로 정답에 포함된다.

### 전체 코드

```java
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private static final int INF = Integer.MAX_VALUE;

    public int solution(int N, int[][] road, int K) {
        int[][] town = new int[N + 1][N + 1];

        for (int[] row : town) {
            Arrays.fill(row, INF);
        }

        for (int i = 1; i <= N; i++) {
            town[i][i] = 0;
        }

        for (int[] currentRoad : road) {
            int start = currentRoad[0];
            int end = currentRoad[1];
            int time = currentRoad[2];

            town[start][end] = Math.min(town[start][end], time);
            town[end][start] = Math.min(town[end][start], time);
        }

        return dijkstra(N, town, K);
    }

    private int dijkstra(int N, int[][] town, int K) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, INF);
        distances[1] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[1], b[1])
        );
        queue.offer(new int[]{1, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentTown = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[currentTown]) {
                continue;
            }

            for (int nextTown = 1; nextTown <= N; nextTown++) {
                int roadTime = town[currentTown][nextTown];

                if (roadTime == INF) {
                    continue;
                }

                int newDistance = currentDistance + roadTime;

                if (newDistance < distances[nextTown]) {
                    distances[nextTown] = newDistance;
                    queue.offer(new int[]{nextTown, newDistance});
                }
            }
        }

        int deliverableTownCount = 0;

        for (int townNumber = 1; townNumber <= N; townNumber++) {
            if (distances[townNumber] <= K) {
                deliverableTownCount++;
            }
        }

        return deliverableTownCount;
    }
}
```

### 자주 발생하는 실수

- `import java.INF`를 작성하면 안 된다. `INF`는 직접 선언하는 상수다.
- 값 비교에는 대입 연산자 `=`가 아니라 비교 연산자 `==`를 사용한다.
- `distance`와 `distances`, `newDistance`와 `nextDistance`처럼 변수 이름을 혼동하지 않는다.
- 메서드 매개변수인 `town`과 같은 이름으로 반복문 변수를 다시 선언하지 않는다.
- 도로가 없는지 확인한 후 거리를 더해야 `Integer.MAX_VALUE`를 더하면서 발생하는 오버플로를 피할 수 있다.

### 복잡도

인접 행렬에서는 큐에서 마을을 꺼낼 때마다 `1`번부터 `N`번 마을까지 확인한다.

- 시간 복잡도: `O(N² log N)`
- 공간 복잡도: `O(N²)` — 인접 행렬 저장

---

## 합승 택시 요금

- 문제 번호: 72413
- 난이도: Level 3
- 풀이 날짜: 2026-09-03
- 핵심 자료구조: 인접 행렬, 거리 배열, `PriorityQueue`
- 핵심 알고리즘: 다익스트라
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/72413

### 핵심 아이디어

두 승객은 출발지 `s`에서 특정 지점까지 함께 이동한 뒤 각자의 목적지 `a`, `b`로 이동할 수 있다.

```text
              s
              │ 함께 이동
              k
             / \
       따로 이동  따로 이동
          /         \
         a           b
```

`k`를 합승이 끝나는 지점이라고 하면 총요금은 다음과 같다.

```text
s → k의 최단 요금
+ k → a의 최단 요금
+ k → b의 최단 요금
```

모든 지점 `k`를 합승 종료 후보로 확인하고 총요금의 최솟값을 구한다.

### 다익스트라를 세 번 실행하는 이유

```java
int[] fromS = dijkstra(road, n, s);
int[] fromA = dijkstra(road, n, a);
int[] fromB = dijkstra(road, n, b);
```

각 배열은 다음 정보를 저장한다.

```text
fromS[k] → s에서 k까지의 최단 요금
fromA[k] → a에서 k까지의 최단 요금
fromB[k] → b에서 k까지의 최단 요금
```

도로는 양방향이므로 `a → k`와 `k → a`의 요금이 같고, `b → k`와 `k → b`의 요금도 같다. 따라서 `a`와 `b`를 출발점으로 다익스트라를 실행한 결과를 반대 방향의 요금으로 사용할 수 있다.

다익스트라 함수에는 `a`, `b`, `fares` 전체를 전달하지 않는다. 이미 `fares`를 `road`로 변환했으며, 다익스트라는 전달받은 출발점 하나에서 모든 지점까지의 최단 거리만 계산하면 되기 때문이다.

```java
private int[] dijkstra(int[][] road, int n, int start)
```

같은 함수를 `start`만 바꾸어 세 번 재사용한다.

### 도로 정보 만들기

```java
int[][] road = new int[n + 1][n + 1];

for (int[] row : road) {
    Arrays.fill(row, INF);
}

for (int i = 1; i <= n; i++) {
    road[i][i] = 0;
}
```

도로가 없는 곳을 `INF`로 먼저 채운 뒤 자기 자신까지의 요금만 `0`으로 설정한다. 순서를 반대로 하면 대각선의 `0`도 `INF`로 다시 덮어쓰므로 주의한다.

```java
for (int[] fare : fares) {
    int start = fare[0];
    int end = fare[1];
    int distance = fare[2];

    road[start][end] = Math.min(road[start][end], distance);
    road[end][start] = Math.min(road[end][start], distance);
}
```

택시 도로는 양방향이므로 양쪽 방향을 모두 저장한다. 같은 두 지점 사이의 정보가 여러 번 주어지는 경우에도 가장 작은 요금만 남긴다.

### 합승 종료 지점별 총요금

```java
int answer = INF;

for (int sharedEnd = 1; sharedEnd <= n; sharedEnd++) {
    int totalFare = fromS[sharedEnd]
            + fromA[sharedEnd]
            + fromB[sharedEnd];

    answer = Math.min(answer, totalFare);
}
```

`answer`는 최소 요금을 찾아야 하므로 큰 값으로 초기화하고 `Math.min()`을 사용한다.

특별한 합승 방식도 모든 `sharedEnd`를 검사하는 과정에 포함된다.

```text
sharedEnd = s → 처음부터 각자 이동
sharedEnd = a → a까지 합승한 뒤 B만 이동
sharedEnd = b → b까지 합승한 뒤 A만 이동
```

### 다익스트라의 거리 갱신

```java
if (currentDistance > distances[currentPlace]) {
    continue;
}
```

현재 큐에서 꺼낸 거리가 이미 저장된 최단 거리보다 크면 오래된 정보이므로 건너뛴다. 거리 배열의 인덱스에는 거리 값이 아니라 지점 번호인 `currentPlace`를 사용한다.

```java
for (int nextPlace = 1; nextPlace <= n; nextPlace++) {
    int fare = road[currentPlace][nextPlace];

    if (fare == INF) {
        continue;
    }

    int nextDistance = currentDistance + fare;

    if (nextDistance < distances[nextPlace]) {
        distances[nextPlace] = nextDistance;
        queue.offer(new int[]{nextPlace, nextDistance});
    }
}
```

도로가 연결된 다음 지점만 확인하고 더 짧은 경로를 발견하면 거리 배열을 갱신한다.

`<=`가 아니라 `<`를 사용해야 같은 거리 정보를 큐에 반복해서 넣지 않는다. 특히 `road[i][i] = 0`이므로 `<=`를 사용하면 자기 자신에 대한 같은 거리 정보가 계속 추가될 수 있다.

거리 배열의 인덱스에는 항상 지점 번호를 사용한다.

```java
distances[nextPlace] = nextDistance; // 올바른 코드
```

```java
distances[nextDistance] = nextDistance; // 잘못된 코드
```

### 전체 코드

```java
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] road = new int[n + 1][n + 1];

        for (int[] row : road) {
            Arrays.fill(row, INF);
        }

        for (int i = 1; i <= n; i++) {
            road[i][i] = 0;
        }

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int distance = fare[2];

            road[start][end] = Math.min(road[start][end], distance);
            road[end][start] = Math.min(road[end][start], distance);
        }

        int[] fromS = dijkstra(road, n, s);
        int[] fromA = dijkstra(road, n, a);
        int[] fromB = dijkstra(road, n, b);

        int answer = INF;

        for (int sharedEnd = 1; sharedEnd <= n; sharedEnd++) {
            int totalFare = fromS[sharedEnd]
                    + fromA[sharedEnd]
                    + fromB[sharedEnd];

            answer = Math.min(answer, totalFare);
        }

        return answer;
    }

    private int[] dijkstra(int[][] road, int n, int start) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, INF);
        distances[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (x, y) -> Integer.compare(x[1], y[1])
        );
        queue.offer(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentPlace = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[currentPlace]) {
                continue;
            }

            for (int nextPlace = 1; nextPlace <= n; nextPlace++) {
                int fare = road[currentPlace][nextPlace];

                if (fare == INF) {
                    continue;
                }

                int nextDistance = currentDistance + fare;

                if (nextDistance < distances[nextPlace]) {
                    distances[nextPlace] = nextDistance;
                    queue.offer(new int[]{nextPlace, nextDistance});
                }
            }
        }

        return distances;
    }
}
```

### 복잡도

인접 행렬을 사용하므로 한 지점을 처리할 때 모든 지점을 확인한다. 이 다익스트라를 `s`, `a`, `b`에서 세 번 실행하지만 상수 배는 빅오 표기에서 제외한다.

- 시간 복잡도: `O(n² log n)`
- 공간 복잡도: `O(n²)` — 인접 행렬 저장

---

## 섬 연결하기

- 문제 번호: 42861
- 난이도: Level 3
- 풀이 날짜: 2026-09-04
- 핵심 자료구조: Union-Find, 부모 배열
- 핵심 알고리즘: 크루스칼, 최소 신장 트리
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42861

### 핵심 아이디어

모든 섬을 최소 비용으로 연결해야 하므로 최소 신장 트리를 만든다.

1. 다리를 비용 기준 오름차순으로 정렬한다.
2. 가장 저렴한 다리부터 확인한다.
3. 두 섬의 대표 노드가 다르면 다리를 선택하고 두 그룹을 합친다.
4. 대표 노드가 같으면 사이클이 생기므로 해당 다리는 건너뛴다.

### `find`와 `union`

`find`는 부모를 따라가며 그룹의 최종 대표 노드를 찾는다.

```java
private int find(int[] parent, int node) {
    if (parent[node] == node) {
        return node;
    }

    return parent[node] = find(parent, parent[node]);
}
```

예를 들어 부모 관계가 `2 → 1 → 0`이면 `find(parent, 2)`의 결과는 `0`이다. 이때 `parent[2]`도 `0`으로 바꿔 다음 검색을 빠르게 한다.

`union`은 두 노드의 대표를 찾은 뒤 한 그룹을 다른 그룹에 연결한다.

```java
private void union(int[] parent, int nodeA, int nodeB) {
    int rootA = find(parent, nodeA);
    int rootB = find(parent, nodeB);

    parent[rootB] = rootA;
}
```

### 전체 코드

```java
import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] cost : costs) {
            int nodeA = cost[0];
            int nodeB = cost[1];

            if (find(parent, nodeA) != find(parent, nodeB)) {
                union(parent, nodeA, nodeB);
                answer += cost[2];
            }
        }

        return answer;
    }

    private int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent, parent[node]);
    }

    private void union(int[] parent, int nodeA, int nodeB) {
        int rootA = find(parent, nodeA);
        int rootB = find(parent, nodeB);

        parent[rootB] = rootA;
    }
}
```

### 복잡도

- 시간 복잡도: `O(E log E)` — 다리 비용 정렬
- 공간 복잡도: `O(n)` — 부모 배열 저장
