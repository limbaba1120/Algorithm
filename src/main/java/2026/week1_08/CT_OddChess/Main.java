import java.io.*;
import java.util.*;

// 말의 상태를 저장하는 클래스
class Piece {
    int row;
    int col;
    int dir;
    boolean alive; // 술래에게 잡혔는지 판단

    Piece(int row, int col, int dir, boolean alive) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.alive = alive;
    }
}

public class Main {
    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // row
    static final int[] dc = {0, -1, -1, -1, 0, 1, 1, 1}; // column
    static final int SIZE = 4;
    static final int PIECE_COUNT = 16;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 말 번호 그대로 인덱스로 사용하기 위해 크기는 17로 만듬
        Piece[] pieces = new Piece[PIECE_COUNT + 1];

        // 각 칸에 위치한 말 번호 저장
        int[][] board = new int[SIZE][SIZE];

        // 체스판과 각 말의 상태를 입력
        for (int row = 0; row < SIZE; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < SIZE; col++) {
                int number = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                board[row][col] = number;
                pieces[number] = new Piece(row, col, dir, true);
            }
        }

        // 술래는 게임 시작과 동시에 (0, 0) 에 있는 말을 잡음
        int firstNumber = board[0][0];
        Piece firstPiece = pieces[firstNumber];

        // 잡힌 상태로 변경
        board[0][0] = 0;
        firstPiece.alive = false;

        dfs(board, pieces, 0, 0, firstPiece.dir, firstNumber);

        System.out.println(answer);
    }

    // 해당 좌표가 4x4 체스판 안에 있는지 확인
    public static boolean isInRange(int row, int col) {
        return row >= 0 && col >= 0 && row < SIZE && col < SIZE;
    }

    // 주어진 번호의 말 한 마리를 이동
    public static void movePiece(
            int number,
            int[][] board,
            Piece[] pieces,
            int hunterRow,
            int hunterCol
    ) {
        Piece piece = pieces[number];

        // 이미 술래에게 잡혔는지 확인
        if (!piece.alive) {
            return;
        }

        /*
         * 먼저 현재 방향으로 이동을 시도한다.
         *
         * 이동할 수 없다면 반시계 방향으로
         * 45도씩 회전하며 최대 8방향을 확인한다.
         */
        for (int rotation = 0; rotation < 8; rotation++) {
            int nextDir = (piece.dir + rotation) % 8;

            int nextRow = piece.row + dr[nextDir];
            int nextCol = piece.col + dc[nextDir];

            if (!isInRange(nextRow, nextCol)) {
                continue;
            }

            // 술래가 있는 칸으로는 이동 금지
            if (nextRow == hunterRow && nextCol == hunterCol) {
                continue;
            }

            // 여기까지 도달하면 이동 가능한 방향을 찾음
            // 목적지의 말 번호를 가져옴
            int otherNumber = board[nextRow][nextCol];

            // 이동하는 말의 기존 위치를 보관
            int currentRow = piece.row;
            int currentCol = piece.col;

            // 체스판에서 위치를 변경
            board[currentRow][currentCol] = otherNumber;
            board[nextRow][nextCol] = number;

            // 목적지에 있는 말의 위치 변경하기
            // 목적지에 있는 말의 번호가 0 (= 없는 말) 이면 skip
            if (otherNumber != 0) {
                pieces[otherNumber].row = currentRow;
                pieces[otherNumber].col = currentCol;
            }

            // 현재 말은 목적지로 이동
            piece.row = nextRow;
            piece.col = nextCol;
            piece.dir = nextDir;

            // 이동에 성공했으면 즉시 함수 종료
            return;
        }
    }

    // 살아있는 말을 1번부터 16번까지 순서대로 이동
    public static void moveAllPieces(
            int[][] board,
            Piece[] pieces,
            int hunterRow,
            int hunterCol
    ) {
        for (int number = 1; number <= PIECE_COUNT; number++) {
            movePiece(number, board, pieces, hunterRow, hunterCol);
        }
    }

    // 체스판 복사 함수 만들기
    // 술래가 이동할 수 있는 경우를 각각 탐색하려면 현재 체스판을 복사해야함
    // 복사 이유: 술래가 이동할 수 있는 선택지는 여러 개, 각 선택지를 독립적으로 확인해야함
    public static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            copy[row] = board[row].clone();
        }

        return copy;
    }

    // 말 배열을 복사
    public static Piece[] copyPieces(Piece[] pieces) {
        Piece[] copy = new Piece[PIECE_COUNT + 1];

        for (int number = 1; number <= PIECE_COUNT; number++) {
            Piece piece = pieces[number];

            copy[number] = new Piece(
                    piece.row,
                    piece.col,
                    piece.dir,
                    piece.alive
            );
        }

        return copy;
    }

    public static void dfs(
            int[][] board,
            Piece[] pieces,
            int hunterRow,
            int hunterCol,
            int hunterDir,
            int score
    ) {
        // 지금까지 얻은 점수의 최댓값을 갱신
        answer = Math.max(answer, score);

        // 모든 말 이동시키기
        moveAllPieces(board, pieces, hunterRow, hunterCol);

        // 술래가 이동할 후보 위치 계산하기 (1칸, 2칸, 3칸 떨어진 위치를 확인)
        for (int distance = 1; distance < SIZE; distance++) {
            int nextRow = hunterRow + dr[hunterDir] * distance;
            int nextCol = hunterCol + dc[hunterDir] * distance;

            if (!isInRange(nextRow, nextCol)) {
                break;
            }

            // 후보 위치에 말 번호
            int targetNumber = board[nextRow][nextCol];

            if (targetNumber == 0) {
                continue;
            }

            // 각 이동 선택은 서로 영향을 주면 안되어서 현재 게임 상태를 복사
            int[][] nextBoard = copyBoard(board);
            Piece[] nextPieces = copyPieces(pieces);

            // 말을 잡은 상태로 변경하기
            Piece target = nextPieces[targetNumber];
            nextBoard[nextRow][nextCol] = 0;
            target.alive = false;

            dfs(
                    nextBoard,
                    nextPieces,
                    nextRow,
                    nextCol,
                    target.dir,
                    score + targetNumber
            );
        }
    }
}
