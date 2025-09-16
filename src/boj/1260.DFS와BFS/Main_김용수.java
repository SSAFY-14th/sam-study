
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int V, E;
	static ArrayList<Integer>[] A;
	static boolean[] visited;
	static int start;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();
		start = sc.nextInt();

		A = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) {
			A[i] = new ArrayList<>();
		}// 인접리스트 초기화
		
		for(int i = 0; i<E; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			
			A[s].add(e);
			A[e].add(s);
		}//입력
		
		for(int i = 1; i<V+1; i++) {
			Collections.sort(A[i]);
		}
		
		visited = new boolean[V+1];
		dfs(start);
		System.out.println();
		
		visited = new boolean[V+1];
		bfs(start);
		System.out.println();
		
	}//main
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v+" ");
		
		for(int w:A[v]) {
			if(!visited[w]) {
				dfs(w);
			}
		}
	}//dfs
	
	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			System.out.print(curr +" ");
			
			for(int w:A[curr]) {
				if(!visited[w]) {
					q.add(w);
					visited[w] = true;
				}
			}
		}
		
	}//bfs
}
