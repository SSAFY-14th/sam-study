import java.io.*;
import java.util.*;

public class Main {

	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);

	}

	private static void dfs(int idx) {
		// 1.기저조건
		// 81번째 인덱스까지 가면 출력하고 종료하세요
		if (idx == 81) {
			printBoard();
			System.exit(0);
		}

		int r = idx / 9;
		int c = idx % 9;

		// 2. 유망성검사
		if (board[r][c] != 0) { // 이미 숫자가 있으면 건너뛰기
			dfs(idx + 1);
		} else {
			for (int num = 1; num <= 9; num++) {
				if (isSafe(r, c, num)) {
					board[r][c] = num;
					dfs(idx + 1);
					board[r][c] = 0;
				}
			}
		}

	}

	private static boolean isSafe(int r, int c, int num) {
		// 같은 행
		for (int j = 0; j < 9; j++) {
			if (board[r][j] == num)
				return false;
		}

		// 같은 열
		for (int j = 0; j < 9; j++) {
			if (board[j][c] == num)
				return false;
		}

		// 3x3
		int nr = (r / 3) * 3;
		int nc = (c / 3) * 3;
		for (int j = nr; j < nr + 3; j++) {
			for (int k = nc; k < nc + 3; k++) {
				if (board[j][k] == num)
					return false;
			}
		}
		return true;
	}

	private static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
