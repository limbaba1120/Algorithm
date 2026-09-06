import java.util.Arrays;

class PG_42747 {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int length = citations.length;

        for (int i = 0; i < length; i++) {
            int paperCount = length - i;

            if (citations[i] >= paperCount) {
                return paperCount;
            }
        }

        return 0;
    }
}
