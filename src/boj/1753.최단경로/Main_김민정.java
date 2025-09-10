import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_김민정 {
	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static int V, E; // V: 정점 수, E: 간선 수
	static List<Edge>[] adj; // 인접 리스트
	static int[] dist; // 거리 저장
	static boolean[] visited; // 방문 체크
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 입력
		// 1.1 정점 개수, 간선 개수 입력
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		visited = new boolean[V + 1];

		// 1.2 시작 정점 번호 입력
		int start = Integer.parseInt(br.readLine());

		// 1.3 간선 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[u].add(new Edge(v, w));
		}

		dijkstra(start);
		
		for(int i=1; i<=V; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
	}// main

	private static void dijkstra(int start) {

		dist[start] = 0; // 시작 정점의 거리 값 0으로 갱신
		// dist 최소가 되는 정점 찾기(방문 처리 아직 X)
		for (int i = 1; i < V; i++) {
			int min = INF;
			int idx = -1;
			for (int j = 1; j <= V; j++) {
				if (!visited[j] && dist[j] < min) {
					min = dist[j];
					idx = j;
				}
			} // 최소가 되는 정점 뽑음
			
			// 선택 못함
			if (idx == -1) 	break;
			
			// 선택 완료
			visited[idx] = true;
			// 인접 노드 탐색 
			for (Edge e : adj[idx]) {
				// 방문하지 않았고, 갱신 가능하면 갱신 ㄱㄱ
				if (!visited[e.to] && dist[e.to] > dist[idx] + e.cost) {
					dist[e.to] = dist[idx] + e.cost;
				}
			}
		}
	}

}
