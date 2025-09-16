import java.io.*;
import java.util.*;

/*
 * 17:27
 */

public class Main_유승준 {
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	static Queue<int[]> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	
	static int W, H, ans;
	static int[][] map, vst;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			vst = new int[H][W];
			
			if (W == 0 && H == 0) {
				break;
			}
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					bfs(i, j);
				}
			}
			
			sb.append(ans).append('\n');
		}
		
		System.out.print(sb);
	}
	
	static void bfs(int r, int c) {
		q.clear();
		
		if (map[r][c] == 0 || vst[r][c] == 1) {
			return;
		}
		
		q.add(new int[] {r, c});
		vst[r][c] = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			for (int i = 0; i < 8; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if (nr < 0 || nr >= H || nc < 0 || nc >= W || vst[nr][nc] == 1 || map[nr][nc] == 0) {
					continue;
				}
				
				q.add(new int[] {nr, nc});
				vst[nr][nc] = 1;
			}
		}
		
		ans++;
	}
}
