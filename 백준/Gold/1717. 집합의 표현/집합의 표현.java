
import java.util.Scanner;

public class Main {
    static int parent[];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        parent = new int[N + 1];
        for (int i = 0; i < parent.length; i++) {   //대표노드 초기화
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) { //질의 계산하는 부분
            int question = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();
            if (question == 0) { //union
                union(a, b);
            } else { //두 원소 같은지 보기
                boolean result = checkSame(a, b);
                if (result) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static void union(int a, int b) {
        // 대표노드를 찾아서 연결하기
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]); //value를 index로 바꿔서 또 찾아보기
        }
    }

    private static boolean checkSame(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        } else {
            return false;
        }
    }
}
