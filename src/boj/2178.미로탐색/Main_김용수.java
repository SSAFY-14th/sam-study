
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		} // 입력 받기

		System.out.println(bfs(0,0));
		
	}// main

	static int bfs(int i, int j){
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i,j,1});
		map[i][j] = 0;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			if  (curr[0] == N-1 && curr[1] == M-1)
				return curr[2];
			
			for(int k=0;k<4;k++) {
				int nr = curr[0] + dr[k];
				int nc = curr[1] + dc[k];
				
				if(nr<0||nc<0||nr>=N||nc>=M)
					continue;
				
				if(map[nr][nc]!=0) {
					map[nr][nc] = 0;
					q.add(new int[] {nr,nc,curr[2]+1});
				}
			}
			
			
			
		}
		
		return -1;
		
		
	}
}
