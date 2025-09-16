import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer>[] adjList;
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		adjList = new ArrayList[V + 1];
		visited = new boolean[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 무향
			adjList[from].add(to);
			adjList[to].add(from);
		}

		dfs(1);

		System.out.println(cnt - 1); // 1은 제외
	}// main

	static void dfs(int v) {
		visited[v] = true;
		cnt++;
		for (int i = 0; i < adjList[v].size(); i++) {
			int curr = adjList[v].get(i);
			// 방문한 곳이면 패스
			if (!visited[curr])
				dfs(curr);
		}
	}
}
