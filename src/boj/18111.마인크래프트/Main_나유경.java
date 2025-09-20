import java.io.*;
import java.util.*;

public class Main_나유경 {

	static int N, M, B, minH, maxH, bestTime, bestHeight, blocks;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		minH = 256;
		maxH = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minH = Math.min(minH, map[i][j]);
				maxH = Math.max(maxH, map[i][j]);
			}
		}

		bestTime = Integer.MAX_VALUE;
		bestHeight = 0;

	
		for (int h = minH; h <= maxH; h++) {
			int time = 0;
			int blocks = B;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int cur = map[i][j];
					if (cur > h) {
						// 블록 제거
						time += (cur - h) * 2;
						blocks += (cur - h);
					} else if (cur < h) {
						// 블록 추가
						time += (h - cur);
						blocks -= (h - cur);
					}
				}
			}

			if (blocks < 0)
				continue; 

			if (time < bestTime || (time == bestTime && h > bestHeight)) {
				bestTime = time;
				bestHeight = h;
			}
		}

		System.out.println(bestTime + " " + bestHeight);
	}
}
