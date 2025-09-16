import java.io.*;
import java.util.*;

public class Main_박서영 {
	
	static int N, M, V;
	static List<Integer>[] adj; //인접리스트
	static boolean[] visited; //방문배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		//인접리스트 생성
		adj = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		//인접리스트 입력받기
		for (int i = 0; i < M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			//양방향 간선
			adj[a].add(b);
			adj[b].add(a);
		}
		
		//인접리스트 오름차순으로 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(adj[i]);
		}
		
		visited = new boolean[N+1];
		dfs(V);
		
		System.out.println();
		
		visited = new boolean[N+1];
		bfs();
	}
	
	static void dfs(int curr) {
		//방문처리 후 출력
		visited[curr] = true;
		System.out.print(curr + " ");
		
		//인접리스트 돌면서 방문처리 안 된 것들 재귀돌리기
		for (int i : adj[curr]) {
			if(!visited[i]) dfs(i);
		}
	}
	
	static void bfs() {
		//큐 생성 후 시작정점 방문처리 후 큐에 넣기
		Queue<Integer> q = new ArrayDeque<>();
		visited[V] = true;
		q.add(V);
		
		//큐가 빌 때까지 while문 돌리기
		while(!q.isEmpty()) {
			//큐에서 하나 뽑아서 출력
			int curr = q.poll();
			System.out.print(curr + " ");
			
			//인접리스트 돌면서 방문처리 안 된 것들 방문처리하고 큐에 넣기
			for (int i : adj[curr]) {
				if(!visited[i]) {
					visited[i] = true;
					q.add(i);
				}
			}
		}
	}
}