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

	static int[][] maze;
	static int[][] dist;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new int[N][M]; // 미로 정보
		dist = new int[N][M]; // 탐색 거리 표시

		for (int i = 0; i < N; i++) {
			String[] strArr = br.readLine().toString().split("");
			for (int j = 0; j < M; j++) {
				maze[i][j] = Integer.parseInt(strArr[j]);
			}
		} // 미로 입력 끝

		bfs(0, 0);

		System.out.println(dist[N - 1][M - 1]);

	}// main

	// bfs 탐색 + depth 표시
	static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();

		q.add(new Pos(r, c));
		dist[r][c] = 1;

		while (!q.isEmpty()) {
			Pos curr = q.poll();

			int[] dr = { 1, -1, 0, 0 };
			int[] dc = { 0, 0, 1, -1 };

			// 인접 칸으로 이동(bfs)
			for (int k = 0; k < 4; k++) {
				int nr = curr.r + dr[k];
				int nc = curr.c + dc[k];

				// 유효 인덱스인가
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				// 이동 가능하고 방문하지 않았으면 큐에 삽입
				if (maze[nr][nc] == 1 && dist[nr][nc] == 0) {
					q.add(new Pos(nr, nc));
					dist[nr][nc] = dist[curr.r][curr.c] + 1;
				}
			}
		}

	}// bfs

}
