
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int N;
	static int[][] board;
	static int x,y,tx,ty;
	static int[] dx = {-2,-2,-1,-1,1,1,2,2};
	static int[] dy = {-1,1,-2,2,-2,2,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 0; tc<T; tc++) {
			N = sc.nextInt();
			board = new int[N][N];
			
			x = sc.nextInt();
			y = sc.nextInt();
			tx = sc.nextInt();
			ty = sc.nextInt();
			
			if(x==tx&&y==ty)
				System.out.println(0);
			else
				System.out.println(bfs(x,y));
			
		}//tc
		
		
	}//main
	
	static int bfs(int x,int y) {
		Queue<int[]> q = new LinkedList<>();
		board[x][y] = 1;
		q.add(new int[] {x,y,0});
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			
			if(cx==tx&&cy==ty) {
				return curr[2];
			}
			
			for(int k = 0;k<8;k++) {
				int nx = cx + dx[k];
				int ny = cy + dy[k];
				
				if(nx<0||ny<0||nx>=N||ny>=N)
					continue;
				
				if(board[nx][ny]!=1) {
					board[nx][ny] = 1;
					q.add(new int[] {nx,ny,curr[2]+1});
				}
			}
			
		}
		
		return -1;
	}
}
