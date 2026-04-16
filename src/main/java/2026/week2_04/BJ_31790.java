package main.java.week2_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 길이 N인 순열 a 에 대해 S와 b를 아래와 같이 정의 
// - 순열 (nPr) = n! / (n-r)!
// - Si,j (1 <= i <= N) 는 순열 a의 연속 부분 수열 a1, a2, ..., ai의 원소 중 p로 나눈 나머지가 j(0<= j < p) 인 원소의 개수 
// bi ( 1 <= i <= N)는 Si,0, Si,1, ..., Si,p-1 중 최대값 
// 어떤 숫자 p와 수열 b에 대해서도 순열 a가 존재하는지 궁금 -> 조건을 만족하는 순열 a가 존재하는지 확인

// 필요 조건 
// b[1] == 1 -> 첫 원소는 무조건 max 1
// b[i] - b[i-1] <= 1 -> max는 한번에 최대 1씩만 증가 
// b[N] == [N/p] -> 최대 용량 버킷은 반드시 끝까지 채워짐


public class BJ_31790 {
    static int N; // 수열 b 의 길이
    static int p; // 정수 p (0 <= j < p)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        int[] b = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        // 버킷 용량 (모든 공을 다 넣고 나면, 가장 큰 버킷은 반드시 용량만큼 채워져 있음)
        // 예) 비둘기집 원리 : N개를 p개 나머지 칸에 나누면, 가장 많은 칸은 최소 [N/p]개 이상
        int M = (N + p - 1) / p;

        // N = k*p + r 로 쪼개기
        // -> 용량이 (k+1)인 버킷: r개 (나머지 1..r 이 하나 더 가짐)
        // -> 용량이  k   인 버킷: p - r개
        int k = N / p;
        int r = N % p;

        // Q[v] = "모든 버킷을 v개 이하로만 채웠을 때 넣을 수 있는 최대 공 개수"
        //      = Σ min(c[j], v)
        // 누적합 공식: Q[v] = Q[v-1] + q(v)
        //   q(v) = "용량이 v 이상인 버킷 수"
        //          v <= k    -> 모든 버킷이 해당 -> q = p
        //          v == k+1  -> 큰 버킷(r개)만 해당 -> q = r
        long[] Q = new long[M + 2];
        for (int v = 1; v <= M; v++) {
            int qv;
            if (v <= k) qv = p;
            else if (v == k + 1) qv = r;
            else qv = 0;
            Q[v] = Q[v - 1] + qv;
        }

        boolean ok = true;

        // 조건 1: b[1] == 1 (첫 공을 넣으면 무조건 max=1)
        if (b[1] != 1) ok = false;

        // 조건 2: b[i] - b[i-1] ∈ {0, 1} (공 하나 추가는 max를 최대 1만 올림)
        for (int i = 2; i <= N && ok; i++) {
            int diff = b[i] - b[i - 1];
            if (diff != 0 && diff != 1) {
                ok = false;
                break;
            }
        }

        // 조건 3: b[N] == M (끝에는 반드시 가장 큰 버킷이 꽉 참)
        if (ok && b[N] != M) ok = false;

        // 조건 4: 모든 i에서 i <= Q[b[i]]
        //  -> b[i]=v 라면 지금까지 넣은 공 i개가 "v 이하로만" 쌓여야 함
        //  -> i가 Q[v]를 넘으면 비둘기집 원리로 누군가 v+1이 되어버림 -> 모순
        if (ok) {
            for (int i = 1; i <= N; i++) {
                if (b[i] > M || i > Q[b[i]]) {
                    ok = false;
                    break;
                }
            }
        }

        System.out.println(ok ? "YES" : "NO");
    }
}

