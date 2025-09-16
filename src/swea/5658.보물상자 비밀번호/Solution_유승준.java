package 보물상자_비밀번호;

import java.io.*;
        import java.util.*;

public class Solution_유승준 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            // 한 변의 코드 길이
            int len = N / 4;

            // 계산하기 용이하도록 기존 코드를 가공
            String code = br.readLine();
            String newCode = code + code.substring(0, len - 1);

            HashSet<String> set = new HashSet<>();

            // 한 사이클 돌며 셋에 저장
            for (int i = len - 1; i < newCode.length(); i++) {
                set.add(newCode.substring(i - (len - 1), i + 1));
            }

            ArrayList<Integer> arr = new ArrayList<>();

            // 숫자로 파싱한 뒤 오름차순으로 정렬
            for (String v : set) {
                arr.add(Integer.parseInt(v, 16));
            }

            Collections.sort(arr);

            sb.append('#').append(tc).append(' ').append(arr.get(arr.size() - K)).append('\n');
        }

        System.out.print(sb);
    }

}
