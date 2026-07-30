# 중요한 단어를 스포 방지

- 문제 번호: 468370
- 풀이 날짜: 2026-07-27
- 핵심 자료구조: 배열, `HashSet`, 중첩 `List`
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/468370

## 핵심 아이디어

1. 각 문자가 어느 스포 구간에 속하는지 배열에 표시한다.
2. 메시지를 단어 단위로 읽는다.
3. 일반 공개 단어와 스포 단어를 나눠 저장한다.
4. 스포 단어를 공개 순서대로 확인한다.

이 문제는 스포 구간은 문자 인덱스로 주어지지만, 중요한 단어인지는 단어 단위로 판단한다. 따라서 먼저 문자별 스포 정보를 만들어 두고, 그 정보를 이용해 각 단어를 분류한다.

## 문자에 구간 번호 표시하기

```text
문자:       a  b  c     d  e  f
구간 번호: -1  0  0 -1 -1  1 -1

-1: 일반 공개
 0: 첫 번째 스포 구간
 1: 두 번째 스포 구간
```

단어의 문자 중 하나라도 `0` 이상이면 스포 단어다.

Java의 `int` 배열은 기본값이 `0`이다. 하지만 `0`은 첫 번째 스포 구간 번호로 사용해야 하므로, 처음에 모든 값을 `-1`로 채운다.

```java
int[] messageArr = new int[message.length()];
Arrays.fill(messageArr, -1);
```

그다음 각 스포 구간에 포함되는 문자 위치에 구간 번호를 기록한다.

```java
for (int index = rangeStart; index <= rangeEnd; index++) {
    messageArr[index] = rangeIndex;
}
```

문제의 `start`와 `end`는 모두 구간에 포함되므로 조건은 `index <= rangeEnd`이다.

## 단어 찾기

`wordStart`부터 다음 공백까지가 하나의 단어다.

```java
int wordEnd = message.indexOf(' ', wordStart);
```

마지막 단어 뒤에는 공백이 없으므로 `indexOf()`가 `-1`을 반환한다. 이때 메시지 길이를 단어의 끝으로 사용한다.

```java
if (wordEnd == -1) {
    wordEnd = message.length();
}
```

```text
message: "my phone number"

0 ~ 1  → my
3 ~ 7  → phone
9 ~ 끝 → number
```

`substring(wordStart, wordEnd)`에서 `wordEnd` 위치의 문자는 포함되지 않으므로 공백 없이 단어만 얻을 수 있다.

## 여러 구간에 걸친 단어

```text
단어 문자:  s  e  c  r  e  t
구간 번호:  0  0 -1  1  1  1
```

이 단어는 `1번` 구간까지 클릭해야 전부 보인다. 따라서 가장 큰 구간 번호인 `1`에 저장한다.

```text
0번 클릭 → 일부가 아직 가려짐
1번 클릭 → 단어 전체 공개
```

단어에 포함된 문자들의 구간 번호 중 최댓값을 구하면 완전히 공개되는 시점을 알 수 있다.

```java
int revealIndex = -1;

for (int index = wordStart; index < wordEnd; index++) {
    revealIndex = Math.max(revealIndex, messageArr[index]);
}
```

- 결과가 `-1`이면 일반 공개 단어다.
- 결과가 `0` 이상이면 해당 번호의 구간에서 완전히 공개되는 스포 단어다.

## 두 저장소의 역할

```text
publicWords
→ 일반 공개 영역에 등장한 단어

revealedWords
→ 단어가 언제 공개되는지 저장하는 일정표

previouslyRevealed
→ 지금까지 공개된 단어를 저장하는 기록
```

예를 들어 다음과 같이 저장될 수 있다.

```text
publicWords = [number, is]

revealedWords
0번 → [phone]
1번 → [secret]
2번 → [phone, number]
```

`revealedWords`에는 앞으로 공개될 단어 전체가 들어 있다. 반면 `previouslyRevealed`에는 현재까지 실제로 공개된 단어만 들어 있으므로 두 저장소를 따로 사용한다.

## 중요한 단어 조건

```text
일반 공개 영역에 같은 단어가 없음
              AND
이전에 공개된 스포 단어와 중복되지 않음
              ↓
중요한 단어
```

다음 조건으로 간단하게 확인할 수 있다.

```java
if (!publicWords.contains(word)
        && !previouslyRevealed.contains(word)) {
    answer++;
}
```

중요하지 않은 단어도 공개된 뒤에는 `previouslyRevealed`에 저장해야 한다. 문제의 조건은 이전에 공개된 **중요한 단어**가 아니라 이전에 공개된 **스포 단어**와 중복되지 않아야 한다는 뜻이기 때문이다.

같은 시점에 여러 단어가 공개되면 왼쪽부터 판단한다. 처음부터 메시지를 왼쪽에서 오른쪽으로 읽어 `revealedWords`에 추가했으므로, 저장된 순서대로 확인하면 된다.

## 전체 흐름

```text
문자별 스포 구간 번호 기록
            ↓
메시지를 왼쪽부터 단어로 분리
            ↓
일반 단어인가?
 ├─ 예 → publicWords에 저장
 └─ 아니요 → 완전 공개 시점에 저장
            ↓
스포 구간 순서대로 단어 확인
            ↓
일반 영역과 이전 공개 기록에 모두 없으면 정답 +1
```

## 주의할 점

- `HashSet<Integer>`가 아니라 단어를 저장하는 `HashSet<String>`을 사용한다.
- 여러 구간에 걸친 단어는 마지막 구간 목록에 한 번만 저장한다.
- 마지막 단어 뒤에는 공백이 없다는 점을 처리한다.
- 중요 여부를 판단한 뒤에는 모든 스포 단어를 `previouslyRevealed`에 추가한다.

## 복잡도

- 스포 구간 표시: `O(n)`
- 단어 확인: `O(n)`
- `HashSet`의 추가와 조회: 평균 `O(1)`
- 전체 시간: `O(n)`
- 공간: `O(n)` — 문자 구간 배열과 단어 저장소

---

# 노란불 신호등

- 문제 번호: 468371
- 풀이 날짜: 2026-07-30
- 핵심 알고리즘: 시뮬레이션, 나머지 연산, 최대공약수, 최소공배수
- 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/468371

## 핵심 아이디어

1. `1초`부터 시간을 하나씩 증가시킨다.
2. 각 신호등이 현재 노란불인지 확인한다.
3. 모든 신호등이 노란불이면 현재 시각을 반환한다.
4. 모든 신호등 주기의 최소공배수까지 찾지 못했다면 `-1`을 반환한다.

각 신호등은 `초록 → 노랑 → 빨강` 순서로 같은 상태를 반복한다. 따라서 현재 시각이 신호등의 한 주기에서 어느 위치인지 나머지 연산으로 구할 수 있다.

## 한 신호등의 주기

초록불이 `G초`, 노란불이 `Y초`, 빨간불이 `R초` 동안 켜진다면 전체 주기는 다음과 같다.

```java
int cycle = G + Y + R;
```

예를 들어 `[2, 1, 2]` 신호등은 다음과 같이 움직인다.

```text
실제 시간:  1  2  3  4  5 | 6  7 ...
position:   0  1  2  3  4 | 0  1 ...
신호:       초 초 노 빨 빨 | 초 초 ...
```

시간은 `1초`부터 시작하지만 배열처럼 주기 안의 위치를 `0`부터 표현하기 위해 `time - 1`을 사용한다.

```java
int position = (time - 1) % cycle;
```

`position`은 현재 시각이 이 신호등의 한 주기 안에서 몇 번째 칸에 있는지를 뜻한다.

## 노란불 범위 확인

주기 내부의 범위는 다음과 같다.

```text
초록불: 0 이상 G 미만
노란불: G 이상 G + Y 미만
빨간불: G + Y 이상 cycle 미만
```

따라서 현재 노란불인지 다음 조건으로 확인한다.

```java
boolean isYellow =
        G <= position && position < G + Y;
```

예를 들어 `G = 2`, `Y = 3`이라면 노란불의 위치는 `2, 3, 4`다.

```text
2 <= position && position < 5
```

## 모든 신호등 확인

각 시간마다 먼저 모든 신호등이 노란불이라고 가정한다.

```java
boolean allYellow = true;
```

노란불이 아닌 신호등을 하나라도 발견하면 가정을 `false`로 바꾸고 `for`문을 끝낸다.

```java
if (!isYellow) {
    allYellow = false;
    break;
}
```

여기서 `break`는 가장 가까운 반복문인 신호등 확인용 `for`문만 종료한다. 바깥쪽 시간 반복은 다음 시각으로 계속 진행한다.

모든 신호등을 확인한 뒤에도 `allYellow`가 `true`라면 원하는 시각을 찾은 것이다.

```java
if (allYellow) {
    return time;
}
```

## 왜 최소공배수까지만 확인하는가?

신호등 주기가 각각 `3초`, `4초`라면 두 신호등의 전체 상태 조합은 두 수의 최소공배수인 `12초`마다 반복된다.

```text
1초 ~ 12초  : 한 번의 전체 상태 조합
13초 ~ 24초 : 1초 ~ 12초와 같은 상태 조합
```

최소공배수까지 모두 노란불인 시각이 없다면 그 뒤에도 같은 상태만 반복되므로 정답은 존재하지 않는다.

여러 신호등의 최소공배수는 하나씩 누적해 구한다.

```java
int limit = 1;

for (int i = 0; i < signals.length; i++) {
    int cycle = signals[i][0] + signals[i][1] + signals[i][2];
    limit = lcm(limit, cycle);
}
```

예를 들어 주기가 `5`, `7`, `4`라면 다음과 같이 계산된다.

```text
lcm(1, 5)  = 5
lcm(5, 7)  = 35
lcm(35, 4) = 140
```

## 최대공약수와 최소공배수

최대공약수는 두 수를 모두 나눌 수 있는 가장 큰 수다. 유클리드 호제법을 이용하면 나머지가 `0`이 될 때의 수를 최대공약수로 구할 수 있다.

```java
private int gcd(int a, int b) {
    while (b != 0) {
        int remainder = a % b;
        a = b;
        b = remainder;
    }

    return a;
}
```

최소공배수는 최대공약수를 이용해 계산한다.

```java
private int lcm(int a, int b) {
    return a / gcd(a, b) * b;
}
```

예를 들어 `6`과 `8`의 최대공약수는 `2`이므로 최소공배수는 `24`다.

```text
6 / 2 × 8 = 24
```

## 전체 흐름

```text
모든 신호등 주기의 최소공배수 계산
                    ↓
1초부터 최소공배수 시각까지 반복
                    ↓
각 신호등의 position 계산
                    ↓
모두 노란불인가?
 ├─ 예   → 현재 시각 반환
 └─ 아니요 → 다음 시각 확인
                    ↓
끝까지 찾지 못하면 -1 반환
```

## 주의할 점

- Java 배열의 길이는 `signals.length()`가 아니라 `signals.length`다.
- 각 신호등은 `signals[0]`이 아니라 `signals[i]`로 확인한다.
- 문제의 시간이 `1초`부터 시작하므로 `position`은 `(time - 1) % cycle`로 구한다.
- `break`는 신호등을 확인하는 안쪽 `for`문만 종료한다.
- 무한 반복을 막기 위해 최소공배수까지만 확인한다.

## 복잡도

- `n`: 신호등 개수
- `L`: 모든 신호등 주기의 최소공배수
- 시간 복잡도: `O(n × L)`
- 공간 복잡도: `O(1)`
