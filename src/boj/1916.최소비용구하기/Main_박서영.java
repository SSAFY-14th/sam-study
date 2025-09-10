
import java.io.*;
import java.util.*;

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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); //정점개수 
		int m = Integer.parseInt(br.readLine()); //간선개수
		
		//인접리스트
		List<Edge>[] adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());  
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adj[from].add(new Edge(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int last = Integer.parseInt(st.nextToken());
		
		//최소비용 배열 
		int[] minCost = new int[n+1];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		minCost[start] = 0;
		
		//우선순위큐에 시작점 넣기
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		
		//최소비용경로에 포함되는 도시 배열
		int[] city = new int[n+1]; 
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			//꺼낸 정점의 cost가 배열에 저장된 값보다 크면 이미 방문한 곳이므로 넘어감
			if(minCost[e.to] < e.cost) continue;
			
			//인접 정점들 확인 후 갱신하고 큐에 넣음
			for (Edge edge : adj[e.to]) {
				if(minCost[edge.to] > minCost[e.to] + edge.cost) {
					minCost[edge.to] = minCost[e.to] + edge.cost;
					pq.add(new Edge(edge.to, minCost[edge.to]));
					//city[현재정점] = 이전정점
					city[edge.to] = e.to;
				}
			}
		}
		
		//city에 저장된 값 꺼내서 리스트에 저장
		List<Integer> list = new ArrayList<>();
		int now = last;
		while(now != 0) {
			list.add(now);
			if(now == start) break;
			now = city[now];
		}
		
		//결과 출력
		System.out.println(minCost[last]);
		System.out.println(list.size());
		for (int i = list.size()-1; i >= 0; i--) {
			System.out.print(list.get(i) + " ");
		}
	}
}

