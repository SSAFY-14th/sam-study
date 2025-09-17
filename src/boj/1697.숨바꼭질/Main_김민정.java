import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS인가...
 * 선택지가 3개 => X-1, X+1, 2*X
 * 
 * 그래프를 계속 만들어 ? 동생 위치 도달할 때 까지? 각자 다 가지를 내려?
 * 그다음 동생 위치와 같아지면 그때의 level을 반환하나?
 *
 */
public class Main_김민정 {
	static int N, K; // 수빈 위치, 동생 위치
	static int[] dist = new int[100000 + 1]; // 거리 배열
	static int ans; // 정답 변수(최소 일수)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Arrays.fill(dist, -1); // -1로 초기화
		ans = 0;
		bfs(N);
		System.out.println(ans);

	}// main

	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(start);
		dist[start] = 0;

		while (!q.isEmpty()) {
			// 현재 위치 추출
			int curr = q.poll();
			// 현재 위치가 동생의 위치면 종료
			if (curr == K) {
				ans = dist[curr];
				break;
			}

			// 이동 가능한 위치
			int n1 = curr - 1;
			int n2 = curr + 1;
			int n3 = curr * 2;
			// 방문 안 했으면 큐에 넣은 후 방문 처리 + 거리 계산
			if (n1 >= 0 && n1 < 100001 && dist[n1] == -1) {
				q.add(n1);
				dist[n1] = dist[curr] + 1;
			}
			if (n2 >= 0 && n2 < 100001 && dist[n2] == -1) {
				q.add(n2);
				dist[n2] = dist[curr] + 1;
			}
			if (n3 >= 0 && n3 < 100001 && dist[n3] == -1) {
				q.add(n3);
				dist[n3] = dist[curr] + 1;
			}

		}

	}

}
