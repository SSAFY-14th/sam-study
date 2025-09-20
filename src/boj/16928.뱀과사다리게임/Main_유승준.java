import java.io.*;
import java.util.*;

/*
 * 1차원 배열로 선언
 * 최소 탐색 횟수 구하기
 *
 * 그리디한 접근 불가능할 듯..
 * 한 번에 이동 가능한 범위 1-6 전부 뱀인 경우
 *
 * >> BFS 또는 DP
 */

public class Main_유승준 {
    static int[] arr = new int[101];
    static int[] dp = new int[101];
    static {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from] = to;
        }

        solve();
        System.out.println(dp[100]);
    }

    static void solve() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 1; i <= 6; i++) {
                int nxt = cur + i;
                if (nxt > 100) continue;

                int arv = (arr[nxt] != 0) ? arr[nxt] : nxt;
                if (dp[arv] <= dp[cur] + 1) continue;

                dp[arv] = dp[cur] + 1;
                q.add(arv);
            }
        }
    }
}