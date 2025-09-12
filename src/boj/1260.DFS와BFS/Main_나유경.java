
import java.util.*;
import java.io.*;

public class Main_나유경 {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int N, M, V;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		// 작은 것을 먼저 방문 하기 위해 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph.get(i));
		}

		// DFS
		visited = new boolean[N + 1];
		dfs(V);
		
		System.out.println(); // 줄바꿈

		// BFS
		visited = new boolean[N + 1];
		bfs(V);

	}

	private static void dfs(int node) {
		visited[node] = true;
		System.out.print(node + " ");

		for (int next : graph.get(node)) { 
			if (!visited[next]) {
				dfs(next);
			}

		}
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur + " ");

			for (int next : graph.get(cur)) {
				if (!visited[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
		}
	}
}
