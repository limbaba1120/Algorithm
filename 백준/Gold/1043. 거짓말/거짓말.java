
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[] parent;
    static int[] trueP;
    static ArrayList<Integer>[] party;
    static int result;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int T = scan.nextInt();
        result = 0;
        trueP = new int[T];
        party = new ArrayList[M];

        for (int i = 0; i < T; i++) {
            trueP[i] = scan.nextInt();
        }

        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<Integer>();
            int party_size = scan.nextInt();
            for (int j = 0; j < party_size; j++) {
                party[i].add(scan.nextInt());
            }
        }
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int firstPeople = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(firstPeople, party[i].get(j));
            }
        }

        for (int i = 0; i < M; i++) {
            boolean isPossible = true;
            int cur = party[i].get(0);
            for (int j = 0; j < trueP.length; j++) {
                if (find(cur) == find(trueP[j])) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                result++;
            }
        }
        System.out.println(result);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}
