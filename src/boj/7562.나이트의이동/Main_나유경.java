import java.io.*;
import java.util.*;

public class Main {

	static int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int T, l, a, a1, b, b1;
	static int[][] chess;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			a1 = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			b = Integer.parseInt(st.nextToken());
			b1 = Integer.parseInt(st.nextToken());

			chess = new int[l][l];

			visited = new boolean[l][l];

			bfs(a, a1);

		}

	}

	private static void bfs(int a, int a1) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { a, a1 });
		visited[a][a1] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			if (r == b && c == b1) {
				System.out.println(chess[r][c]);
				return;
			}

			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 여기서 a,a1,b,b1에 도착하면 멈춰야할것같은데
				if (nr >= 0 && nr < l && nc >= 0 && nc < l && !visited[nr][nc]) {
					visited[nr][nc] = true;
					chess[nr][nc] = chess[r][c] + 1;
					q.offer(new int[] { nr, nc });

				}
			}
		}

	}

}
