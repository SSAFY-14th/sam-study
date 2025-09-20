import java.io.*;
import java.util.*;

public class Main_나유경 {
	static String p,str;
	static int n;
	static Deque<Integer> deque;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			p = br.readLine();
			n = Integer.parseInt(br.readLine());
			str = br.readLine();
			deque = new LinkedList<>();

			if (n > 0) {
				String[] nums = str.substring(1, str.length() - 1).split(",");
				for (String num : nums) {
					deque.offer(Integer.parseInt(num));
				}
			}

			boolean reversed = false;
			boolean error = false;

			for (char cmd : p.toCharArray()) {
				if (cmd == 'R') {
					reversed = !reversed;
				} else if (cmd == 'D') {
					if (deque.isEmpty()) {
						sb.append("error\n");
						error = true;
						break;
					}
					if (!reversed)
						deque.pollFirst();
					else
						deque.pollLast();
				}
			}

			if (!error) {
				sb.append("[");
				if (!deque.isEmpty()) {
					if (!reversed) {
						while (!deque.isEmpty())
							sb.append(deque.pollFirst()).append(",");
					} else {
						while (!deque.isEmpty())
							sb.append(deque.pollLast()).append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]\n");
			}
		}
		System.out.print(sb);
	}
}