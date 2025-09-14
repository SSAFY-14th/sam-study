import java.io.*;
import java.util.*;

public class Main {
	static int w, h; // 너비, 높이
	static int[][] land;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static boolean[][] visited;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0) break;

			land = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					land[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[h][w];

			count = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && land[i][j] == 1) {
						bfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}

	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc] && land[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}

		}
	}
}
