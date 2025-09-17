import java.io.*;
import java.util.*;

public class Main_박서영 {
	
	static int N, M;
	static char[][] map;
	static int[][] move;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M]; //미로 char배열로 받기
		move = new int[N][M]; //이동한 칸의 개수 저장할 배열
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		bfs();
		
		System.out.println(move[N-1][M-1]);
	}
	
	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(0, 0));
		
		//처음 시작점도 개수에 포함이므로 1부터 시작
		move[0][0] = 1;
		
		while(!q.isEmpty()) {
			Point point = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = point.x + dr[i];
				int nc = point.y + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue; //미로 범위 벗어나면 넘기기
				if(map[nr][nc] == '0') continue; //벽이면 넘기기
				if(move[nr][nc] == 0) { //방문한적이 없는지 확인(0이면 방문안함)
					move[nr][nc] = move[point.x][point.y] + 1;
					q.add(new Point(nr, nc));
				}
			}
		}
	}
}
