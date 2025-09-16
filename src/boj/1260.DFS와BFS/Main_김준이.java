import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] adjList;
	static int N, M, V;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점
		M = Integer.parseInt(st.nextToken()); // 간선

		V = Integer.parseInt(st.nextToken()); // 탐색 시작점

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 무향
			adjList[a].add(b);
			adjList[b].add(a);
		} // 입력 끝

		// 정점 번호가 작은 것을 먼저 방문
		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i]);
		}

		visited = new boolean[N + 1];
		dfs(V);
		System.out.println();
		visited = new boolean[N + 1];
		bfs(V);

	}// main

	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");

		for (int tmp : adjList[v]) {
			if (!visited[tmp])
				dfs(tmp);
		}
	}// dfs

	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();
			System.out.print(curr + " ");
			// 인접 정점 탐색
			for (int i = 0; i < adjList[curr].size(); i++) {
				int tmp = adjList[curr].get(i);
				if (!visited[tmp]) {
					q.add(tmp);
					visited[tmp] = true;
				}
			}
		} // while

	}// bfs

}