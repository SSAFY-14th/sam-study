import java.io.*;
import java.util.*;

public class Main {

	static int[] board;
	static int N;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		board = new int[N];

		dfs(0);
		System.out.println(count);

	}

	// 백 트래킹
	private static void dfs(int row) {
		if (row == N) {
			count++;
			return;
		}

		for (int col = 0; col < N; col++) {
			board[row] = col; //row 행에 col 배치
			if (isSafe(row)) {
				dfs(row + 1);
			}

		}
	}

	private static boolean isSafe(int row) {
		for (int i = 0; i < row; i++) {
			//열에 퀸이 있나요
			if (board[i] == board[row]) {
				return false;
			}
			//대각선에 퀸이 있나요
			if (Math.abs(row - i) == Math.abs(board[row] - board[i])) {
				return false;
			}
		}
		return true;
	}
}
