import java.io.*;

public class Main_박서영 {
	
	static int N, count; //퀸 N개, 경우의 수
	static int[] queen; //각 행의 퀸 위치 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		queen = new int[N];
		
		findQueen(0);
		
		System.out.println(count);
	}

	//각 열마다 조건을 만족하는 퀸 자리를 찾아서 설정하고, 다 설정한 경우 카운트 올림 
	static void findQueen(int n) {
		if(n == N) {
			count++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(isSafe(n, i)) {
				queen[n] = i;
				findQueen(n+1);
			}
		}
	}
	
	//이전에 놓인 퀸과 같은 열일 경우 또는 대각선 자리에 있는 경우 false 반환
	static boolean isSafe(int n, int col) {
		for (int i = 0; i < n; i++) {
			//이전 퀸의 열과 현재의 열 비교
			if(queen[i] == col)
				return false;
			//행의 차와 열의 차가 같으면 대각선 위치에 존재함
			else if(Math.abs(n - i) == Math.abs(queen[i] - col))
				return false;
		}
		return true;
	}
}
