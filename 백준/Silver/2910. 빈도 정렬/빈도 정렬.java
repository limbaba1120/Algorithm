
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int C = scan.nextInt();

		Map<Integer, Integer> messages = new LinkedHashMap<>();

		for (int i = 0; i < N; i++) {
			int message = scan.nextInt();
			messages.put(message, messages.getOrDefault(message, 0) + 1);
		}

		Integer[] frequencies = messages.keySet().toArray(new Integer[messages.size()]);

		Arrays.sort(frequencies, (o1, o2) -> {
			return messages.get(o2) - messages.get(o1);
		});

		for (int frequency : frequencies) {
			int count = messages.get(frequency);
			while (count-- > 0) {
				System.out.print(frequency + " ");
			}
		}
		System.out.println();
	}
	/*public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");

		Message[] messages = new Message[N];

		for (int i = 0; i < N; i++) {
			messages[i] = new Message(Integer.parseInt(st.nextToken()), i);
		}

		Arrays.sort(messages, (o1, o2) -> {
			return o1.value - o2.value;
		});

		Frequency[] frequencies = new Frequency[N];
		int idx = 0;
		frequencies[0] = new Frequency(messages[0].value, 1, messages[0].idx);

		for (int i = 1; i < N; i++) {
			if (messages[i].value != messages[i - 1].value) {
				frequencies[++idx] = new Frequency(messages[i].value, 0, messages[i].idx);
			}
			frequencies[idx].count++;
		}

		Arrays.sort(frequencies, 0, idx + 1, new Comparator<Frequency>() {
			@Override
			public int compare(Frequency f1, Frequency f2) {
				if (f1.count == f2.count) {
					return f1.idx - f2.idx;
				}
				return f2.count - f1.count;
			}
		});

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i <= idx; i++) {
			while (frequencies[i].count-- > 0) {
				bw.write(frequencies[i].value + " ");
			}
		}
		bw.flush();


	}

	static class Message {
		int value;
		int idx;

		Message(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
	}

	static class Frequency {
		int value;
		int count;
		int idx;

		Frequency(int value, int count, int idx) {
			this.value = value;
			this.count = count;
			this.idx = idx;
		}
	}*/
}
