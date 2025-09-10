import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	static final int INF = Integer.MAX_VALUE;
	static int V, E, K;
	static List<Edge>[] adj; // 인접리스트(그래프 정보 저장)
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		// 인접리스트 세팅
		adj = new ArrayList[V + 1]; // 정점이 1번부터 시작
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		visited = new boolean[V + 1];

		// 간선 정보 입력받기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[from].add(new Edge(to, cost));
		}

		///////////////////// 입력받기 끝

		dijkstra(K);

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < V + 1; i++) {
			if (dist[i] < INF) {
				sb.append(dist[i] + "\n");
			} else {
				sb.append("INF\n");
			}
		}

		System.out.println(sb);

	}// main

	// 우선순위 큐
	static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge curr = pq.poll(); // 가장 가중치가 작은 간선이 튀어나옴

			if (visited[curr.to])
				continue;

			visited[curr.to] = true;

			// 갱신
			for (Edge e : adj[curr.to]) {
				if (!visited[e.to] && dist[e.to] > dist[curr.to] + e.cost) { // 거쳐가는게 빠른 경우
					dist[e.to] = dist[curr.to] + e.cost;
					pq.offer(new Edge(e.to, dist[e.to]));
				}
			}

		} // end while
	}// 다익스트라

}
