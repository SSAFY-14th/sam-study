import java.io.*;
import java.util.*;

/*
 * 2차원 맵 -> 1차원 높이 배열
 */

public class Main_유승준 {
    static int N, M, B;
    static int[] block = new int[257];

    static int stt, end;
    static int ansT, ansH;

    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int minH = 256;
        int maxH = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                block[v]++;

                minH = Math.min(minH, v);
                maxH = Math.max(maxH, v);
            }
        }

        stt = maxH;
        end = minH;

        ansT = Integer.MAX_VALUE;
        ansH = -1;

        for (int i = stt; i >= end; i--) {
            getAns(i);
        }

        System.out.println(ansT + " " + ansH);
    }

    static void getAns(int h) {
        int t = 0;
        int addAvAmt = B;

        for (int cur = 0; cur <= 256; cur++) {
            if (block[cur] == 0) continue;

            if (cur > h) {
                int diff = cur - h;
                t += (diff * 2) * block[cur];
                addAvAmt += diff * block[cur];
            }
            else if (cur < h) {
                int diff = h - cur;
                t += (diff * 1) * block[cur];
                addAvAmt -= diff * block[cur];
            }
        }

        if (addAvAmt < 0 || t >= ansT) return;

        ansT = t;
        ansH = h;
    }
}