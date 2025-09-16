import java.util.ArrayList;
import java.util.Scanner;

public class Main_김용수 {
	static int[][] board = new int[9][9];
	static ArrayList<int[]> blanks = new ArrayList<>();
	static boolean solved = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i<9; i++) {
			for(int j = 0 ; j<9; j++) {
				board[i][j] = sc.nextInt();
				
				if(board[i][j]==0)
					blanks.add(new int[] {i,j});
			}
		}
		
		dfs(0);
	}//main
	
	static void dfs(int idx) {
		if(solved)
			return;
		if(idx == blanks.size()) {
			for(int i =0; i<9; i++) {
				for(int j = 0; j<9;j++) {
					System.out.print(board[i][j]+" ");
					
				}
				System.out.println();
			}
			solved = true;
			return;
		}
		
		int r = blanks.get(idx)[0];
		int c = blanks.get(idx)[1];
		
		for(int num = 1; num<=9; num++) {
			if(isValid(r,c,num)) {
				board[r][c] = num;
				dfs(idx+1);
				board[r][c] = 0;
			}
		}
		
	}// dfs
	
	static boolean isValid(int r, int c, int num) {
		for(int i=0;i<9;i++) {
			if(board[r][i] == num||board[i][c] == num)
				return false;
		}
		
		int sr = r - (r%3);
		int sc = c - (c%3);
		for(int i = sr; i<sr+3; i++	) {
			for(int j = sc; j<sc+3;j++) {
				if(board[i][j]==num)
					return false;
			}
		}
		return true;
		
	}
}
