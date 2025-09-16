import java.util.*;
import java.io.*;

/*
 * 1은 이동가능 0은 이동 불가
 */

public class Main_김민정 {
	static int N, M; // 행 열
	static int[][] map;
	static int[][] dist; // 거리 저장 및 방문 체크
	// 상하 좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dist = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - 48;
			}
		} // 미로 입력 끝

		int ans = bfs(0, 0);
		System.out.println(ans);

	}// main

	static int bfs(int r, int c) {
		// 좌표 저장 큐
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c }); // 현재 좌표 큐에 넣음
		dist[r][c] = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll(); // 좌표 꺼냄

			// 도착 지점이면
			if (curr[0] == N - 1 && curr[1] == M - 1)
				return dist[N - 1][M - 1];

			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				// 범위 벗어나면
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				// 벽이면
				if (map[nr][nc] == 0)
					continue;

				// 이미 방문했다면
				if (dist[nr][nc] != 0)
					continue;

				dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
				q.add(new int[] { nr, nc });

			}

		}

		return -1;

	}

}
