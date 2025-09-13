import java.io.*;
import java.util.*;

/*
 * 스도쿠 문제 (Java: 292ms)
 * 빈 칸은 0으로 입력받음, 빈 칸을 정답으로 채워 출력하기
 * -> 효율적인 탐색을 위해 입력과 동시에 체크
 */

public class Main_유승준 {
    static int[][] map = new int[9][9];
    static boolean[][] rowCheck = new boolean[9][10];
    static boolean[][] colCheck = new boolean[9][10];
    static boolean[][] squareCheck = new boolean[9][10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int v = Integer.parseInt(st.nextToken());
                map[i][j] = v;
                rowCheck[i][v] = true;
                colCheck[j][v] = true;
                squareCheck[(i / 3) * 3 + j / 3][v] = true;
            }
        }

        solve(0);
    }

    static void solve(int depth) {
        if (depth == 81) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            System.exit(0);
        }

        int r = depth / 9;
        int c = depth % 9;

        if (map[r][c] != 0) {
            solve(depth + 1);
        } else {
            for (int num = 1; num <= 9; num++) {
                if (rowCheck[r][num] || colCheck[c][num] || squareCheck[(r / 3) * 3 + c / 3][num]) {
                    continue;
                }

                map[r][c] = num;
                rowCheck[r][num] = true;
                colCheck[c][num] = true;
                squareCheck[(r / 3) * 3 + c / 3][num] = true;

                solve(depth + 1);

                map[r][c] = 0;
                rowCheck[r][num] = false;
                colCheck[c][num] = false;
                squareCheck[(r / 3) * 3 + c / 3][num] = false;
            }
        }
    }
}
