import java.io.*;
import java.util.*;

public class Main_나유경 {

	static int N, M, x, y, u, v;
	static int[] board;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[101];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			board[x] = y; // x칸에 도착하면 y로 이동
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			board[u] = v;// u칸에 도착하면 v로 이동

		}

		visited = new boolean[101];
		System.out.println(bfs());

	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 1, 0 }); // 현위치, 주사위 횟수
		visited[1] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int pos = cur[0];
			int cnt = cur[1];

			if (pos == 100)
				return cnt;

			for (int dice = 1; dice <= 6; dice++) {
				int next = pos + dice;

				if (next > 100)
					continue;

				if (board[next] != 0) // 사다리/뱀 있으면 순간이동 
					next = board[next];

				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] { next, cnt + 1 });
				}

			}
		}
		return -1;
	}
}
