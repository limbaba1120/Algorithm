import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PG_468370 {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;

        // 각 문자가 속한 스포 구간 번호를 저장한다. -1은 일반 공개 구간이다.
        int[] messageArr = new int[message.length()];
        Arrays.fill(messageArr, -1);

        for (int rangeIndex = 0; rangeIndex < spoiler_ranges.length; rangeIndex++) {
            int rangeStart = spoiler_ranges[rangeIndex][0];
            int rangeEnd = spoiler_ranges[rangeIndex][1];

            for (int index = rangeStart; index <= rangeEnd; index++) {
                messageArr[index] = rangeIndex;
            }
        }

        Set<String> publicWords = new HashSet<>();
        List<List<String>> revealedWords = new ArrayList<>();

        for (int i = 0; i < spoiler_ranges.length; i++) {
            revealedWords.add(new ArrayList<>());
        }

        // 메시지를 왼쪽부터 단어 단위로 읽는다.
        int wordStart = 0;

        while (wordStart < message.length()) {
            int wordEnd = message.indexOf(' ', wordStart);

            if (wordEnd == -1) {
                wordEnd = message.length();
            }

            String word = message.substring(wordStart, wordEnd);
            int revealIndex = -1;

            // 가장 큰 구간 번호가 이 단어가 완전히 공개되는 시점이다.
            for (int index = wordStart; index < wordEnd; index++) {
                revealIndex = Math.max(revealIndex, messageArr[index]);
            }

            if (revealIndex == -1) {
                publicWords.add(word);
            } else {
                revealedWords.get(revealIndex).add(word);
            }

            wordStart = wordEnd + 1;
        }

        Set<String> previouslyRevealed = new HashSet<>();

        // 스포 구간 순서대로, 같은 구간에서는 왼쪽 단어부터 판단한다.
        for (List<String> words : revealedWords) {
            for (String word : words) {
                if (!publicWords.contains(word)
                        && !previouslyRevealed.contains(word)) {
                    answer++;
                }

                // 중요 여부와 상관없이 이미 공개된 스포 단어로 기록한다.
                previouslyRevealed.add(word);
            }
        }

        return answer;
    }
}
