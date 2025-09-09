package 벽돌_깨기;

import java.io.*;
import java.util.*;

public class Solution_유승준 {

    static int N; // 구슬의 수 (타격 횟수)
    static int W; // 열의 수 (첫 타격 범위)
    static int H; // 행의 수 (깊이 탐색 횟수)
    static int min; // 남은 벽돌의 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= T; i++) {
            // 초기화
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;

            int[][] map = new int[H][W];

            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 정답 추가
            sb.append('#').append(i).append(' ');
            dfs(map, N);

            if (min == Integer.MAX_VALUE) {
                min = 0;
            }

            sb.append(min).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int[][] map, int depth) {
        // 탈출 구문 (타격 횟수 소진)
        if (depth == 0) {
            int cnt = 0;
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    if (map[r][c] != 0) {
                        cnt++;
                    }
                }
            }

            min = Math.min(min, cnt);
            return;
        }

        // 타격 지점 선정: 각 열의 최고점 (최고 행)
        for (int c = 0; c < W; c++) {
            for (int r = 0; r < H; r++) {
                if (map[r][c] != 0) {
                    // 새 배열로 복사
                    int[][] newMap = new int[H][W];

                    for (int i = 0; i < H; i++) {
                        for (int j = 0; j <W; j++) {
                            newMap[i][j] = map[i][j];
                        }
                    }

                    // 방문 배열
                    int[][] vst = new int[H][W];

                    // 한번에 부수기 위한 큐
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{r, c});
                    vst[r][c] = 1;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int curR = cur[0];
                        int curC = cur[1];
                        int curPow = map[curR][curC];

                        // 뽑은 타격 지점은 0으로 수정
                        newMap[curR][curC] = 0;

                        // 뽑은 타격 지점 주변을 영향력 만큼 탐색 후, 신규 타격 지점 발견 시 큐에 추가
                        for (int i = 0; i < curPow; i++) {
                            if (curR - i >= 0 && vst[curR - i][curC] == 0 && map[curR - i][curC] != 0) {
                                q.add(new int[]{curR - i, curC});
                                vst[curR - i][curC] = 1;
                            }
                            if (curR + i < H && vst[curR + i][curC] == 0 && map[curR + i][curC] != 0) {
                                q.add(new int[]{curR + i, curC});
                                vst[curR + i][curC] = 1;
                            }
                            if (curC - i >= 0 && vst[curR][curC - i] == 0 && map[curR][curC - i] != 0) {
                                q.add(new int[]{curR, curC - i});
                                vst[curR][curC - i] = 1;
                            }
                            if (curC + i < W && vst[curR][curC + i] == 0 && map[curR][curC + i] != 0) {
                                q.add(new int[]{curR, curC + i});
                                vst[curR][curC + i] = 1;
                            }
                        }
                    }

                    // 큐가 비어서 반복문이 종료되었다는 건, 1회 타격 완료를 의미
                    int[][] refreshedMap = refresh(newMap);

                    // 재정렬된 map 재귀 호출 (depth 1 감소)
                    dfs(refreshedMap, depth - 1);

                    break;
                }
            }
        }
    }

    // 빈 공간 재정렬 메서드
    static int[][] refresh(int[][] map) {
        int[][] newMap = new int[H][W];

        for (int c = 0; c < W; c++) {
            int newIdx = H - 1; // newMap 행 인덱스

            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    newMap[newIdx--][c] = map[r][c];
                }
            }
        }

        return newMap;
    }

}