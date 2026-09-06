import java.util.HashSet;
import java.util.Set;

class PG_42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostStudents = new HashSet<>();
        Set<Integer> reserveStudents = new HashSet<>();

        for (int student : lost) {
            lostStudents.add(student);
        }

        // 도난당했지만 여벌도 있는 학생은 자신의 체육복을 사용한다.
        for (int student : reserve) {
            if (lostStudents.contains(student)) {
                lostStudents.remove(student);
            } else {
                reserveStudents.add(student);
            }
        }

        // 앞번호 학생의 여벌을 먼저 확인하고, 없으면 뒷번호를 확인한다.
        for (int student = 1; student <= n; student++) {
            if (!lostStudents.contains(student)) {
                continue;
            }

            if (reserveStudents.contains(student - 1)) {
                lostStudents.remove(student);
                reserveStudents.remove(student - 1);
            } else if (reserveStudents.contains(student + 1)) {
                lostStudents.remove(student);
                reserveStudents.remove(student + 1);
            }
        }

        return n - lostStudents.size();
    }
}
