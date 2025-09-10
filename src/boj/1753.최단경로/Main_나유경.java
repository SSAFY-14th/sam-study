import java.io.*;
import java.util.*;

public class Main_나유경 {
    // 그래프의 간선을 저장하는 클래스
    // to: 도착 정점 번호, cost: 현재까지의 거리(가중치)
    // 우선순위 큐에서 비용이 작은 것 부터 꺼내도록 하자

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static final int INF = Integer.MAX_VALUE; // 도달 불가
    static int V, E, K; // V: 정점 개수, E: 간선 개수, K: 시작 정점
    static List<Edge>[] adjList; // 인접 리스트 -> 그래프 저장용임
    static int[] dist; // 최단거리를 저장할 배열임

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 정점 개수, 간선 개수, 시작 정점 입력 받 자
        V = sc.nextInt();
        E = sc.nextInt();
        K = sc.nextInt();

        // 인접 리스트 초기화 하자
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 처음에는 모두 무한대로 설정함
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        // 간선 정보 입력받자
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt(); // 시작정점
            int v = sc.nextInt(); // 도착정점
            int w = sc.nextInt(); // 가중치
            // u -> v로 가는 간선 추가
            // 방향 그래프라서 한쪽만 저장함
            adjList[u].add(new Edge(v, w));
        }

        // 다익스트라 ㄱㄱ
        dijkstra(K);

        // 출력
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF)
                System.out.println("INF"); // 갈 수 없음
            else
                System.out.println(dist[i]);
        }
    }

    // 다익스트라
    private static void dijkstra(int start) {
        // 항상 가장 짧은 거리부터 꺼내기
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 이미 최단 거리가 확정 된 정점은 방문체크하셈
        boolean[] visited = new boolean[V + 1];

        // 시작 정점 까지의 거리는 0임
        // 큐에 (시작정점, 0) 추가하자
        dist[start] = 0;
        pq.add(new Edge(start, 0));

        // 큐가 빌 때까지 반복
        while (!pq.isEmpty()) {
            // 현재 비용이 가장 작은 정점을 꺼냄
            Edge curr = pq.poll();

            // 이미 방문한 정점이면
            if (visited[curr.to])
                continue; // 무시
            visited[curr.to] = true;

            // 현재 정점에서 이동할 수 있는 모든 인접 정점 확인
            for (Edge next : adjList[curr.to]) {
                // 더 짧은 거리 발견하면 dist[] 갱신
                if (dist[next.to] > dist[curr.to] + next.cost) {
                    dist[next.to] = dist[curr.to] + next.cost;
                    // 갱신된 정점을 큐에 다시 넣음
                    pq.add(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }
}