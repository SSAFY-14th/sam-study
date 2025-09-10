

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_김용수 {
	// 우선순위큐 간선정보
	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) { // 비용 작은거부터 
			return Integer.compare(this.cost, o.cost);
		}
	}

	static final int INF = Integer.MAX_VALUE; // 제일 큰 수
	static int V, E, K;
	static List<Edge>[] adjList;
	static int[] dist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt(); // 정점 수
		E = sc.nextInt(); // 간선 수
		K = sc.nextInt(); // 시작 정점 (1based)

		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		} // 초기화 

		dist = new int[V + 1];
		Arrays.fill(dist, INF);

		for (int i = 0; i < E; i++) {
			int u = sc.nextInt(); // 시작 정점
			int v = sc.nextInt(); // 도착 정점
			int w = sc.nextInt(); // 가중치
			adjList[u].add(new Edge(v, w));
		}

		dijkstra(K);

		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF)
				System.out.println("INF"); // 못감
			else
				System.out.println(dist[i]);
		}
	}

	// 다익스트라 
	private static void dijkstra(int start) {
		//최소비용 우선순위 큐 
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		// 시작 정점 비용 
		dist[start] = 0;
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge curr = pq.poll(); 

			if (visited[curr.to])
				continue;
			visited[curr.to] = true;
			
			//연결된 모든 간선 확인 
			for (Edge next : adjList[curr.to]) {
				if (dist[next.to] > dist[curr.to] + next.cost) {
					dist[next.to] = dist[curr.to] + next.cost;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}
