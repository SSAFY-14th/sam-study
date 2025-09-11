import java.io.*;
import java.util.*;

public class Main {
    static Queue<int[]> q = new LinkedList<>();
    static int[][] grid;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        br.close();

        System.out.println(bfs(grid));
    }

    public static int bfs(int[][] grid) {
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                for (int j = 0; j < 4; j++) {
                    int newX = x + dx[j];
                    int newY = y + dy[j];

                    if (isValid(newX, newY, grid)) {
                        grid[newX][newY] = 1;
                        q.offer(new int[]{newX, newY});
                    }
                }
            }

            if (!q.isEmpty()) {
                level++;
            }
        }

        if (containsZero(grid)) {
            return -1;
        }
        return level;
    }

    private static boolean isValid(int newX, int newY, int[][] grid) {
        return newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 0;
    }

    private static boolean containsZero(int[][] grid) {
        for (int[] row : grid) {
            for (int i = 0; i < grid[0].length; i++) {
                if (row[i] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
