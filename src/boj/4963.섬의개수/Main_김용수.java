import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int w, h;
	static int[][] map;
	static ArrayList<int[]> islands;
	static boolean[][] visited;
	static int cnt;
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			w = sc.nextInt();
			h = sc.nextInt();
			
			if(w==0)
				return;
			map = new int[h][w];
			islands = new ArrayList<>();
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						islands.add(new int[] { i, j });
					}
				}
			}

			cnt = 0;
			
			for (int i = 0; i<islands.size();i++) {
				int r = islands.get(i)[0];
				int c = islands.get(i)[1];
				if (!visited[r][c]) {
					bfs(r, c);
					cnt++;
				}
			}
			
			System.out.println(cnt);

		} // tc
	}// main

	static void bfs(int r,int c) {
		Queue<int[]> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cr = curr[0];
			int cc = curr[1];
			
			for(int k=0; k<8; k++) {
				int nr = cr + dr[k];
				int nc = cc + dc[k];
				
				if(nr<0||nc<0||nr>=h||nc>=w)
					continue;
				
				if(!visited[nr][nc]&&map[nr][nc]==1) {
					visited[nr][nc] = true;
					q.add(new int[] {nr,nc});
				}
			}
			
			
			
		}
	}
}
