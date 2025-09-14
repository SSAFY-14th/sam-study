import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] box;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		box = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				result = Math.max(result, box[i][j]);
			}

		}
		if (result == 1) {
			System.out.println(0);
		} else {
			System.out.println(result - 1);
		}

	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();

		// 시작점 여러 개, 모든 1을 동시에 시작점으로 하기
		for (int k = 0; k < N; k++) {
			for (int l = 0; l < M; l++) {
				if (box[k][l] == 1) {
					q.add(new int[] { k,l });
				}
			}
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && box[nr][nc] == 0) {
					box[nr][nc] = box[r][c] + 1;
					q.add(new int[] { nr, nc });

				}
			}

		}
	}
}
