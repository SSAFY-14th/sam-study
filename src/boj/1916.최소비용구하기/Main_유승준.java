import java.io.*;
import java.util.*;

/*
 * 도시 수 N 1,000
 * 버스 수 M 100,000
 * 비용 100,000
 */

public class Main_유승준 {
	static int N, M;
	static int[] dist;
	static PriorityQueue<Node> pq;
	static ArrayList<ArrayList<Node>> graph;
	
	static class Node implements Comparable<Node> {
		int from;
		int cost;
		
		public Node(int from, int cost) {
			super();
			this.from = from;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// ㅊㄱㅎ
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq = new PriorityQueue<>();
		
		graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}
		
		// ㅇㅈㄹㅅㅌ
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, cost));
		}
		
		// ㅅㅈㅈ, ㄲㅈ
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tgf = Integer.parseInt(st.nextToken());
		int tgt = Integer.parseInt(st.nextToken());
		
		// ㄷㅇㅅㅌㄹ
		pq.add(new Node(tgf, 0));
		dist[tgf] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int from = cur.from;
			int cost = cur.cost;
			
			if (cost > dist[from]) continue;
			if (from == tgt) break;

			for (int i = 0; i < graph.get(from).size(); i++) {
				
				Node nn = graph.get(from).get(i);
				
				if (cost + nn.cost < dist[nn.from]) {
					pq.add(new Node(nn.from, cost + nn.cost));
					dist[nn.from] = cost + nn.cost;
				}
			}
		}
		
		// ㅊㄹ
		System.out.println(dist[tgt]);
	}
}
