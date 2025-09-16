package 바이러스2606;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_김용수 {
	static int V,E;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt(); // 컴퓨터의 수
		E = sc.nextInt(); // 연결된 쌍의 수
		
		adjList = new ArrayList[V+1];
		for(int i = 1; i<V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		visited = new boolean[V+1];
		//초기화
		
		for(int i = 0; i<E; i++	) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			
			adjList[s].add(e);
			adjList[e].add(s);
		}//간선정보 입력
		
		ans = -1;
		dfs(1);
		System.out.println(ans);
	}//main
	
	static void dfs(int v) {
		visited[v] = true;
		ans++;
		
		for(int w:adjList[v]) {
			if(!visited[w]) 
				dfs(w);
		}
		
	}
}
