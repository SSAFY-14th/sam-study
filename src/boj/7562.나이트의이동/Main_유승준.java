import java.io.*;
import java.util.*;

/*
 * 8방향 델타 탐색
 */

public class Main_유승준 {
	static int[] dr = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dc = {-1, 1, -2, 2, -2, 2, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;		
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] vst = new int[N][N];
			
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int tr = Integer.parseInt(st.nextToken());
			int tc = Integer.parseInt(st.nextToken());
			
			Queue<int[]> q = new ArrayDeque<>();
			q.add(new int[] {sr, sc});
			vst[sr][sc] = 1;
			
			int ans = 0;
			
			while (!q.isEmpty()) {
				int rp = q.size();
				
				for (int i = 0; i < rp; i++) {
					int[] cur = q.poll();
					int r = cur[0];
					int c = cur[1];
					
					if (r == tr && c == tc) {
						sb.append(ans).append('\n');
						break;
					}
					
					for (int j = 0; j < 8; j++) {
						int nr = r + dr[j];
						int nc = c + dc[j];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= N || vst[nr][nc] == 1) {
							continue;
						}
						
						q.add(new int[] {nr, nc});
						vst[nr][nc] = 1;
					}
				}
				
				ans++;
			}
		}
		
		System.out.print(sb);
	}
}
