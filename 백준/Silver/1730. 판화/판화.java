
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();

		String command = scan.hasNext() ? scan.next() : "";

		int curR = 0, curC = 0;
		boolean[][] pathVertical = new boolean[N][N];
		boolean[][] pathHorizontal = new boolean[N][N];

		for (int i = 0; i < command.length(); i++) {
			char cmd = command.charAt(i);
			if (cmd == 'D') {
				if (curR == N - 1) continue;
				pathVertical[curR][curC] = pathVertical[curR + 1][curC] = true;
				curR++;
			} else if (cmd == 'U') {
				if (curR == 0) continue;
				pathVertical[curR][curC] = pathVertical[curR-1][curC] = true;
				curR--;
			} else if (cmd == 'L') {
				if (curC == 0) continue;
				pathHorizontal[curR][curC] = pathHorizontal[curR][curC - 1] = true;
				curC--;
			} else {
				if (curC == N - 1) continue;
				pathHorizontal[curR][curC] = pathHorizontal[curR][curC + 1] = true;
				curC++;
			}
		}

		for (int i = 0; i < N; i++) {
			String ans = "";
			for (int j = 0; j < N; j++) {
				if (pathVertical[i][j] && pathHorizontal[i][j]) {
					ans += "+";
				} else if (pathVertical[i][j]) {
					ans += "|";
				} else if (pathHorizontal[i][j]) {
					ans += "-";
				} else {
					ans += ".";
				}
			}
			System.out.println(ans);
		}
	}
}


/*
public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		scan.nextLine();

		char[][] board = new char[N][N];

		String path = scan.nextLine();
		char prev = 0;

		Position pos = new Position(N-1, 0);

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '.';
			}
		}

		for (int i = 0; i < path.length(); i++) {
			char dir = path.charAt(i);

			if (dir == 'D' && (pos.x - 1) >= 0) {
				if (prev == '-') {
					board[pos.x][pos.y] = '+';
					pos.x -= 1;
					prev = '|';
					continue;
				}
				board[pos.x][pos.y] = '|';
				pos.x -= 1;
				prev = '|';
			}

			if (dir == 'U' && (pos.x + 1) < N) {
				if (prev == '-') {
					board[pos.x][pos.y] = '+';
					pos.x += 1;
					prev = '|';
					continue;
				}
				board[pos.x][pos.y] = '|';
				pos.x += 1;
				prev = '|';
			}

			if (dir == 'R' && (pos.y + 1) < N) {
				if (prev == '|') {
					board[pos.x][pos.y] = '+';
					pos.y += 1;
					prev = '-';
					continue;
				}
				board[pos.x][pos.y] = '-';
				pos.y += 1;
				prev = '-';
			}

			if (dir == 'L' && (pos.y - 1) >= 0) {
				if (prev == '|') {
					board[pos.x][pos.y] = '+';
					pos.y -= 1;
					prev = '-';
					continue;
				}
				board[pos.x][pos.y] = '-';
				pos.y -= 1;
				prev = '-';
			}
		}


		for (int i = board.length-1; i >= 0; i--) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}


	}

	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
 */