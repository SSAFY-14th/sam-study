import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = line.charAt(j) - '0';
			}
		}

		visited = new boolean[N][M];

		bfs(0, 0);

	}

	private static void bfs(int sr, int sc) {

		Queue<int[]> q = new LinkedList<>();
		visited[sr][sc] = true;
		q.offer(new int[] { sr, sc });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			if (r == N - 1 && c == M - 1) {
				System.out.println(board[r][c]);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					board[nr][nc] = board[r][c] + 1;
					q.offer(new int[] { nr, nc });
				}
			}

		}

	}
}
