import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj18111_마인크래프트 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 땅고르기 작업 -> 최소 시간
//		2초: 좌표 (i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다.
//		1초: 인벤토리에서 블록 하나를 꺼내어 좌표 (i, j)의 가장 위에 있는 블록 위에 놓는다.	

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		B = Integer.parseInt(st.nextToken()); // 초기 인벤토리

		cnt = 0;

		int[] counting = new int[257];
		int mean = 0;

		// 배열 완성
		arr = new int[N][M];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				counting[arr[i][j]]++;
                min = Math.min(min,arr[i][j]);
                max = Math.max(max,arr[i][j]);
			}
		}

		int cnt = Integer.MAX_VALUE;
        int height = 0;

        for (int i = min; i <= max; i++) {
            int dig = 0; // 깎을거
            int amass = 0; // 쌓을거

            // counting 배열로 계산 => dig와 amass 산출
            for (int j = 0; j <= 256; j++) {
                int c = counting[j];
                if (c == 0) continue;
                if (j > i) {
                	dig += (j - i) * c;
                }
                else if (j < i) {
                	amass += (i - j) * c;
                }
            }

            if (dig + B < amass) continue; // 블록이 부족함.

            int time = dig * 2 + amass; // 최종적으로 소요되는 시간
            if (time < cnt || (time == cnt && i > height)) {
                cnt = time;
                height = i;
            }
        }

        sb.append(cnt).append(" ").append(height);
        System.out.println(sb);
    }

	static int N, M;
	static int B;
	static int cnt;
	static int[][] arr;
}
