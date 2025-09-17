import java.io.*;
import java.util.*;

public class Main_김민정 {
	static int N, M, V; // 정점 개수, 간선 개수, 탐색 시작할 정점 번호
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		visited = new boolean[N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 양방향
			adjList[x].add(y);
			adjList[y].add(x);

		} // 입력 끝
		
		for(int i=1; i<=N; i++) {
			Collections.sort(adjList[i]);
		}
		
		dfs(V);
		visited = new boolean[N+1];	// 방문 배열 초기화 
		System.out.println();
		bfs(V);

	}// main

	// DFS
	static void dfs(int v) {
		// 방문 처리
		visited[v] = true;
		// 방문 노드 출력
		System.out.print(v + " ");
		
		// 방문한 노드의 인접 노드 탐색
		for (int i : adjList[v]) {
			if (!visited[i]) {
				dfs(i);
			}
		}
	}

	// BFS
	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<Integer>();
		// 시작노드 처리 (큐 넣고 방문 처리) 
		q.add(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			//현재 노드 추출 
			int curr = q.poll();
			// 방문 노드 출력 
			System.out.print(curr + " ");
			// 인접 노드 탐색
			for(int i : adjList[curr]) {
				if(!visited[i]) {	//방문하지 않았다면 현재 정점 큐에 넣고 방문 처리 
					q.add(i);
					visited[i] = true;
				}
			}
		}
		
	}

}