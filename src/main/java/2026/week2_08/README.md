# 올바른 괄호

- 문제 번호: 12909
- 난이도: Level 2
- 풀이 날짜: 2026-08-10
- 핵심 자료구조: 스택(`ArrayDeque`)
- 핵심 알고리즘: 스택, 문자열 순회
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/12909

## 문제 설명

문자열 `s`는 `(`와 `)`로만 이루어져 있다. 괄호가 올바르게 짝을 이루면 `true`, 그렇지 않으면 `false`를 반환한다.

올바른 괄호가 되려면 다음 조건을 모두 만족해야 한다.

1. 닫는 괄호 `)` 앞에는 아직 짝을 이루지 않은 여는 괄호 `(`가 있어야 한다.
2. 문자열을 모두 확인한 뒤 짝을 이루지 못한 여는 괄호가 남아 있으면 안 된다.

```text
"()()"  → true
"(())"  → true
")()("  → false: 첫 문자인 ')'와 짝을 이룰 '('가 없다.
"(()"   → false: 검사가 끝난 뒤 '('가 하나 남는다.
```

## 핵심 아이디어

문자열을 왼쪽부터 한 글자씩 확인한다.

- `(`를 만나면 스택에 `push()`한다.
- `)`를 만나면 앞에서 나온 `(` 하나와 짝을 지어야 하므로 `pop()`한다.
- `)`를 만났는데 스택이 비어 있으면 짝이 될 `(`가 없으므로 즉시 `false`를 반환한다.
- 순회가 끝난 뒤 스택이 비어 있어야 모든 괄호가 짝을 이룬 것이다.

스택에는 문자열의 모든 문자를 먼저 넣는 것이 아니라, **아직 닫히지 않은 여는 괄호만 저장한다.** 모든 문자를 먼저 `push()`한 뒤 `pop()`하면 마지막 문자부터 역순으로 확인하게 되어 왼쪽부터 괄호의 짝을 검사할 수 없다.

## 동작 예시

문자열이 `"(())"`일 때의 스택 상태는 다음과 같다.

```text
문자 '(' → push → [(]
문자 '(' → push → [(, (]
문자 ')' → pop  → [(]
문자 ')' → pop  → []

스택이 비어 있으므로 true
```

문자열이 `"())"`라면 마지막 `)`를 확인할 때 스택이 이미 비어 있다.

```text
문자 '(' → push → [(]
문자 ')' → pop  → []
문자 ')' → 스택이 비어 있음 → false
```

## `Deque`를 스택으로 사용하기

Java에서는 `Stack` 클래스보다 `Deque` 인터페이스와 `ArrayDeque` 구현체를 사용해 스택을 만드는 방식을 권장한다.

```java
Deque<Character> stack = new ArrayDeque<>();
```

| 메서드 | 역할 |
|---|---|
| `push(value)` | 스택의 맨 위에 값을 넣는다. |
| `pop()` | 스택의 맨 위 값을 제거하고 반환한다. |
| `peek()` | 스택의 맨 위 값을 제거하지 않고 반환한다. |
| `isEmpty()` | 스택이 비어 있는지 확인한다. |

`push()`와 `pop()`은 마지막에 넣은 값을 먼저 꺼내는 후입선출(LIFO) 방식이다. 이 풀이에서는 가장 최근에 만난, 아직 닫히지 않은 `(`를 `)`와 짝짓는 데 사용한다.

변수 이름도 실제 사용 목적에 맞게 `queue`가 아니라 `stack`으로 작성한다.

## 전체 흐름

```text
문자열을 왼쪽부터 순회
        ↓
현재 문자가 '('인가?
 ├─ 예 → 스택에 push
 └─ 아니요 → 스택이 비어 있는가?
                ├─ 예 → false
                └─ 아니요 → pop
        ↓
순회가 끝난 뒤 스택이 비어 있는가?
 ├─ 예 → true
 └─ 아니요 → false
```

## 복잡도

- 시간 복잡도: `O(n)` — 문자열의 각 문자를 한 번씩 확인한다.
- 공간 복잡도: `O(n)` — 문자열이 모두 `(`라면 최대 `n`개의 문자가 스택에 저장된다.

## 전체 코드

```java
import java.util.ArrayDeque;
import java.util.Deque;

class PG_12909 {
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '(') {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
```

## 추가로 생각해 볼 점

이 문제는 괄호 종류가 `()` 하나뿐이므로 스택 대신 열린 괄호의 개수를 나타내는 정수 변수 하나로도 풀 수 있다. 하지만 여러 종류의 괄호 `()`, `{}`, `[]`가 섞인 문제에서는 가장 최근에 열린 괄호의 종류를 확인해야 하므로 스택 풀이가 더 일반적이다.

---

# 더 맵게

- 문제 번호: 42626
- 난이도: Level 2
- 풀이 날짜: 2026-08-10
- 핵심 자료구조: 최소 힙(`PriorityQueue`)
- 핵심 알고리즘: 그리디, 우선순위 큐
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42626

## 문제 설명

모든 음식의 스코빌 지수를 주어진 기준 `K` 이상으로 만들어야 한다. 기준보다 맵지 않은 음식이 있으면 현재 가장 맵지 않은 음식 두 개를 골라 다음 공식으로 새로운 음식을 만든다.

```text
새로운 음식의 스코빌 지수
= 가장 맵지 않은 음식
  + (두 번째로 맵지 않은 음식 × 2)
```

모든 음식의 스코빌 지수가 `K` 이상이 될 때까지 음식을 섞은 최소 횟수를 반환한다. 어떤 방법으로도 조건을 만족할 수 없으면 `-1`을 반환한다.

## 핵심 아이디어

매번 가장 작은 값 두 개를 선택해야 하므로 최소 힙을 사용한다.

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

Java의 `PriorityQueue<Integer>`는 기본적으로 가장 작은 값이 앞에 위치하는 최소 힙이다.

- `peek()`: 현재 최솟값을 확인한다.
- `poll()`: 현재 최솟값을 꺼내면서 제거한다.
- `offer(value)`: 새로운 값을 힙에 추가한다.

가장 작은 값인 `pq.peek()`이 `K` 이상이라면 힙에 있는 나머지 값도 모두 `K` 이상이므로 작업을 끝낼 수 있다.

## 원본 배열이 아닌 `peek()`을 확인하는 이유

음식을 섞어 만든 값은 원본 `scoville` 배열이 아니라 우선순위 큐에 추가된다. 따라서 첫 번째 혼합 이후부터 원본 배열은 현재 음식들의 상태를 나타내지 못한다.

```text
원본: [1, 2, 3, 9, 10, 12]

1과 2를 섞은 값: 1 + (2 × 2) = 5
현재 음식: [3, 5, 9, 10, 12]
원본 배열: [1, 2, 3, 9, 10, 12]  ← 변하지 않음
```

현재 음식 중 가장 작은 값은 다음과 같이 확인해야 한다.

```java
if (pq.peek() >= k) {
    break;
}
```

## 동작 예시

`scoville = [1, 2, 3, 9, 10, 12]`, `K = 7`인 경우다.

```text
초기 힙: [1, 2, 3, 9, 10, 12]

1회:
1과 2를 제거
1 + (2 × 2) = 5를 추가
현재 음식: [3, 5, 9, 10, 12]

2회:
3과 5를 제거
3 + (5 × 2) = 13을 추가
현재 음식: [9, 10, 12, 13]

최솟값 9가 K 이상이므로 종료
정답: 2
```

힙 내부 전체가 정렬된 배열 형태인 것은 아니지만, `peek()`이 최솟값이라는 점은 보장된다.

## 섞은 음식을 항상 다시 넣는 이유

섞은 음식도 현재 존재하는 음식 중 하나이므로 결과가 `K` 이상인지와 관계없이 다시 우선순위 큐에 넣어야 한다. 또한 음식을 실제로 한 번 섞을 때마다 횟수를 증가시킨다.

```java
pq.offer(combineFood);
answer++;
```

## 실패 조건

현재 최솟값이 `K` 미만인데 음식이 하나만 남았다면 두 음식을 선택할 수 없으므로 더 이상 섞을 수 없다.

```java
if (pq.size() < 2) {
    return -1;
}
```

이 검사는 반드시 `poll()`을 두 번 호출하기 전에 수행한다. 음식이 하나 남았더라도 그 값이 이미 `K` 이상이면 성공이므로, 먼저 `peek()`으로 성공 여부를 확인한 다음 크기를 확인한다.

## `for`문을 사용할 수 있는 이유

음식을 한 번 섞으면 큐의 원소 개수는 하나 줄어든다.

```text
두 음식 제거: -2
새 음식 추가: +1
전체 변화: -1
```

처음 음식이 `n`개라면 최대 `n - 1`번까지만 섞을 수 있다. 따라서 원본 배열의 길이를 상한으로 둔 현재 `for`문 안에서 반드시 다음 중 하나가 발생한다.

- 모든 음식이 `K` 이상이 되어 `break`
- 음식이 하나만 남아 `-1` 반환

반복 횟수가 조건에 따라 정해진다는 의미가 더 잘 드러나도록 `while (pq.peek() < k)`로 작성할 수도 있지만, 현재 `for`문 풀이도 올바르게 동작한다.

## 전체 흐름

```text
모든 음식을 최소 힙에 추가
          ↓
현재 최솟값이 K 이상인가?
 ├─ 예 → 반복 종료
 └─ 아니요 → 음식이 2개 미만인가?
                ├─ 예 → -1 반환
                └─ 아니요
                      ↓
              최솟값 두 개 poll
                      ↓
              새 스코빌 지수 계산
                      ↓
              새 음식을 offer
                      ↓
              혼합 횟수 증가
```

## 복잡도

음식의 개수를 `n`이라고 할 때:

- 힙 생성: `O(n log n)`
- 한 번의 혼합: `poll()` 두 번과 `offer()` 한 번이므로 `O(log n)`
- 전체 시간 복잡도: `O(n log n)`
- 공간 복잡도: `O(n)`

## 전체 코드

```java
import java.util.PriorityQueue;

class PG_42626 {
    public int solution(int[] scoville, int k) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }

        for (int i = 0; i < scoville.length; i++) {
            if (pq.peek() >= k) {
                break;
            }

            if (pq.size() < 2) {
                return -1;
            }

            int firstFood = pq.poll();
            int secondFood = pq.poll();
            int combineFood = firstFood + (secondFood * 2);

            pq.offer(combineFood);
            answer++;
        }

        return answer;
    }
}
```

---

# 프로세스

- 문제 번호: 42587
- 난이도: Level 2
- 풀이 날짜: 2026-08-10
- 핵심 자료구조: 큐(`ArrayDeque`), 최대 힙(`PriorityQueue`)
- 핵심 알고리즘: 큐 시뮬레이션, 우선순위 큐
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42587

## 문제 설명

실행 대기 큐에서 프로세스를 하나 꺼낸다. 대기 중인 프로세스 가운데 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 큐의 맨 뒤에 다시 넣는다. 더 높은 우선순위가 없다면 해당 프로세스를 실행한다.

이 과정을 반복할 때, 처음에 `location` 위치에 있던 프로세스가 몇 번째로 실행되는지 반환한다.

```text
priorities = [2, 1, 3, 2]
location = 2

실행 순서: 우선순위 3인 2번 프로세스가 첫 번째로 실행
결과: 1
```

## 핵심 아이디어

두 자료구조를 서로 다른 목적으로 사용한다.

```java
Queue<Integer> waitingQueue = new ArrayDeque<>();

PriorityQueue<Integer> priorityQueue =
        new PriorityQueue<>(Collections.reverseOrder());
```

- `waitingQueue`: 아직 실행되지 않은 프로세스들의 원래 인덱스를 대기 순서대로 관리한다.
- `priorityQueue`: 아직 실행되지 않은 프로세스 중 가장 높은 우선순위를 바로 확인한다.

대기 큐의 맨 앞 프로세스 우선순위와 최대 힙의 최댓값을 비교한다.

```java
int currentIndex = waitingQueue.poll();
int currentPriority = priorities[currentIndex];

if (currentPriority == priorityQueue.peek()) {
    // 현재 프로세스 실행
} else {
    // 대기 큐 뒤로 이동
}
```

## 대기 큐에 인덱스를 저장하는 이유

우선순위 값만 저장하면 같은 우선순위의 프로세스들을 구분할 수 없고, 현재 프로세스가 목표인 `location`의 프로세스인지도 확인할 수 없다.

```text
priorities = [2, 2, 2]
location = 1
```

세 프로세스의 우선순위는 모두 같지만 목표 프로세스는 인덱스 `1`의 프로세스다. 따라서 대기 큐에는 우선순위가 아니라 인덱스를 저장한다.

```java
waitingQueue.offer(i);
```

인덱스를 이용하면 원본 배열에서 해당 프로세스의 우선순위를 가져올 수 있다.

```java
int currentPriority = priorities[currentIndex];
```

## 최대 힙을 사용하는 이유

Java의 `PriorityQueue`는 기본적으로 최소 힙이므로 가장 작은 값이 먼저 나온다. 이 문제에서는 가장 높은 우선순위를 확인해야 하므로 역순 비교 기준을 전달해 최대 힙으로 만든다.

```java
PriorityQueue<Integer> priorityQueue =
        new PriorityQueue<>(Collections.reverseOrder());
```

이제 다음 메서드들은 가장 높은 우선순위를 기준으로 동작한다.

| 메서드 | 역할 |
|---|---|
| `peek()` | 현재 가장 높은 우선순위를 확인한다. |
| `poll()` | 현재 가장 높은 우선순위를 제거한다. |
| `offer(value)` | 우선순위를 힙에 추가한다. |

## 두 큐의 처리 방법

현재 프로세스의 우선순위가 최대 힙의 최댓값과 같다면 실행할 차례다.

```java
if (currentPriority == priorityQueue.peek()) {
    priorityQueue.poll();
    answer++;
}
```

프로세스가 실제로 실행되었으므로 최대 힙에서도 해당 우선순위 하나를 제거하고 실행 순서를 증가시킨다.

현재 프로세스보다 높은 우선순위가 남아 있다면 실행하지 않고 대기 큐의 뒤로 보낸다.

```java
else {
    waitingQueue.offer(currentIndex);
}
```

이때 프로세스가 실행된 것이 아니므로 최대 힙에서는 아무 값도 제거하지 않는다.

## 동작 예시

`priorities = [1, 1, 9, 1, 1, 1]`, `location = 0`인 경우다.

```text
초기 대기 큐: [0, 1, 2, 3, 4, 5]
현재 최고 우선순위: 9

0번(우선순위 1) → 뒤로 이동
대기 큐: [1, 2, 3, 4, 5, 0]

1번(우선순위 1) → 뒤로 이동
대기 큐: [2, 3, 4, 5, 0, 1]

2번(우선순위 9) → 실행, 실행 순서 1
대기 큐: [3, 4, 5, 0, 1]

이제 최고 우선순위는 1이므로 앞에서부터 차례로 실행
3번 → 2번째
4번 → 3번째
5번 → 4번째
0번 → 5번째

location이 0이므로 결과는 5
```

## 같은 우선순위가 여러 개일 때

현재 우선순위와 최대 힙의 최댓값이 같으면 현재 프로세스를 실행한다.

```java
currentPriority == priorityQueue.peek()
```

같은 우선순위끼리는 대기 큐에 들어 있는 순서대로 실행되므로 별도의 정렬 기준이 필요하지 않다. 최대 힙은 어떤 인덱스가 실행되는지가 아니라 현재 실행 가능한 가장 높은 우선순위 값만 알려주는 역할을 한다.

## 전체 흐름

```text
모든 인덱스를 대기 큐에 저장
모든 우선순위를 최대 힙에 저장
              ↓
대기 큐의 맨 앞 인덱스를 poll
              ↓
현재 우선순위 == 최대 힙의 최댓값?
 ├─ 아니요 → 인덱스를 대기 큐 뒤에 다시 offer
 └─ 예 → 최대 힙에서 최댓값 제거
           실행 횟수 증가
              ↓
        현재 인덱스 == location?
         ├─ 예 → 실행 횟수 반환
         └─ 아니요 → 다음 프로세스 확인
```

## 복잡도

프로세스 개수를 `n`이라고 할 때:

- 최대 힙 구성: `O(n log n)`
- 프로세스 실행 시 최대 힙 제거: 총 `n`번, `O(n log n)`
- 대기 큐의 추가와 제거: 한 번에 `O(1)`
- 전체 시간 복잡도: 최악의 경우 `O(n² + n log n)`, 간단히 `O(n²)`
- 공간 복잡도: `O(n)`

프로세스가 실행되기 전에 대기 큐 뒤로 여러 번 이동할 수 있으므로 큐 순회 횟수는 최악의 경우 `O(n²)`이 될 수 있다.

## 전체 코드

```java
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class PG_42587 {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> waitingQueue = new ArrayDeque<>();
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            waitingQueue.offer(i);
            priorityQueue.offer(priorities[i]);
        }

        while (!waitingQueue.isEmpty()) {
            int currentIndex = waitingQueue.poll();
            int currentPriority = priorities[currentIndex];

            if (currentPriority == priorityQueue.peek()) {
                priorityQueue.poll();
                answer++;

                if (currentIndex == location) {
                    return answer;
                }
            } else {
                waitingQueue.offer(currentIndex);
            }
        }

        return -1;
    }
}
```

---

# 게임 맵 최단거리

- 문제 번호: 1844
- 난이도: Level 2
- 풀이 날짜: 2026-08-13
- 핵심 자료구조: 큐(`ArrayDeque`), 2차원 배열
- 핵심 알고리즘: 너비 우선 탐색(BFS)
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/1844

## 문제 설명

`maps`는 `0`과 `1`로 구성된 `n × m` 크기의 게임 맵이다.

- `0`: 벽이어서 이동할 수 없는 칸
- `1`: 이동할 수 있는 칸

캐릭터는 왼쪽 위 `(0, 0)`에서 출발하여 오른쪽 아래 `(n - 1, m - 1)`까지 이동해야 한다. 한 번에 상하좌우로 한 칸씩 이동할 수 있으며, 목적지까지 지나가는 칸의 최소 개수를 반환한다. 목적지에 도달할 수 없다면 `-1`을 반환한다.

## BFS를 사용하는 이유

각 칸으로 이동하는 비용은 모두 한 칸으로 동일하다. BFS는 시작점에서 가까운 칸부터 거리 순서대로 탐색하므로, 어떤 칸을 처음 방문했을 때 기록한 거리가 그 칸까지의 최단거리다.

```text
시작점
  ↓
거리 2인 칸들
  ↓
거리 3인 칸들
  ↓
거리 4인 칸들
```

먼저 발견한 좌표부터 처리하기 위해 선입선출(FIFO) 큐를 사용한다.

```java
Queue<int[]> queue = new ArrayDeque<>();
```

좌표는 `int[]`에 행과 열을 함께 담아 저장한다.

```java
queue.offer(new int[]{x, y});

int[] current = queue.poll();
int currentX = current[0];
int currentY = current[1];
```

## 방문 배열에 거리를 함께 저장하기

`isVisited`는 방문 여부와 시작점부터의 거리를 동시에 저장한다.

```text
0     → 아직 방문하지 않은 칸
1 이상 → 방문한 칸이며, 값은 시작점부터 지나온 칸의 개수
```

시작점도 지나가는 칸의 개수에 포함되므로 BFS를 실행하기 전에 `1`로 설정한다.

```java
isVisited[0][0] = 1;
bfs(maps, isVisited, 0, 0);
```

새로운 칸은 현재 칸보다 한 칸 더 이동한 위치이므로 현재 거리에서 `1`을 더한다.

```java
isVisited[nX][nY] = isVisited[currentX][currentY] + 1;
```

예를 들어 다음 경로를 이동하면 거리 배열에는 다음 값이 저장된다.

```text
(0, 0) → (1, 0) → (2, 0) → (2, 1)
   1        2        3        4
```

시작점을 BFS 실행 뒤에 `1`로 설정하면 탐색 중에는 시작점이 미방문 상태로 남는다. 그러면 시작점이 큐에 다시 들어갈 수 있고 거리도 한 칸 작게 계산되므로 반드시 탐색 전에 방문 처리해야 한다.

## 상하좌우 이동

두 배열에서 같은 인덱스를 조합하여 네 방향의 다음 좌표를 계산한다.

```java
static int[] dx = {1, 0, -1, 0};
static int[] dy = {0, 1, 0, -1};
```

```text
k = 0 → 아래: (+1,  0)
k = 1 → 오른쪽: (0, +1)
k = 2 → 위: (-1,  0)
k = 3 → 왼쪽: (0, -1)
```

```java
int nX = currentX + dx[k];
int nY = currentY + dy[k];
```

## 경계 검사

다음 좌표가 맵을 벗어나면 탐색하지 않는다.

```java
if (nX < 0 || nY < 0
        || maps.length - 1 < nX
        || maps[0].length - 1 < nY) {
    continue;
}
```

- `maps.length`: 행의 개수
- `maps[0].length`: 열의 개수

행과 열의 개수가 다를 수 있으므로 `nY`의 범위는 반드시 `maps[0].length`로 검사해야 한다. 같은 조건을 다음처럼 작성할 수도 있다.

```java
if (nX < 0 || nY < 0
        || nX >= maps.length
        || nY >= maps[0].length) {
    continue;
}
```

## 다음 칸을 방문하는 조건

다음 좌표가 아직 방문하지 않은 길일 때만 큐에 추가한다.

```java
if (isVisited[nX][nY] == 0 && maps[nX][nY] == 1) {
    queue.offer(new int[]{nX, nY});
    isVisited[nX][nY] = isVisited[currentX][currentY] + 1;
}
```

- `isVisited[nX][nY] == 0`: 아직 방문하지 않은 칸
- `maps[nX][nY] == 1`: 벽이 아닌 이동 가능한 칸

방문한 칸을 다시 큐에 넣지 않으므로 중복 탐색과 무한 반복을 방지할 수 있다.

## 목적지 도달 여부

BFS가 끝난 뒤 목적지에 저장된 값을 확인한다.

```java
int answer = isVisited[maps.length - 1][maps[0].length - 1];
```

목적지 값이 `0`이면 방문하지 못한 것이므로 `-1`, 그렇지 않으면 기록된 최단거리를 반환한다.

```java
return answer == 0 ? -1 : answer;
```

## 전체 흐름

```text
시작점 거리를 1로 설정하고 큐에 추가
                    ↓
              큐의 좌표 poll
                    ↓
             상하좌우 좌표 계산
                    ↓
        맵 안에 있고 방문하지 않은 길인가?
          ├─ 아니요 → 다음 방향 확인
          └─ 예 → 현재 거리 + 1 기록
                   큐에 다음 좌표 추가
                    ↓
                큐가 빌 때까지 반복
                    ↓
            목적지 거리 확인
          ├─ 0 → 도달 불가능, -1
          └─ 1 이상 → 최단거리 반환
```

## 복잡도

행의 개수를 `n`, 열의 개수를 `m`이라고 할 때 각 칸은 최대 한 번 방문되고, 방문할 때 네 방향만 확인한다.

- 시간 복잡도: `O(n × m)`
- 공간 복잡도: `O(n × m)`

## 전체 코드

```java
import java.util.ArrayDeque;
import java.util.Queue;

class PG_1844 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        int[][] isVisited = new int[maps.length][maps[0].length];
        int answer = 0;

        isVisited[0][0] = 1;

        bfs(maps, isVisited, 0, 0);

        answer = isVisited[maps.length - 1][maps[0].length - 1];

        return answer == 0 ? -1 : answer;
    }

    static void bfs(int[][] maps, int[][] isVisited, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            for (int k = 0; k < 4; k++) {
                int nX = currentX + dx[k];
                int nY = currentY + dy[k];

                if (nX < 0 || nY < 0
                        || maps.length - 1 < nX
                        || maps[0].length - 1 < nY) {
                    continue;
                }

                if (isVisited[nX][nY] == 0 && maps[nX][nY] == 1) {
                    queue.offer(new int[]{nX, nY});
                    isVisited[nX][nY] = isVisited[currentX][currentY] + 1;
                }
            }
        }
    }
}
```

---

# 네트워크

- 문제 번호: 43162
- 난이도: Level 3
- 풀이 날짜: 2026-08-13
- 핵심 자료구조: 큐(`ArrayDeque`), 방문 배열
- 핵심 알고리즘: 너비 우선 탐색(BFS), 연결 요소
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/43162

## 문제 설명

컴퓨터의 개수 `n`과 컴퓨터 사이의 연결 정보를 담은 2차원 배열 `computers`가 주어진다. 직접 또는 간접적으로 연결된 컴퓨터들은 하나의 네트워크를 구성한다. 전체 컴퓨터가 몇 개의 네트워크로 나뉘는지 반환한다.

```text
0번 ─ 1번    2번

네트워크 1: {0, 1}
네트워크 2: {2}
결과: 2
```

0번과 2번이 직접 연결되지 않았더라도 중간 컴퓨터를 통해 이어져 있다면 같은 네트워크다.

```text
0번 ─ 1번 ─ 2번

0번과 2번도 같은 네트워크
```

## 연결 정보를 읽는 방법

`computers`는 컴퓨터 사이의 연결 관계를 나타내는 인접 행렬이다.

```java
computers[current][next] == 1
```

위 조건은 `current` 컴퓨터와 `next` 컴퓨터가 연결되어 있다는 의미다. 게임 맵처럼 좌표를 상하좌우로 이동하는 문제가 아니므로 `dx`, `dy` 방향 배열은 필요하지 않다.

## 핵심 아이디어

아직 방문하지 않은 컴퓨터를 발견하면 그 컴퓨터부터 BFS를 실행한다. BFS 한 번으로 해당 컴퓨터와 연결된 컴퓨터를 모두 방문할 수 있으므로, 새로운 BFS를 시작한 횟수가 네트워크의 개수다.

```java
for (int i = 0; i < n; i++) {
    if (!isVisited[i]) {
        bfs(i, computers, isVisited);
        answer++;
    }
}
```

컴퓨터마다 `answer`를 증가시키는 것이 아니라, 방문하지 않은 컴퓨터에서 새로운 탐색을 시작할 때만 증가시킨다.

## BFS 탐색 과정

큐에는 좌표 배열이 아니라 탐색할 컴퓨터 번호를 저장한다.

```java
Queue<Integer> queue = new ArrayDeque<>();
```

시작 컴퓨터를 방문 처리하고 큐에 넣는다.

```java
isVisited[start] = true;
queue.offer(start);
```

현재 컴퓨터와 연결된 모든 컴퓨터를 확인하여 아직 방문하지 않은 컴퓨터를 큐에 추가한다.

```java
for (int next = 0; next < computers.length; next++) {
    if (computers[current][next] == 1 && !isVisited[next]) {
        isVisited[next] = true;
        queue.offer(next);
    }
}
```

큐에 넣는 시점에 방문 처리하면 같은 컴퓨터가 여러 번 큐에 들어가는 것을 막을 수 있다.

## 동작 예시

다음과 같이 컴퓨터가 연결되어 있다고 가정한다.

```text
0 ─ 1    2 ─ 3    4
```

```text
i = 0
0번은 미방문
→ BFS로 0번과 1번 방문
→ answer = 1

i = 1
이미 방문했으므로 넘어감

i = 2
2번은 미방문
→ BFS로 2번과 3번 방문
→ answer = 2

i = 3
이미 방문했으므로 넘어감

i = 4
4번은 미방문
→ BFS로 4번 방문
→ answer = 3
```

따라서 네트워크의 개수는 `3`이다. 다른 컴퓨터와 연결되지 않은 컴퓨터도 혼자 하나의 네트워크를 구성한다.

## `answer`를 지역 변수로 두는 이유

네트워크 개수는 `solution()`을 실행할 때마다 `0`부터 계산해야 하므로 지역 변수로 선언한다.

```java
int answer = 0;
```

`static` 필드로 만들면 같은 `Solution` 객체에서 메서드를 다시 호출할 때 이전 결과가 남을 수 있다. 이 문제에서 `bfs()`는 네트워크 개수를 직접 변경할 필요가 없으므로 `answer`를 전달할 필요도 없다.

## 전체 흐름

```text
0번부터 모든 컴퓨터 확인
             ↓
현재 컴퓨터를 방문했는가?
 ├─ 예 → 다음 컴퓨터 확인
 └─ 아니요 → 새로운 네트워크 발견
              answer 증가
              BFS 시작
                 ↓
          연결된 미방문 컴퓨터를
          모두 방문 처리
                 ↓
          다음 컴퓨터 확인
```

## 복잡도

각 컴퓨터를 BFS로 처리할 때 인접 행렬의 한 행 전체를 확인한다.

- 시간 복잡도: `O(n²)`
- 공간 복잡도: `O(n)`

방문 배열과 BFS 큐에는 최대 `n`개의 컴퓨터 정보가 저장된다.

## 전체 코드

```java
import java.util.ArrayDeque;
import java.util.Queue;

class PG_43162 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                bfs(i, computers, isVisited);
                answer++;
            }
        }

        return answer;
    }

    static void bfs(int start, int[][] computers, boolean[] isVisited) {
        Queue<Integer> queue = new ArrayDeque<>();

        isVisited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 0; next < computers.length; next++) {
                if (computers[current][next] == 1 && !isVisited[next]) {
                    isVisited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}
```
