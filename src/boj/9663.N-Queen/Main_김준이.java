import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] board;
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		board = new int[N][N];
		ans = 0;
		
		dfsChess(0); // 0번 행부터 시작
		
		System.out.println(ans);

	}// main

	static void dfsChess(int r) {
		if(r == N) {
			ans++;
			return;
		}
		for (int c = 0; c < N; c++) {
			if (possible(r, c)) {
				board[r][c] = 1;
				dfsChess(r + 1); // 다음 행 두기
				board[r][c] = 0; // 다시 원상복구
			}
		} // for
	}// dfsChess

	static boolean possible(int r, int c) {
		
		// 세로에 퀸이 있는지 검사
		for (int i = 0; i < N; i++) {
			if (board[i][c] == 1) {
				return false;
			}
		}

		// 대각선에 퀸이 있는지 검사
		for (int i = 0; i < r; i++) {	//이전 행만 검사
			for (int j = 0; j < N; j++) {
				if (Math.abs(r - i) == Math.abs(c - j) && board[i][j] == 1)
					return false;
			}
		}
		return true;
	}
}