import java.util.Arrays;

class PG_42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            answer[i] = findPosition(array, commands[i]);
        }

        return answer;
    }

    private int findPosition(int[] array, int[] command) {
        int first = command[0];
        int last = command[1];
        int position = command[2];

        int[] copied = Arrays.copyOfRange(array, first - 1, last);
        Arrays.sort(copied);

        return copied[position - 1];
    }
}
