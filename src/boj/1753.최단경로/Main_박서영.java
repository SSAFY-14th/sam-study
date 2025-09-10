import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_박서영 {
	
	static class Edge implements Comparable<Edge>{
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st1.nextToken()); //정점 개수
		int E = Integer.parseInt(st1.nextToken()); //간선 개수
		int K = Integer.parseInt(br.readLine()); //시작 정점
		
		//인접리스트
		List<Edge>[] adj = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st2.nextToken());
			int v = Integer.parseInt(st2.nextToken());
			int w = Integer.parseInt(st2.nextToken());
			
			adj[u].add(new Edge(v, w));
		}
		
		//경로값
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		//시작점 경로값 0으로 설정
		dist[K] = 0;
		
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//시작정점 우선순위큐에 넣기
		pq.add(new Edge(K, 0));
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			//방문했으면 넘어가기
			if(visited[e.to]) continue;
			
			//방문안했으면 먼저 방문처리
			visited[e.to] = true;
			//인접한 정점들 확인
			for (Edge edge : adj[e.to]) {
				//현재누적경로값에 가중치 더한값보다 인접정점까지의 누적경로값이 더 크면 대체 후 우선순위큐에 넣음
				if(dist[edge.to] > dist[e.to] + edge.cost) {
					dist[edge.to] = e.cost + edge.cost;
					pq.add(new Edge(edge.to, dist[edge.to]));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}
}
