import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	static int W, H;
	static int[][] map;
	static boolean[][] visited;

	static int cnt;
	static int[] dr = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] dc = { 0, 0, 1, -1, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			W = Integer.parseInt(st.nextToken()); // 지도의 너비
			H = Integer.parseInt(st.nextToken()); // 지도의 높이

			// 테스트 케이스 종료
			if (W == 0 && H == 0)
				break;

			map = new int[H][W];
			visited = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝

			cnt = 0;

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					// 방문하지 않은 섬이면 bfs
					if (visited[i][j] || map[i][j] == 0)
						continue;
					bfs(i, j);
					cnt++;
				}
			}

			System.out.println(cnt);
		} // test case
	}// main

	static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();

		q.add(new Pos(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Pos curr = q.poll();

			// 현재 위치에서 8방 탐색하면서 인접 땅을 큐에 넣기
			for (int k = 0; k < 8; k++) {
				int nr = curr.r + dr[k];
				int nc = curr.c + dc[k];

				// 유효 인덱스 검사
				if (nr < 0 || nc < 0 || nr >= H || nc >= W)
					continue;

				// 방문하지 않은 땅이면 삽입
				if (map[nr][nc] == 1 && !visited[nr][nc]) {
					q.add(new Pos(nr, nc));
					visited[nr][nc] = true;
				}
			}
		} // end while
	}// bfs

}