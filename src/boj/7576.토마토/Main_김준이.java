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

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] store = new int[N][M]; // 토마토 기록
		int[][] day = new int[N][M]; // 날짜 기록

		Queue<Pos> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				store[i][j] = Integer.parseInt(st.nextToken());
				if (store[i][j] == 1) {
					q.add(new Pos(i, j)); // 익은 토마토들을 큐에 추가
					day[i][j] = 1;
				}
			}
		} // 입력 끝

		while (!q.isEmpty()) {
			Pos curr = q.poll(); // 익은 토마토를 꺼내서

			for (int k = 0; k < 4; k++) { // 주위에 안 익은 토마토를 탐색
				int nr = curr.r + dr[k];
				int nc = curr.c + dc[k];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (store[nr][nc] == 0) { // 아직 익지 않은 토마토면
					q.add(new Pos(nr, nc));
					store[nr][nc] = 1; // 토마토 익히고
					day[nr][nc] = day[curr.r][curr.c] + 1; // 날짜 하루 증가
				}
			}
		} // while - bfs

		int ans = 0;

		a: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (day[i][j] > ans) // 모두 익을 때까지 걸린 날짜를 출력
					ans = day[i][j];

				if (store[i][j] == 0) { // 토마토가 모두 익지 못하는 상황이면 -1
					System.out.println(-1);
					return;
				}
			}
		}

		System.out.println(ans - 1); // 첫날 빼기

	}// main

}
