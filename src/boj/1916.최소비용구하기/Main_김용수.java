
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	// 최소비용 우선순위큐 
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

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static List<Edge>[] adjList;
    static int[] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 도시 개수 (정점)
        M = sc.nextInt(); // 버스 개수 (간선)

        adjList = new ArrayList[N + 1]; // 1번부터 N번까지 사용
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        // 간선 입력 
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt(); // 출발 도시
            int v = sc.nextInt(); // 도착 도시
            int w = sc.nextInt(); // 비용
            adjList[u].add(new Edge(v, w));
        }

        int start = sc.nextInt(); // 출발점
        int end = sc.nextInt();   // 도착점

        dijkstra(start);

        System.out.println(dist[end]);
    }

    //다익 
    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        
        //시작정점 
        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (visited[curr.to]) continue;
            visited[curr.to] = true;

            for (Edge next : adjList[curr.to]) {
                if (dist[next.to] > dist[curr.to] + next.cost) {
                    dist[next.to] = dist[curr.to] + next.cost;
                    pq.add(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }
} // 메인 
