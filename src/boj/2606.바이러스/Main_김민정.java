import java.io.*;
import java.util.*;

/*
 * 
 * 1번 컴퓨터 통해 바이러스 걸리게 되는 컴퓨터 수 출력 ! (1번 제외)
 * union find로 풀어..? -> 이걸로 풀면 뭔가 나올 것 같긴함...
 * 
 */

public class Main_김민정 {
	static int V, E;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine()); // 컴퓨터 수(정점)
		E = Integer.parseInt(br.readLine()); // 연결된 컴퓨터 쌍의 수 (간선)

		p = new int[V + 1];
		for (int i = 1; i <= V; i++)
			p[i] = i;

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			makeSet(x, y);
		} // 입력 및 집합 생성

		int cnt = 0;
		int first = findSet(1);		// 1번의 부모
		for (int i = 2; i <= V; i++) {	// 2번부터 확인
			if (findSet(i) == first)	// 1번 부모와 같으면 같은 그룹임
				cnt++;
		}

		System.out.println(cnt);	

	}// main

	// make set
	static void makeSet(int x, int y) {
		p[findSet(y)] = findSet(x);
	}

	static int findSet(int x) {
		if (x == p[x])
			return x;
		else
			return findSet(p[x]);
	}

}
