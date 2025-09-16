
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int N,K;
	static int[] dist;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		dist = new int[1000001]; // 거리 겸 방문여부 
		Arrays.fill(dist, -1); // 방문한 적 없으면 -1
		
		System.out.println(bfs(N));
	}
	
	static int bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		dist[v] = 0; // 시작점 
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			if(curr ==K	) //도착 
				return dist[curr];
			
			int[] move = {curr-1,curr+1,curr*2};
			for(int n:move) {
				if(n<0||n>=1000001)
					continue;
				if(dist[n]==-1) {
					dist[n] = dist[curr] + 1;
					q.add(n);
				}
			}
		}
		return -1;
	}
	
}
