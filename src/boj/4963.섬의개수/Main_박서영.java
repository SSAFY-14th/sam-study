import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int w, h, count; //너비, 높이, 섬의 개수
	static int[][] map; //지도 배열
	static boolean[][] visited; //방문체크 배열
	static int[] dx = {-1,-1,-1,0,1,1,1,0}; //가로, 세로, 대각선 
	static int[] dy = {-1,0,1,1,1,0,-1,-1};
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w == 0) break; //0 입력받으면 while문 탈출
			
			map = new int[h][w];
			visited = new boolean[h][w];
			count = 0;
			
			//지도 입력받기
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			islandNum(0, 0);
			
			System.out.println(count);
		}
		
	}
	
	//지도 돌면서 방문안한 섬이면 bfs 시작하고 섬 개수 +1
	static void islandNum(int x, int y) {
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			
			for (int i = 0; i < dx.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
				if(map[nx][ny] == 0) continue;
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}
	}
}
