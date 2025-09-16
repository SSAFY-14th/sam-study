import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		dist = new int[100001];

		bfs(N);

		System.out.println(dist[K]);

	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			if(cur == K) return;

			int[] walking = { cur - 1, cur + 1, cur * 2 };

			for (int next : walking) {
				if (!visited[next] && next<=100000 && next >=0) {
					visited[next] = true;
					dist[next] = dist[cur] + 1;
					q.add(next);
				}
			}
		}
	}
}
