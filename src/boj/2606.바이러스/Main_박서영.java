import java.io.*;
import java.util.*;

public class Main_박서영 {
	
	static int N, M; //컴퓨터 수, 컴퓨터 쌍의 수
	static List<Integer>[] adj; //인접리스트
	static boolean[] visited; //방문배열
	static int count; //바이러스에 걸린 컴퓨터 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//무향
			adj[a].add(b);
			adj[b].add(a);
		}
		
		bfs();
		
		System.out.println(count);
	}
	
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[N+1];
		q.add(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			//큐에서 꺼낸 노드의 인접노드들 방문안했으면 방문처리하고 큐에 넣고 카운트 올리기 
			for (int i : adj[curr]) {
				if(!visited[i]) {
					visited[i] = true;
					q.add(i);
					count++;
				}	
			}
		}
	}
}
