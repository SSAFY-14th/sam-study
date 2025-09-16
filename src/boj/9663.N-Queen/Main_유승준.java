import java.io.*;

public class Main_유승준 {
	static int N, ans;
	static boolean[] col;
	static boolean[] daig1;
	static boolean[] daig2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		ans = 0;
		col = new boolean[N];
		daig1 = new boolean[N * 2 - 1];
		daig2 = new boolean[N * 2 - 1];
		
		solve(0);
		System.out.println(ans);
	}
	
	static void solve(int r) {
		if (r == N) {
			ans++;
			return;
		}
		
		for (int c = 0; c < N; c++) {
			// (N - 1)은 대각선 인덱스 보정
			if (!col[c] && !daig1[r + c] && !daig2[r - c + (N - 1)]) {
				col[c] = true;
				daig1[r + c] = true;
				daig2[r - c + (N - 1)] = true;
				
				solve(r + 1);
				
				col[c] = false;
				daig1[r + c] = false;
				daig2[r - c + (N - 1)] = false;
			}
		}
	}
}
