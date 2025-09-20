import java.io.*;
import java.util.*;

/*
 * 명령은 R(뒤집기)와 D(버리기)
 *
 * 1. 인덱스 포인터
 * 2. 자료구조 덱
 *
 * >> 덱을 사용한 풀이
 */

public class Main_유승준 {
    static String cmd;
    static Deque<String> dq = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 초기화
            dq.clear();
            cmd = br.readLine();

            // 입력값 버리기
            br.readLine();

            // 배열 변환
            String ori = br.readLine();
            String tmp = ori.substring(1, ori.length() - 1);
            String[] arr = tmp.split(",");

            // 덱에 추가
            for (int i = 0; i < arr.length; i++) {
                dq.add(arr[i]);
            }

            // 스플릿 예외 처리
            if (tmp.isEmpty()) {
                dq.clear();
            }

            // 풀이 함수
            solve();
        }

        // 최종 출력
        System.out.print(sb);
    }

    static void solve() {
        boolean isRev = false;

        // 명령어 처리
        for (int i = 0; i < cmd.length(); i++) {
            char cur = cmd.charAt(i);

            if (cur == 'R') {
                isRev = !isRev;
            } else if (cur == 'D') {
                // 검증
                if (dq.isEmpty()) {
                    sb.append("error\n");
                    return;
                }

                // 방향을 고려한 제거
                if (isRev) {
                    dq.removeLast();
                } else {
                    dq.removeFirst();
                }
            }
        }

        // 방향을 고려한 정상 등록
        int rep = dq.size();

        sb.append("[");
        for (int i = 0; i < rep; i++) {
            if (isRev) {
                sb.append(dq.removeLast());
            } else {
                sb.append(dq.removeFirst());
            }

            if (i < rep - 1) {
                sb.append(",");
            }
        }
        sb.append("]\n");
    }
}