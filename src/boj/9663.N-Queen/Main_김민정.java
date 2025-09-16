import java.io.*;
import java.util.*;

public class Main_김민정 {
	static int N;
	static boolean[] col; // 열 방문 체크
	static boolean[] diag1; // / 방향 대각선 체크
	static boolean[] diag2; // \ 방향 대각선 체크
	static int count; // 가능한 경우의 수 (정답)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		col = new boolean[N];
		diag1 = new boolean[2 * N - 1];
		diag2 = new boolean[2 * N - 1];

		count = 0;
		dfs(0);
		System.out.println(count);

	}// main

	static void dfs(int row) {
		// 종료 조건
		if (row == N) { // 마지막 행 초과하면
			count++;
			return;
		}

		for (int c = 0; c < N; c++) {
			int d1 = row + c;
			int d2 = row - c + N - 1; // 음수 인덱스 방지

			if (col[c] || diag1[d1] || diag2[d2]) { // 이미 방문했다면 넘어감
				continue;
			}

			// 방문하지 않았다면 방문처리
			col[c] = true;
			diag1[d1] = true;
			diag2[d2] = true;
			// 다음 행으로 넘어감
			dfs(row + 1);
			// 방문 처리 원복
			col[c] = false;
			diag1[d1] = false;
			diag2[d2] = false;
		}

	}
}
