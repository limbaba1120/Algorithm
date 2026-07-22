import java.util.*;

class PG_389481 {
    long[] pow26 = new long[12];

    public String solution(long n, String[] bans) {

        // 26^0=1, 26^1=26, 26^2=676 ... 미리 계산
        pow26[0] = 1;
        for (int i = 1; i < 12; i++) {
            pow26[i] = pow26[i - 1] * 26;
        }

        // bans를 길이별로 분류: {1→["d","e"], 2→["aa","ae","bb"]}
        Map<Integer, List<String>> banMap = new HashMap<>();
        for (String ban : bans) {
            banMap.computeIfAbsent(ban.length(), k -> new ArrayList<>()).add(ban);
        }
        // 나중에 이진탐색 쓸 것이므로 정렬 필수
        for (List<String> list : banMap.values()) Collections.sort(list);

        // Step 1: 답이 몇 글자짜리인지 결정
        // 예) n=30, 1글자 유효=24개 → 30>24 → remaining=6, 2글자에서 6번째 찾기
        long remaining = n;
        int targetLen = -1;
        for (int len = 1; len < 12; len++) {
            int bannedCount = banMap.getOrDefault(len, List.of()).size();
            long valid = pow26[len] - bannedCount; // 이 길이에서 유효한 개수
            if (remaining <= valid) {
                targetLen = len; // 이 길이에서 찾으면 됨
                break;
            }
            remaining -= valid; // 이 길이는 통째로 건너뜀
        }

        // Step 2: 이진탐색으로 targetLen 길이 안에서 remaining번째 문자열 찾기
        List<String> bannedAtLen = banMap.getOrDefault(targetLen, List.of());
        long lo = 0, hi = pow26[targetLen] - 1; // 인덱스 탐색 범위

        while (lo < hi) {
            long mid = (lo + hi) / 2;
            // 인덱스 0~mid 중 유효한 문자열 수가 remaining 이상이면 → 답은 mid 이하
            if (countValid(mid, targetLen, bannedAtLen) >= remaining) {
                hi = mid;
            } else {
                lo = mid + 1; // mid까지는 유효한 게 부족 → 더 뒤에 있음
            }
        }

        // 최종 인덱스 lo를 문자열로 변환해서 반환
        return indexToStr(lo, targetLen);
    }

    // 인덱스 0~k 중 banned 아닌 문자열 개수
    // 예) k=7, banned=["aa","ae"] → (7+1) - 2 = 6
    long countValid(long k, int len, List<String> banned) {
        String kStr = indexToStr(k, len);          // 인덱스 k → 문자열 변환
        long bannedUpTo = upperBound(banned, kStr); // kStr 이하인 banned 개수
        return (k + 1) - bannedUpTo;
    }

    // 숫자 인덱스 → 문자열 변환 (26진법)
    // 예) indexToStr(7, 2) → "ah"  (7 = 0*26 + 7 → 'a'+'h')
    String indexToStr(long idx, int len) {
        char[] result = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = (char) ('a' + idx % 26); // 나머지 → 문자
            idx /= 26;                            // 다음 자리로
        }
        return new String(result);
    }

    // 정렬된 리스트에서 target 이하인 원소 개수 (이진탐색)
    // 예) ["aa","ae","bb"], target="ah" → 2개 ("aa","ae")
    long upperBound(List<String> sorted, String target) {
        int lo = 0, hi = sorted.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (sorted.get(mid).compareTo(target) <= 0) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
