package algorithm.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n12891 {
    public static int myArr[];
    public static int checkArr[];
    public static int checkSecret;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Result = 0;
        checkArr = new int[4]; // 비밀번호 체크 배열
        myArr = new int[4];  // 현재 상태의 배열
        char A[] = new char[S]; // 전체 문자열 담는 배열
        checkSecret = 0; // 몇 개의 문자와 관련된 개수를 충족했는지 판단

        A = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) {
                checkSecret++; // 만약 check 배열중에 0 (굳이 확인 안해도 되는 char이 있으면 무시하면됨) 빼고 check해야할 갯수 체크한다..
            }
        }

        for (int i = 0; i < P; i++) { // 부분 문자열 처음 받을 때 세팅
            Add(A[i]);
        }

        Result = getResult(Result);

        // 슬라이딩 윈도우
        for (int i = P; i < S; i++) {
            int j = i - P; // 왼쪽 끝
            Add(A[i]);
            remove(A[j]);
            Result = getResult(Result);
        }
        System.out.println(Result);

    }

    private static int getResult(int Result) {
        if (checkSecret == 4) {
            Result++;
        }
        return Result;
    }

    private static void remove(char c) {
        switch (c) {
            case 'A':
                if (myArr[0] == checkArr[0]) {
                    checkSecret--;
                }
                myArr[0]--;
                break;
            case 'C':
                if (myArr[1] == checkArr[1]) {
                    checkSecret--;
                }
                myArr[1]--;
                break;
            case 'G':
                if (myArr[2] == checkArr[2]) {
                    checkSecret--;
                }
                myArr[2]--;
                break;
            case 'T':
                if (myArr[3] == checkArr[3]) {
                    checkSecret--;
                }
                myArr[3]--;
                break;

        }
    }

    private static void Add(char c) {
        switch (c) {
            case 'A':
                myArr[0]++;
                if (myArr[0] == checkArr[0]) {
                    checkSecret++;
                }
                break;
            case 'C':
                myArr[1]++;
                if (myArr[1] == checkArr[1]) {
                    checkSecret++;
                }
                break;
            case 'G':
                myArr[2]++;
                if (myArr[2] == checkArr[2]) {
                    checkSecret++;
                }
                break;
            case 'T':
                myArr[3]++;
                if (myArr[3] == checkArr[3]) {
                    checkSecret++;
                }
                break;

        }
    }
}
