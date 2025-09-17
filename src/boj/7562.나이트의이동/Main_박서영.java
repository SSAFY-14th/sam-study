import java.io.*;
import java.util.*;

public class Main_박서영 {
	
	static int N, startX, startY, goalX, goalY; //체스판 크기, 시작점, 도착점
	static int[][] map; //체스판 배열
	static int[][] move; //움직인 횟수 저장 배열
	static int[] dx = {-2,-2,-1,1,2,2,1,-1}; //나이트가 이동할 수 있는 위치
	static int[] dy = {-1,1,2,2,1,-1,-2,-2};
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			move = new int[N][N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			goalX = Integer.parseInt(st.nextToken());
			goalY = Integer.parseInt(st.nextToken());
			
			knight();
			
			//출발지점을 1로 설정했기 때문에 -1 해주기
			System.out.println(move[goalX][goalY] - 1);
		}
	}
	
	static void knight() {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(startX, startY));
		//출발지점 방문표시하기 위해 1로 설정
		move[startX][startY] = 1;
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			
			for (int i = 0; i < dy.length; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(move[nx][ny] == 0) {
					move[nx][ny] = move[curr.x][curr.y] + 1;
					q.add(new Point(nx, ny));
				}
			}
			//도착지점에 도착했을 시 while문 탈출
			if(move[goalX][goalY] > 0) break;
		}
	}
}
