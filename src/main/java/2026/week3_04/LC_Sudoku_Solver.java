class Main {
    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},                                        
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},                                        
            {'7','.','.','.','2','.','.','.','6'},                                        
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},                                        
            {'.','.','.','.','8','.','.','7','9'}   
        };

        solveSudoku(board);
        printBoard(board);
    }

    static boolean solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;

                for (char k = '1' ; k <= '9' ; k++) {
                    if (isValid(board, i, j, k)) {
                        board[i][j] = k;

                        if (solveSudoku(board)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    static boolean isValid(char[][] board, int i, int j, char k ) {
        int boxRow = (i / 3) * 3;
        int boxCol = (j / 3) * 3;

        for (int a = 0; a < 9; a++) {
            if (board[i][a] == k) return false;
            if (board[a][j] == k) return false;
            if (board[boxRow + a / 3][boxCol + a % 3] == k) return false;
        }
        return true;
    }

    static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);

                if ( j == 2 || i == 5 ) sb.append("|");
                else if ( j < 8) sb.append(" ");
            }
            System.out.println(sb.toString());
            if (i == 2 || i == 5) System.out.println("------+-------+------");
        }
    }
}