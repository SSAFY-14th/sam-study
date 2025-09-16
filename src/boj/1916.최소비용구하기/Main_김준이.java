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
	static int[] p; // 경로 복원 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		V = Integer.parseInt(br.readLine()); // 도시 = 정점
		E = Integer.parseInt(br.readLine()); // 버스 = 간선

		// 인접리스트 세팅
		adj = new ArrayList[V + 1]; // 정점이 1번부터 시작
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		dist = new int[V + 1]; // 거리
		Arrays.fill(dist, INF);

		visited = new boolean[V + 1];

		p = new int[V + 1]; // 경로 복원 위한 배열
		for (int i = 1; i < p.length; i++) {
			p[i] = i;
		}

		// 간선 정보 입력받기
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[from].add(new Edge(to, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); // 출발 지점
		int stop = Integer.parseInt(st.nextToken()); // 도착 지점

		///////////////////// 입력받기 끝

		dijkstra(start);

		// 출력준비: 최소 비용 + 도시 개수 + 경로
		int idx = stop; // 방문한 도시 번호 = p[]의 인덱스
		List<Integer> city = new ArrayList<>();	//거쳐가는 도시들 저장할 리스트
		
		while (idx != p[idx]) {
			city.add(idx);
			idx = p[idx];
		}
		city.add(start);
		
		//결과 출력
		System.out.println(dist[stop]);	//최소 비용
		System.out.println(city.size());	//도시 개수
		
		for(int i = city.size() - 1; i >= 0; i--) {
			System.out.print(city.get(i) + " ");
		}

	}// main

	// 우선순위 큐
	static void dijkstra(int start) {
		// 출발점 처리
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
					p[e.to] = curr.to;
					pq.offer(new Edge(e.to, dist[e.to]));
				}
			}

		} // end while

		// 도착점 처리

	}// 다익스트라

}
