import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	//나이트 이동 델타
	static int[] dr = { -2, -2, -1, +1, +2, +2, +1, -1 };
	static int[] dc = { -1, +1, +2, +2, +1, -1, -2, -2 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 체스판의 크기
			int I = Integer.parseInt(br.readLine());

			// 시작 지점
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());

			// 도착 지점
			st = new StringTokenizer(br.readLine());
			int destRow = Integer.parseInt(st.nextToken());
			int destCol = Integer.parseInt(st.nextToken());

			int[][] board = new int[I][I];
			for (int i = 0; i < I; i++) {
				Arrays.fill(board[i], -1);
			}
			
			//bfs 시작
			Queue<Pos> q = new LinkedList<>();

			q.add(new Pos(startRow, startCol));
			board[startRow][startCol] = 0;

			while (!q.isEmpty()) {
				Pos curr = q.poll();

				if (curr.r == destRow && curr.c == destCol)
					break;

				for (int k = 0; k < 8; k++) {
					int nr = curr.r + dr[k];
					int nc = curr.c + dc[k];

					if (nr < 0 || nc < 0 || nr >= I || nc >= I)
						continue;

					if (board[nr][nc] == -1) {
						q.add(new Pos(nr, nc));
						board[nr][nc] = board[curr.r][curr.c] + 1;
					}

				} // for
			} // while

			System.out.println(board[destRow][destCol]);
		} // test case loop
	}// main

}
