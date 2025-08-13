[알고리즘 스터디](https://www.notion.so/23f64b9928ef80dc8c06fb3f65cf4db3?pvs=21) 

### 8월 3주차 발표준비

## BFS

### 무슨 뜻일까…

- Breadth First Search
- 정의: 다차원 배열에서 각 노드를 방문할 때 **너비**를 우선으로 방문하는 알고리즘
- 그래프나 트리 등의 자료구조에서 모든 노드들을 방문하기 위한 알고리즘

### 동작 방식

- 시작 지점에서 가장 가까운 이웃 노드들을 먼저 방문하고, 그 다음 이웃의 이웃 노드들을 방문하며 탐색하는 방식
    1. 시작 지점의 노드를 큐에 넣고 **방문했다는 표시**를 남김.
    2. 큐에서 노드를 하나 꺼냄.
    3. 꺼낸 노드에 상하좌우로 이웃한 노드 중 방문하지 않은 노드를 큐에 넣고 방문 표시를 남김.
    4. 큐가 빌 때까지 2~3번을 반복.

### 주의할 점

- 방문했다는 표시 꼭 하기
    
    → 하지 않으면 무한루프에 빠질 수 있음…
    
- 이웃 노드의 범위 체크 잘 하기

### 특징

- **큐 사용**: 시작 지점에서 가까운 노드부터 순서대로 탐색하기 때문에, 선입선출 구조인 큐를 사용하는 것이 적합
- **최단 경로 보장**: 각 노드를 이동할 때 드는 비용/거리가 같거나 없을 때, 시작 지점부터 특정 노드까지의  최단 경로를 찾아줌
- 시간복잡도: 노드가 N개일 때 O(N)

### 적용해보자!

- 백준 1926. 그림(https://www.acmicpc.net/problem/1926)
- 어떻게 풀까?
    1. 도화지 전체를 한 칸씩 훑으며 1을 찾는다.
    2. 1 발견 시 그림의 개수 +1, 그 지점부터 BFS 시작해서 연결된 1을 찾는다.
    3. BFS로 탐색하면서 방문한 1의 개수(그림의 넓이)를 센다.
    4. 방문한 칸들은  방문 완료 표시를 한다.(현재 위치의 값 +1해서 2로 만들기)
    5. 그림 하나의 BFS 탐색이 끝나면 계산된 넓이를 현재까지의 최대 넓이와 비교 후 갱신한다.
- 코드
    
    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.LinkedList;
    import java.util.Queue;
    import java.util.StringTokenizer;
    
    public class Main {
        // Point 클래스: 좌표(x, y)를 편하게 다루기 위함
        static class Point {
            int x, y;
    
            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    
        static int n, m; // 세로, 가로 크기
        static int[][] paper; // 도화지 정보
        // 상, 하, 좌, 우 4방향 탐색을 위한 배열
        static int[] dx = {0, 0, 1, -1};
        static int[] dy = {1, -1, 0, 0};
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
    
            paper = new int[n][m];
    
            // 도화지 정보 입력받기
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            int pictureCount = 0; // 그림의 개수
            int maxArea = 0;      // 가장 넓은 그림의 넓이
    
            // 도화지 전체를 순회 (i: 행, j: 열)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 만약 해당 칸이 색칠되어 있다면(1)
                    if (paper[i][j] == 1) {
                        pictureCount++; // 새로운 그림 발견! 개수 증가
                        int currentArea = bfs(i, j); // BFS를 호출해 현재 그림의 넓이를 구함
                        maxArea = Math.max(maxArea, currentArea); // 최대 넓이 갱신
                    }
                }
            }
    
            // 결과 출력
            System.out.println(pictureCount);
            System.out.println(maxArea);
        }
    
        // BFS 메서드: (x, y)에서 시작해서 연결된 그림의 넓이를 반환
        public static int bfs(int x, int y) {
            Queue<Point> q = new LinkedList<>();
            
            // 시작점을 큐에 넣고 값을 2로 바꿔서 방문 처리
            q.add(new Point(x, y));
            paper[x][y] = 2;
            int area = 1; // 넓이는 1부터 시작 (시작점 포함)
    
            while (!q.isEmpty()) {
                Point current = q.poll();
    
                // 현재 위치에서 상하좌우 4방향 탐색
                for (int i = 0; i < 4; i++) {
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];
    
                    // 1. 다음 위치가 도화지 범위를 벗어나는지 확인
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    
                    // 2. 다음 위치가 색칠된 부분(1)인지 확인
                    if (paper[nx][ny] == 1) {
                        paper[nx][ny] = 2; // 방문 처리
                        q.add(new Point(nx, ny)); // 큐에 추가
                        area++; // 넓이 증가
                    }
                }
            }
            return area; // 탐색이 끝난 후, 계산된 넓이 반환
        }
    }
    ```
    

### 응용

- 시작 지점이 여러 개일 때는?
    
    → 모든 시작 지점을 큐에 넣고 BFS를 돌린다!
    

## DFS

### **BFS와 구조 매우 유사!**

### 무슨 뜻일까…

- Depth First Search
- 정의: 다차원 배열에서 각 노드를 방문할 때 **깊이**를 우선으로 방문하는 알고리즘
- 그래프나 트리 등의 자료구조에서 모든 노드들을 방문하기 위한 알고리즘

### 동작 방식

- DFS에서 큐가 스택으로 바뀐 버전
- 한 방향으로 계속 파고들다가 더 이상 갈 곳이 없으면 바로 이전 지점으로 돌아와서 다른 방향을 탐색하는 방식
    1. 시작 지점의 노드를 스택에 넣고 **방문했다는 표시**를 남김.
    2. 스택에서 노드를 하나 꺼냄.
    3. 꺼낸 노드에 상하좌우로 이웃한 노드 중 방문하지 않은 노드를 스택에 넣고 방문 표시를 남김.
    4. 스택이 빌 때까지 2~3번을 반복.

### 주의할 점

- BFS와 똑같다

### 특징

- **스택/재귀함수** 사용: 가장 마지막에 탐색한 노드부터 거슬러 올라가야 하기 때문에, 후입선출 구조인 스택을 사용하는 것이 적합
    
    → 재귀함수도 내부적으로는 콜 스택을 사용하므로 원리가 같음
    
- 연결된 모든 것을 찾는 문제를 풀 때 유용함

## BFS vs DFS

| 구분 | BFS (너비 우선 탐색) | DFS (깊이 우선 탐색) |
| --- | --- | --- |
| **탐색 방식** | 수평적, 넓게 (Level-by-level) | 수직적, 깊게 (One path at a time) |
| **자료구조** | 큐 (Queue) | 스택 (Stack) 또는 재귀 |
| **경로 탐색** | 최단 경로 보장 | 최단 경로 보장 안 됨 |
| **메모리** | 정점(노드)이 많고 넓은 그래프에서 많이 사용 | 간선(경로)이 길고 깊은 그래프에서 많이 사용 |
| **속도** | 목표가 시작점 근처에 있을 때 빠름 | 목표가 시작점에서 멀리, 깊은 곳에 있을 때 빠름 |
| **주요 사례** | 미로 찾기 최단 거리, 소셜 네트워크 친구 찾기 | 모든 경로 탐색, 사이클 찾기, 위상 정렬 |

→ 그런데? 굳이 DFS를 쓸 필요는 없다… 알고 있으면 좋지만 거의 모든 문제는 BFS로 해결이 가능하다…
