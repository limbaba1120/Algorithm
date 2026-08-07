import java.util.HashSet;
import java.util.Set;

class PG_42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        for (int student : lost) {
            lostSet.add(student);
        }

        for (int student : reserve) {
            reserveSet.add(student);
        }

        // 도난당했지만 여벌 체육복도 있는 학생은 자신의 체육복을 입는다.
        for (int student : lost) {
            if (reserveSet.contains(student)) {
                lostSet.remove(student);
                reserveSet.remove(student);
            }
        }

        int answer = n - lostSet.size();

        // 번호가 작은 학생부터 앞번호의 여벌 체육복을 우선 확인한다.
        for (int student = 1; student <= n; student++) {
            if (!lostSet.contains(student)) {
                continue;
            }

            if (reserveSet.remove(student - 1)) {
                answer++;
            } else if (reserveSet.remove(student + 1)) {
                answer++;
            }
        }

        return answer;
    }
}
