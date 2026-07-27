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
