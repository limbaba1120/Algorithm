package main.java.week2_04;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ_2170 {

    static class Line implements Comparable<Line>{
        int start;
        int end; 

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        // 선 정보를 정렬 
        PriorityQueue<Line> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Line(start, end));
        }

        Line firstLine = pq.poll();

        int start = firstLine.start;
        int end = firstLine.end;

        int result = 0;

        // 다음 선이 현재 선과 겹치지 않는 경우 확인
        while (!pq.isEmpty()) {
            Line nextLine = pq.poll();

            if (nextLine.start > end) {
                result += end - start;
                start = nextLine.start;
                end = nextLine.end;
                continue;
            }

            // 다음 선이 현재 선과 겹치는 경우 확인 
            if (nextLine.end > end) {
                end = nextLine.end;
            }
        }

        result += end - start; 
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
