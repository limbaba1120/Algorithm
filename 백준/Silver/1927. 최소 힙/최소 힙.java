
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		MinPriorityQueue pq = new MinPriorityQueue();
		StringBuilder sb = new StringBuilder();


		while (n-- > 0) {
			int m = scan.nextInt();

			if (m == 0) {
				sb.append(pq.pop()).append('\n');
			} else {
				pq.push(m);
			}
		}
		System.out.println(sb);
	}
}


class MinPriorityQueue {
	private int[] heap;
	private int size;

	public MinPriorityQueue() {
		heap = new int[100000];
		size = 0;
	}

	public void swap(int a, int b) {
		int tmp = heap[a];
		heap[a] = heap[b];
		heap[b] = tmp;
	}

	public void push(int a) {
		heap[++size] = a;

		int current = size;

		while (current > 1) {
			int parent = current / 2;
			if (heap[parent] < heap[current]) {
				break;
			}
			swap(parent, current);
			current = parent;
		}
	}

	public int pop() {
		if (size == 0) {
			return 0;
		}

		int ret = heap[1];

		heap[1] = heap[size--];

		int current = 1;

		while (current * 2 <= size) {
			int left = current * 2;
			int right = left + 1;

			int child = left;

			if (right <= size && heap[left] > heap[right]) {
				child = right;
			}

			if (heap[current] <= heap[child]) {
				break;
			}

			swap(current, child);
			current = child;
		}
		return ret;
	}
}