# 기능개발

- 문제 번호: 42586
- 난이도: Level 2
- 풀이 날짜: 2026-08-19
- 핵심 자료구조: `ArrayList`
- 핵심 알고리즘: 순차 탐색, 그룹화
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42586

## 문제 설명

각 기능의 현재 개발 진도와 하루 개발 속도가 주어진다. 각 기능은 진도가 `100` 이상이 되면 완료되지만, 뒤에 있는 기능은 앞 기능보다 먼저 완료되어도 먼저 배포할 수 없다.

배포는 하루에 한 번 이루어지며, 앞 기능이 배포되는 날 이미 완료된 뒤 기능들은 함께 배포된다. 배포할 때마다 몇 개의 기능이 배포되는지를 배열로 반환한다.

```text
progresses = [93, 30, 55]
speeds     = [1, 30, 5]

완료까지 걸리는 날짜 = [7, 3, 9]

7일째: 첫 번째와 두 번째 기능 배포 → 2개
9일째: 세 번째 기능 배포           → 1개

결과: [2, 1]
```

두 번째 기능은 3일 만에 완료되지만 첫 번째 기능이 완료될 때까지 기다려야 한다.

## 핵심 아이디어

각 기능이 완료되는 데 필요한 날짜를 계산한 뒤, 앞 기능의 배포일을 기준으로 뒤 기능을 같은 배포 묶음에 포함할지 결정한다.

```text
다음 기능 완료일 <= 현재 배포일
→ 현재 배포 묶음에 포함

다음 기능 완료일 > 현재 배포일
→ 현재 묶음을 결과에 저장
→ 다음 기능부터 새로운 배포 묶음 시작
```

첫 번째 기능은 항상 첫 배포 묶음에 포함되므로 다음과 같이 초기화한다.

```java
int releaseDay = calculateDays(progresses[0], speeds[0]);
int count = 1;
```

## 완료까지 걸리는 날짜 계산

남은 작업량은 다음과 같다.

```java
100 - progress
```

완료 날짜는 남은 작업량을 하루 작업 속도로 나눈 값을 올림해야 한다.

```java
private int calculateDays(int progress, int speed) {
    return (100 - progress + speed - 1) / speed;
}
```

양의 정수 `value`를 `divisor`로 나눈 결과를 올림하는 공식은 다음과 같다.

```text
(value + divisor - 1) / divisor
```

예를 들어 진도가 `30`, 속도가 `30`이면 남은 작업량은 `70`이다.

```text
(70 + 30 - 1) / 30
= 99 / 30
= 3일
```

진도가 `95`, 속도가 `1`이면 다음과 같다.

```text
(5 + 1 - 1) / 1
= 5일
```

## 배포 묶음 만들기

두 번째 기능부터 완료 날짜를 계산한다.

```java
for (int i = 1; i < progresses.length; i++) {
    int countDay = calculateDays(progresses[i], speeds[i]);
}
```

다음 기능이 현재 배포 기준일까지 완료된다면 함께 배포한다.

```java
if (releaseDay >= countDay) {
    count++;
}
```

다음 기능이 더 늦게 완료된다면 기존 배포 묶음을 저장하고 새로운 묶음을 시작한다.

```java
else {
    result.add(count);
    releaseDay = countDay;
    count = 1;
}
```

새로운 묶음의 첫 기능이므로 `count`를 `0`이 아닌 `1`로 초기화한다.

## 마지막 `result.add(count)`가 필요한 이유

반복문 안에서는 더 늦게 완료되는 기능을 만났을 때 **이전 배포 묶음**만 저장한다. 마지막 배포 묶음 뒤에는 새로운 기능이 없으므로 반복문 안에서 저장될 기회가 없다.

```java
result.add(count);
```

예를 들어 완료 날짜가 `[7, 3, 9]`라면 다음과 같이 진행된다.

```text
초기 상태: releaseDay = 7, count = 1

3 <= 7
→ count = 2

9 > 7
→ result에 2 저장
→ releaseDay = 9
→ count = 1

반복문 종료
현재 result = [2], count = 1

마지막 count 저장
최종 result = [2, 1]
```

모든 기능이 한 번에 배포되는 경우에도 반복문 안에서 결과가 한 번도 저장되지 않으므로 마지막 추가가 반드시 필요하다.

## `List<Integer>`를 `int[]`로 변환하기

배포 묶음의 개수는 미리 알 수 없으므로 크기가 가변적인 `ArrayList`에 먼저 저장한다.

```java
List<Integer> result = new ArrayList<>();
```

프로그래머스에서 요구하는 반환형은 `int[]`이므로 마지막에 배열을 생성하여 값을 옮긴다.

```java
int[] answer = new int[result.size()];

for (int i = 0; i < result.size(); i++) {
    answer[i] = result.get(i);
}
```

## 전체 흐름

```text
첫 기능의 완료일을 배포 기준일로 설정
첫 배포 묶음의 개수를 1로 설정
                 ↓
          다음 기능 완료일 계산
                 ↓
완료일이 현재 배포일보다 빠르거나 같은가?
 ├─ 예 → 같은 묶음에 포함, count 증가
 └─ 아니요 → 현재 count를 결과에 저장
              새 완료일을 배포 기준일로 설정
              count를 1로 초기화
                 ↓
          모든 기능을 확인할 때까지 반복
                 ↓
          마지막 배포 묶음을 결과에 저장
                 ↓
          List를 int[]로 변환하여 반환
```

## 복잡도

기능의 개수를 `n`이라고 할 때 각 기능을 한 번씩 확인한다.

- 시간 복잡도: `O(n)`
- 공간 복잡도: `O(n)`

## 전체 코드

```java
import java.util.ArrayList;
import java.util.List;

class PG_42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();

        int releaseDay = calculateDays(progresses[0], speeds[0]);
        int count = 1;

        for (int i = 1; i < progresses.length; i++) {
            int countDay = calculateDays(progresses[i], speeds[i]);

            if (releaseDay >= countDay) {
                count++;
            } else {
                result.add(count);
                releaseDay = countDay;
                count = 1;
            }
        }

        result.add(count);

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private int calculateDays(int progress, int speed) {
        return (100 - progress + speed - 1) / speed;
    }
}
```

---

# 디스크 컨트롤러

- 문제 번호: 42627
- 난이도: Level 3
- 풀이 날짜: 2026-08-19
- 핵심 자료구조: 최소 힙(`PriorityQueue`)
- 핵심 알고리즘: 정렬, 우선순위 큐, 작업 스케줄링
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42627

## 문제 설명

각 작업은 다음 두 값으로 구성된다.

```text
[요청 시각, 소요 시간]
```

하드디스크는 한 번에 하나의 작업만 실행할 수 있다. 현재까지 요청된 작업 중 소요 시간이 가장 짧은 작업을 먼저 실행하여, 각 작업이 요청된 시점부터 완료될 때까지 걸린 시간의 평균을 구한다.

```text
반환 시간 = 완료 시각 - 요청 시각
```

## 핵심 아이디어

작업을 다음 두 기준으로 관리한다.

```text
jobs 배열
→ 요청 시각이 빠른 순서
→ 현재 시각까지 들어온 작업을 찾는 용도

PriorityQueue
→ 소요 시간이 짧은 순서
→ 대기 중인 작업에서 다음 작업을 선택하는 용도
```

즉, 요청 시각순으로 작업을 받아서 현재 대기 중인 작업은 소요 시간순으로 처리한다.

## 요청 시각순 정렬

`jobs[i][0]`은 작업의 요청 시각이다.

```java
Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
```

요청 시각순으로 정렬하면 `index`를 한 방향으로 이동하면서 현재 시각까지 요청된 작업을 찾을 수 있다.

```text
정렬 전: [[2, 6], [0, 3], [1, 9]]
정렬 후: [[0, 3], [1, 9], [2, 6]]
```

## 소요 시간 기준 최소 힙

`jobs[i][1]`은 작업의 소요 시간이다.

```java
PriorityQueue<int[]> pq =
        new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
```

현재까지 요청된 작업을 이 큐에 넣으면 `poll()`할 때 소요 시간이 가장 짧은 작업이 나온다.

```text
pq에 들어 있는 작업: [1, 9], [2, 6], [3, 2]
poll() 결과: [3, 2]
```

## 람다식 비교 기준

다음 람다식은 두 작업 `a`, `b`의 요청 시각을 비교한다.

```java
(a, b) -> Integer.compare(a[0], b[0])
```

다음 람다식은 두 작업의 소요 시간을 비교한다.

```java
(a, b) -> Integer.compare(a[1], b[1])
```

`Integer.compare(first, second)`는 첫 번째 값이 작으면 음수, 같으면 `0`, 크면 양수를 반환한다. 따라서 작은 값이 앞에 오는 오름차순 정렬이 된다.

## 상태 변수

```java
int currentTime = 0;
int totalTime = 0;
int completed = 0;
int index = 0;
```

| 변수 | 역할 |
|---|---|
| `currentTime` | 현재 시각 또는 방금 실행한 작업의 완료 시각 |
| `totalTime` | 모든 작업의 반환 시간 합 |
| `completed` | 실행을 완료한 작업의 개수 |
| `index` | 아직 우선순위 큐에 넣지 않은 다음 작업의 위치 |

## 반복문의 전체 구조

```text
모든 작업이 완료되었는가?
          │
          ├─ 아니요
          ▼
현재 시각까지 요청된 작업을 pq에 추가
          │
          ▼
       pq가 비었는가?
       ┌──┴──┐
     아니요   예
       │      │
       ▼      ▼
 가장 짧은    다음 작업의
 작업 실행    요청 시각으로 이동
       │
       ▼
 반환 시간 누적
 완료 개수 증가
       │
       └──── 반복
```

## 현재 시각까지 요청된 작업 추가

```java
while (index < jobs.length && jobs[index][0] <= currentTime) {
    pq.offer(jobs[index]);
    index++;
}
```

조건은 다음 두 가지를 확인한다.

- `index < jobs.length`: 아직 큐에 넣지 않은 작업이 남아 있다.
- `jobs[index][0] <= currentTime`: 그 작업이 현재 시각까지 요청되었다.

현재 시각이 `3`이고 요청 시각이 `0, 1, 2, 5`라면 `0, 1, 2`에 요청된 작업만 큐에 넣는다. 요청 시각이 `5`인 작업은 아직 실행 후보가 아니다.

## 대기 중인 작업 실행

```java
if (!pq.isEmpty()) {
    int[] currentJob = pq.poll();

    currentTime += currentJob[1];
    totalTime += currentTime - currentJob[0];
    completed++;
}
```

`pq`가 비어 있지 않다면 현재 실행할 수 있는 작업이 있다는 의미다.

```java
int[] currentJob = pq.poll();
```

대기 중인 작업 가운데 소요 시간이 가장 짧은 작업을 꺼낸다.

```java
currentTime += currentJob[1];
```

작업을 실행한 만큼 시간이 흐르므로 현재 시각에 소요 시간을 더한다. 이 계산 후의 `currentTime`은 작업의 완료 시각이다.

## 반환 시간 계산

```java
totalTime += currentTime - currentJob[0];
```

`currentTime`은 완료 시각이고 `currentJob[0]`은 요청 시각이므로 두 값을 빼면 해당 작업의 반환 시간이 된다.

```text
요청 시각: 2
실행 시작: 3
소요 시간: 6
완료 시각: 9

대기 시간: 3 - 2 = 1
실행 시간: 6
반환 시간: 1 + 6 = 7

완료 시각 - 요청 시각
= 9 - 2
= 7
```

## 대기 중인 작업이 없는 경우

```java
else {
    currentTime = jobs[index][0];
}
```

`pq`가 비었다면 현재 시각에 실행할 수 있는 작업이 없다는 의미다.

```text
현재 시각: 3
다음 작업 요청 시각: 10
```

`3`부터 `10`까지 하드디스크가 할 일이 없으므로 시간을 한 단위씩 증가시키지 않고 다음 요청 시각인 `10`으로 바로 이동한다.

## 동작 예시

```text
jobs = [[0, 3], [1, 9], [2, 6]]
```

첫 번째 작업:

```text
현재 시각 0
pq에 [0, 3] 추가
[0, 3] 실행

완료 시각: 0 + 3 = 3
반환 시간: 3 - 0 = 3
```

두 번째 작업:

```text
현재 시각 3
[1, 9], [2, 6] 모두 pq에 추가
소요 시간이 짧은 [2, 6] 실행

완료 시각: 3 + 6 = 9
반환 시간: 9 - 2 = 7
```

세 번째 작업:

```text
현재 시각 9
[1, 9] 실행

완료 시각: 9 + 9 = 18
반환 시간: 18 - 1 = 17
```

평균 반환 시간:

```text
(3 + 7 + 17) / 3
= 27 / 3
= 9
```

## 평균 반환

모든 작업의 반환 시간을 작업 개수로 나눈다.

```java
return totalTime / jobs.length;
```

Java의 정수 나눗셈은 소수점 이하를 버리므로 문제에서 요구하는 평균의 정수 부분을 얻을 수 있다.

## 복잡도

작업의 개수를 `n`이라고 할 때:

- 요청 시각순 정렬: `O(n log n)`
- 각 작업의 힙 추가와 제거: `O(log n)`, 총 `O(n log n)`
- 전체 시간 복잡도: `O(n log n)`
- 공간 복잡도: `O(n)`

## 전체 코드

```java
import java.util.Arrays;
import java.util.PriorityQueue;

class PG_42627 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        int currentTime = 0;
        int totalTime = 0;
        int completed = 0;
        int index = 0;

        while (completed < jobs.length) {
            while (index < jobs.length && jobs[index][0] <= currentTime) {
                pq.offer(jobs[index]);
                index++;
            }

            if (!pq.isEmpty()) {
                int[] currentJob = pq.poll();

                currentTime += currentJob[1];
                totalTime += currentTime - currentJob[0];
                completed++;
            } else {
                currentTime = jobs[index][0];
            }
        }

        return totalTime / jobs.length;
    }
}
```

---

# 주식가격

- 문제 번호: 42584
- 난이도: Level 2
- 풀이 날짜: 2026-08-20
- 핵심 자료구조: 스택(`ArrayDeque`)
- 핵심 알고리즘: 단조 스택
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42584

## 문제 설명

초 단위로 기록된 주식 가격이 주어질 때, 각 시점의 가격이 떨어지지 않은 기간이 몇 초인지 구한다. 가격이 떨어진 순간까지도 기간에 포함한다.

```text
prices = [1, 2, 3, 2, 3]
answer = [4, 3, 1, 1, 0]
```

인덱스 `2`의 가격은 `3`이고 다음 시점의 가격은 `2`이므로 1초 뒤 가격이 떨어진다.

## 핵심 아이디어

스택에는 가격 자체가 아니라 아직 가격이 떨어진 시점을 만나지 못한 가격의 인덱스를 저장한다.

```java
Deque<Integer> stack = new ArrayDeque<>();
```

인덱스를 저장하면 이전 가격과 유지 시간을 모두 구할 수 있다.

```text
이전 가격 = prices[이전 인덱스]
유지 시간 = 현재 인덱스 - 이전 인덱스
```

## 현재 가격과 이전 가격 비교

현재 가격이 스택 맨 위 인덱스의 가격보다 낮으면 이전 가격이 현재 시점에 떨어진 것이다.

```java
while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
    int previousIndex = stack.pop();
    answer[previousIndex] = i - previousIndex;
}
```

예를 들어 현재 인덱스가 `3`이고 다음 상태라면:

```text
prices[2] = 3
prices[3] = 2

3 > 2 → 인덱스 2의 가격이 현재 시점에 하락
유지 시간 = 3 - 2 = 1초
```

가격이 떨어진 인덱스는 정답을 계산한 뒤 스택에서 제거한다.

## `if`가 아니라 `while`을 사용하는 이유

현재 가격 하나로 스택에 있는 여러 이전 가격의 하락 시점이 결정될 수 있다. 따라서 스택 맨 위의 가격이 현재 가격보다 높은 동안 반복해서 처리한다.

```text
이전 가격들: 5, 4, 3
현재 가격: 1

3 > 1 → 처리
4 > 1 → 처리
5 > 1 → 처리
```

비교가 끝나면 현재 인덱스도 이후 가격과 비교하기 위해 스택에 저장한다.

```java
stack.push(i);
```

## 끝까지 가격이 떨어지지 않은 경우

전체 가격을 확인한 뒤 스택에 남은 인덱스는 마지막 시점까지 가격이 떨어지지 않은 경우다.

```java
int lastIndex = prices.length - 1;

while (!stack.isEmpty()) {
    int previousIndex = stack.pop();
    answer[previousIndex] = lastIndex - previousIndex;
}
```

예를 들어 인덱스 `1`의 가격이 마지막 인덱스 `4`까지 떨어지지 않았다면 유지 시간은 다음과 같다.

```text
4 - 1 = 3초
```

마지막 가격은 이후 시간이 없으므로 결과가 자동으로 `0`이 된다.

## 동작 예시

```text
가격: [1, 2, 3, 2, 3]
시간:  0  1  2  3  4
```

```text
0초: 인덱스 0 push
1초: 가격이 떨어지지 않음 → 인덱스 1 push
2초: 가격이 떨어지지 않음 → 인덱스 2 push
3초: 가격 3 > 현재 가격 2
     → 인덱스 2 pop
     → answer[2] = 3 - 2 = 1
     → 인덱스 3 push
4초: 가격이 떨어지지 않음 → 인덱스 4 push
```

순회가 끝난 뒤 남은 인덱스는 마지막 시점까지의 시간을 계산한다.

```text
answer[0] = 4 - 0 = 4
answer[1] = 4 - 1 = 3
answer[3] = 4 - 3 = 1
answer[4] = 4 - 4 = 0
```

최종 결과:

```text
[4, 3, 1, 1, 0]
```

## 전체 흐름

```text
모든 가격을 앞에서부터 확인
             ↓
스택의 이전 가격 > 현재 가격인가?
 ├─ 예 → 이전 인덱스 pop
 │       현재 인덱스 - 이전 인덱스 기록
 │       다시 스택 맨 위와 비교
 └─ 아니요
             ↓
현재 인덱스를 스택에 push
             ↓
전체 순회가 끝나면 남은 인덱스에
마지막 시점까지의 기간 기록
```

## 복잡도

각 인덱스는 스택에 한 번 들어가고 최대 한 번 나온다. 내부에 `while`문이 있지만 전체 push와 pop 횟수는 각각 최대 `n`번이다.

- 시간 복잡도: `O(n)`
- 공간 복잡도: `O(n)`

## 전체 코드

```java
import java.util.ArrayDeque;
import java.util.Deque;

class PG_42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int previousIndex = stack.pop();

                answer[previousIndex] = i - previousIndex;
            }

            stack.push(i);
        }

        int lastIndex = prices.length - 1;

        while (!stack.isEmpty()) {
            int previousIndex = stack.pop();

            answer[previousIndex] = lastIndex - previousIndex;
        }

        return answer;
    }
}
```
