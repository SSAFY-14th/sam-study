import java.io.*;
import java.util.*;

/*
 * 1. 정점 개수 V, 간선 개수 E
 * 2. 시작 정점 K
 * 3-E. 간선 u, 간선 v, 가중치 w
 *
 * 시작점 기준으로 각 정점까지의 최단 경로 출력
 * 편의를 위해 제로 베이스 -> 원 베이스
 */

class Main_유승준 {
    static PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    static ArrayList<int[]>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 최단 거리 테이블 초기화
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 인접 리스트 초기화
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 2.
        int K = Integer.parseInt(br.readLine());
        dist[K] = 0;
        minHeap.add(new int[] {dist[K], K});

        // 3.
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[] {w, v});
        }

        // 다익스트라
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int curDist = cur[0];
            int curNo = cur[1];

            // 이미 처리된 노드 스킵
            if (curDist > dist[curNo]) {
                continue;
            }

            // 현재 정점과 연결된 다른 인접 정점 전부 확인
            for (int[] nbNo : graph[curNo]) {
                int tmpDist = nbNo[0];
                int tmpNo = nbNo[1];

                // 현재 정점을 거쳐 다른 정점으로 이동하는 거리가 더 짧은 경우
                if (dist[curNo] + tmpDist < dist[tmpNo]) {
                    dist[tmpNo] = dist[curNo] + tmpDist;
                    // 최단 거리 갱신 후, 갱신된 정보 추가
                    minHeap.add(new int[] {dist[tmpNo], tmpNo});
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
}
