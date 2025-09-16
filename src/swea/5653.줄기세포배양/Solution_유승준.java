package 줄기세포배양;

import java.io.*;
import java.util.*;

public class Solution_유승준 {

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static StringBuilder sb = new StringBuilder();

    // 세포 클래스
    public static class Cell {
        final int life;
        int time;
        boolean isDead;

        Cell(int life) {
            this.life = life;
            this.time = 0;
            this.isDead = false;
        }

        // 시간을 흐르게 함
        void addTime() {
            this.time++;
        }

        // 죽이는 시간 조건: time == 비활성화 시간(life) + 활성화 시간(life)
        void checkTime() {
            if (time == life * 2) {
                this.isDead = true;
            }
        }
    }

    // 메인
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 배열은 K만큼 넉넉히 선언
            Cell[][] map = new Cell[N + 2 * K][M + 2 * K];

            for (int i = K; i < K + N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = K; j < K + M; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (v != 0) {
                        map[i][j] = new Cell(v);
                    }
                }
            }

            sb.append('#').append(tc).append(' ');
            run(map, K);
        }

        System.out.print(sb);
    }

    static void run(Cell[][] map, int depth) {
        // 탈출
        if (depth == 0) {
            int cnt = 0;

            for (Cell[] cells : map) {
                for (int j = 0; j < map[0].length; j++) {
                    if (cells[j] == null) {
                        continue;
                    }

                    if (!(cells[j].isDead)) {
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append('\n');

            return;
        }

        // 이번 턴에 퍼트릴 줄기세포 좌표 리스트 (life 내림차순 정렬 필수)
        ArrayList<int[]> rc = new ArrayList<>();

        // 배열 전체 탐색 (죽지 않은 줄기세포 서치)
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == null) {
                    continue;
                }

                if (!(map[i][j].isDead)) {
                    Cell cell = map[i][j];

                    // 활성 상태이면서 최초 영향인 줄기세포만 추가
                    if (cell.time == cell.life) {
                        rc.add(new int[] {i, j});
                    }

                    cell.addTime();
                    cell.checkTime();
                }
            }
        }

        // 해당 좌표 cell.life 기준 내림차순 정렬
        rc.sort((o1, o2) -> map[o2[0]][o2[1]].life - map[o1[0]][o1[1]].life);

        // 상하좌우 영향끼치기
        while (!(rc.isEmpty())) {
            int[] cur = rc.remove(0);
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (map[nr][nc] == null) {
                    map[nr][nc] = new Cell(map[r][c].life);
                }
            }
        }

        // 재귀호출 (depth 1 감소)
        run(map, depth - 1);
    }

}