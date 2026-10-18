import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 2026-10-18 코드트리 식량 보급
// 학습용 기본 풀이: 겨울나기 1회 O(집 수 × log(보급량 + 2)).
// 큰 입력에서의 시간 제한 통과는 검증하지 않았다.
// 제출할 때 클래스 이름을 Main으로 변경한다.
class CT_20261018_Commissariat {
    static List<Long> houses = new ArrayList<>();
    static long bonus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) {
                int n = Integer.parseInt(st.nextToken());
                for (int j = 0; j < n; j++) {
                    houses.add(Long.parseLong(st.nextToken()));
                }
            } else if (command == 200) {
                long food = Long.parseLong(st.nextToken());
                // 새 집은 이전 강화의 효과를 받지 않는다.
                houses.add(food - bonus);
            } else if (command == 300) {
                bonus += Long.parseLong(st.nextToken());
            } else if (command == 400) {
                long supply = Long.parseLong(st.nextToken());
                System.out.println(findBest(supply));
            }
        }
    }

    static boolean canReach(long target, long supply) {
        long needed = 0;

        for (long saved : houses) {
            long actual = saved + bonus;
            if (actual < target) {
                needed += target - actual;
                if (needed > supply) {
                    return false;
                }
            }
        }
        return true;
    }

    static long findBest(long supply) {
        long minFood = Long.MAX_VALUE;
        for (long saved : houses) {
            minFood = Math.min(minFood, saved + bonus);
        }

        long left = minFood;
        long right = minFood + supply;
        long answer = minFood;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (canReach(mid, supply)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
}
