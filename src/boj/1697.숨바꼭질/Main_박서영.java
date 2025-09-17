import java.io.*;
import java.util.*;

public class Main_박서영 {
	
	static int N, K; //수빈위치, 동생위치
	static int[] move; //시간 저장 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		move = new int[100001]; //N과 K의 범위만큼 크기 지정
		
		if(N >= K) // K보다 N이 크면 무조건 -1로 이동해야 하므로 N-K만큼 시간 소요됨
			System.out.println(N - K);
		else {
			bfs();
			System.out.println(move[K]-1); //처음 시작을 1로 했으므로 -1 해주기
		}
	}
	
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(N);
		move[N] = 1; //방문체크해야하므로 1로 설정
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			int nx = curr + 1; //+1일때
			if(nx >= 0 && nx < 100001 && move[nx] == 0) {
				move[nx] = move[curr] + 1;
				q.add(nx);
			}
			
			nx = curr - 1; //-1일때
			if(nx >= 0 && nx < 100001 && move[nx] == 0) {
				move[nx] = move[curr] + 1;
				q.add(nx);
			}
			
			nx = curr*2; //x2일때
			if(nx >= 0 && nx < 100001 && move[nx] ==  0) {
				move[nx] = move[curr] + 1;
				q.add(nx);
			}
			
			if(move[K] != 0) break; //동생에게 도착하면 while문 중단
		}
	}
}
