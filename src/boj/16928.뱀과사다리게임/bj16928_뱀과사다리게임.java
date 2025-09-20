import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj16928_뱀과사다리게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new Edge[101]; // 보드판
		visit = new boolean[101]; // 방문배열

		// 사다리
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from] = new Edge(to);
		}
		// 뱀
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from] = new Edge(to);
		}

		cnt = 0;
		// bfs 수행
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		while (!q.isEmpty()) {
			int size = q.size();
			
			// 주사위 1회 굴린 후 작업
			cnt++; 
			for (int i = 0; i < size; i++) {
				int curr = q.poll();

				for (int idx = 1; idx <= 6; idx++) {
					int ni = curr + idx;

					if (ni > 100) continue; // 범위 밖

					while (arr[ni] != null) ni = arr[ni].to; // 사다리 타고 이동
					
					if (visit[ni]) continue; // 최종 도착지가 기존에 방문 => 패스

					if (ni == 100) { // 도착
						System.out.println(cnt);
						return;
					}
					// 도착지가 아니면
					visit[ni] = true;
					q.add(ni);
				}
			}
		}
	}

	static final int end = 100;
	static int cnt;

	static boolean[] visit;
	static Edge[] arr;

	static class Edge {
		int to;

		public Edge(int to) {
			this.to = to;
		}
	}
}
