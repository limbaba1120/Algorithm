class PG_67256 {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        // 키패드의 마지막 줄을 10(*), 11(0), 12(#)로 표현한다.
        int left = 10;
        int right = 12;

        for (int number : numbers) {
            int target = number == 0 ? 11 : number;

            // 1, 4, 7은 항상 왼손으로 누른다.
            if (number == 1 || number == 4 || number == 7) {
                answer.append("L");
                left = target;
                continue;
            }

            // 3, 6, 9는 항상 오른손으로 누른다.
            if (number == 3 || number == 6 || number == 9) {
                answer.append("R");
                right = target;
                continue;
            }

            int leftRow = (left - 1) / 3;
            int leftColumn = (left - 1) % 3;

            int rightRow = (right - 1) / 3;
            int rightColumn = (right - 1) % 3;

            int targetRow = (target - 1) / 3;
            int targetColumn = (target - 1) % 3;

            int leftDistance =
                    Math.abs(leftRow - targetRow)
                            + Math.abs(leftColumn - targetColumn);

            int rightDistance =
                    Math.abs(rightRow - targetRow)
                            + Math.abs(rightColumn - targetColumn);

            if (leftDistance < rightDistance) {
                answer.append("L");
                left = target;
            } else if (rightDistance < leftDistance) {
                answer.append("R");
                right = target;
            } else if (hand.equals("right")) {
                answer.append("R");
                right = target;
            } else {
                answer.append("L");
                left = target;
            }
        }

        return answer.toString();
    }
}
