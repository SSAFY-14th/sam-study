import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class bj5430_AC {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String order = br.readLine();
			char[] char_arr = order.toCharArray();
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();

			// 숫자 뽑아내기
			str = str.replace("[", "");
			str = str.replace("]", "");

			String[] arr = str.split(",");
			list = new ArrayList<>();
			int idx = 0;
			if (str.length() > 0) {
				for (String i : arr) {
					list.add(Integer.parseInt(i));
				}
			}

			error = false;
			direction = true; // true 이면 정방향
			// 작업 수행
			for (int i = 0; i < char_arr.length; i++) {
				char curr = char_arr[i];
				doit(curr, direction);
				if (error) break; // 조기종료
			}

			// 출력
			StringBuilder sb = new StringBuilder();
			if (error) {
				sb.append("error");
			} else {
				if (direction) {
					for (Integer i : list) {
						sb.append(i + ",");
					}
				}
				else {
					for (int i = list.size()-1; i >= 0; i--) {
						sb.append(list.get(i)+",");
					}
				}
				if(sb.length() != 0) sb.deleteCharAt(sb.length() -1);
				sb.insert(0, "[");
				sb.insert(sb.length(), "]");
			}
			System.out.println(sb);
		}

	}

	private static void doit(char curr, boolean direction2) {
		if (curr == 'D') {
			if (list.size() == 0) {
				error = true;
				return;
			}

			if (direction2)
				list.remove(0);
			else
				list.remove(list.size() - 1);
		}
		if (curr == 'R') {
			direction = !direction2;
		}
	}

	static boolean error;
	static boolean direction;
	static List<Integer> list;
}