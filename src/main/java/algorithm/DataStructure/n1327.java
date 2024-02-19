package algorithm.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n1327 {
    static int N,K;
    static char[] arr, copy;
    static String arr_str, copy_str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N];
        arr = br.readLine().replace(" ", "").toCharArray();

        copy = Arrays.copyOf(arr, N);
        Arrays.sort(arr);
        arr_str = new String(arr);
        copy_str = new String(copy);
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Strint> q = new LinkedList<>();
        Set<String> search = new HashSet<>();
        // SET으로 방문 여부 관리
        q.offer(new Strint(copy_str, 0));

        while(!q.isEmpty()) {
            Strint ci = q.poll();
            String str = ci.str;
            int cnt = ci.cnt;

            // 결과와 같을 경우 CNT 반환
            if(arr_str.equals(str)) return cnt;

            // set에 포함되어 있지 않을 경우 k개 뒤집은 배열 넣기
            if(!search.contains(str)) {
                search.add(str);
                for(int i=0; i<=N-K;i++) {
                    q.offer(new Strint(reverseStr(str,i,i+K), cnt+1));
                }
            }

        }
        return -1;
    }

    private static String reverseStr(String str, int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, i));

        // 특정 부분만 뒤집기
        String reverse = str.substring(i,j);
        for(int t = K-1;t>=0;t--) {
            sb.append(reverse.charAt(t));
        }

        sb.append(str.substring(j, N));
        return sb.toString();
    }

    private static class Strint{
        String str;
        int cnt;
        public Strint(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }

    }
}