/**
 * 프로그래머스 258705. 산 모양 타일링
 *
 * 핵심 관찰:
 *  - 정삼각형 타일 1개는 어디든 회전시켜 끼울 수 있어 "혼자 두는 삼각형"은 항상 채워진다.
 *  - 마름모 타일은 변을 공유하는 두 삼각형을 하나로 묶는 것뿐이다.
 *  - 따라서 실제 선택은 "이웃한 두 삼각형을 묶을지 말지" 뿐이며, 이는 1×N 복도를
 *    1칸/2칸 타일로 채우는 문제(=피보나치)와 동일하다.
 *  - tops[i]=1인 삼각형은 위에 곁방(삼각형)이 하나 더 붙는데, 이웃한 곁방끼리는
 *    서로 붙지 않으므로 그 자리에서만 "곁방과 묶기" 선택지가 하나 늘어 계수가 2배가 된다.
 *
 * 풀이: 2n+1개의 삼각형을 순서대로 훑으며 a[k] = a[k-1] + a[k-2] (일반 칸)
 *      또는 a[k] = 2*a[k-1] + a[k-2] (tops로 곁방이 붙은 칸)를 누적. O(n).
 */
class Solution {
    public int solution(int n, int[] tops) {
        final int MOD = 10007;
        long prev2 = 1; // a[0]
        long prev1 = 1; // a[1] (첫 삼각형은 곁방이 없어 항상 일반 칸)

        for (int k = 2; k <= 2 * n + 1; k++) {
            boolean hasBump = (k % 2 == 0) && tops[k / 2 - 1] == 1;
            long cur = hasBump
                    ? (2 * prev1 + prev2) % MOD
                    : (prev1 + prev2) % MOD;
            prev2 = prev1;
            prev1 = cur;
        }

        return (int) prev1;
    }
}
