import java.io.*;
import java.util.*;

public class Main_유승준 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[100001];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == K) {
                System.out.print(arr[K]);
                break;
            }

            if (cur - 1 >= 0 && arr[cur - 1] == 0) {
                arr[cur - 1] = arr[cur] + 1;
                q.offer(cur - 1);
            }

            if (cur + 1 <= 100000 && arr[cur + 1] == 0) {
                arr[cur + 1] = arr[cur] + 1;
                q.offer(cur + 1);
            }

            if (cur * 2 <= 100000 && arr[cur * 2] == 0) {
                arr[cur * 2] = arr[cur] + 1;
                q.offer(cur * 2);
            }
        }
    }
}
