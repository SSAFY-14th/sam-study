
import java.util.Scanner;

public class Main_김용수 {
	static int N;
	static int[] col;
	static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		col = new int[N];
		cnt = 0;

		dfs(0); // 행 번호
		System.out.println(cnt);
	}// main

	static void dfs(int row) { // 행 번호 입력
		if (row == N) { // 행이 끝에 도달하면 cnt 하고 return
			cnt++;
			return;
		}

		for (int j = 0; j < N; j++) { // 각 행에 대해 열을 순회
			col[row] = j; // 이번행에 는 j열에 둬보자

			if (isP(row)) { // 둘수있으면
				dfs(row + 1); // 한행 내려가기
			}
		}
	}

	static boolean isP(int row) {
		for (int i = 0; i < row; i++) {
			if (col[i] == col[row]) // 한번이라도 두려는곳과 같은열에 이미 뒀거나 
				return false;
			if (Math.abs(row - i) == Math.abs(col[row] - col[i]))
				return false;
		}
		return true;
	}
}
