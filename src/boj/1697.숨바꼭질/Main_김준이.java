import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] pos;
	static int N, K;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		pos = new int[100001];
		Arrays.fill(pos, Integer.MAX_VALUE);

		find();

		System.out.println(ans);
	}// main

	static void find() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N); // 수빈이 시작 위치
		pos[N] = 0;

		while (!q.isEmpty()) {
			int curr = q.poll();

			// 동생을 찾으면 그만두기
			if (curr == K) {
				ans = pos[curr];
				return;
			}

			// 순간이동 했을 때 도착할 위치의 기존 시간 < 지금 이동해서 갔을 때의 시간
			if (curr * 2 <= 100000 && pos[curr * 2] > pos[curr] + 1) {
				pos[curr * 2] = pos[curr] + 1;
				q.add(curr * 2);
			}

			// 전진
			if (curr + 1 <= 100000 && pos[curr + 1] > pos[curr] + 1) {
				pos[curr + 1] = pos[curr] + 1;
				q.add(curr + 1);
			}

			// 후진
			if (curr - 1 >= 0 && pos[curr - 1] > pos[curr] + 1) {
				pos[curr - 1] = pos[curr] + 1;
				q.add(curr - 1);
			}
		}

	}// find
}
