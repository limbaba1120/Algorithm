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
