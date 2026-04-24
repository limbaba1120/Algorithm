package algorithm.DataStructure;

import java.util.*;

public class PG_258711 {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        int maxNum = 0; //정점의 개수
        Map<Integer, Edge> graph = new HashMap<>();

        for(int i = 0 ; i < edges.length; i++){
            int giveNum = edges[i][0];
            int receiveNum = edges[i][1];

            if(maxNum < giveNum) maxNum = giveNum;
            if(maxNum < receiveNum) maxNum = receiveNum;

            if(!graph.containsKey(giveNum)) graph.put(giveNum, new Edge(0, 0));
            if(!graph.containsKey(receiveNum)) graph.put(receiveNum, new Edge(0, 0));

            graph.get(giveNum).giveCount++;
            graph.get(receiveNum).receiveCount++;
        }

        for(int i = 1; i <= maxNum; i++){
            int giveCount = graph.get(i).giveCount;
            int receiveCount = graph.get(i).receiveCount;

            if(giveCount >= 2 && receiveCount == 0){
                //정점 : 받는 간선이 2개 이상 이고 나가는 간선이 없다(0)
                answer[0] = i;
            }else if(graph.get(i).giveCount == 0){
                //막대 : 받는 간선이 없이 나가는 간선만 있음.
                answer[2]++;
            }else if(graph.get(i).giveCount >= 2 && graph.get(i).receiveCount >= 2){
                //8자 : 받고 나가는 간선이 2개 이상 이어야 한다.
                answer[3]++;
            }

        }
        // 도넛
        answer[1] = graph.get(answer[0]).giveCount - answer[2] - answer[3];
        return answer;
    }

    public class Edge {
        private int giveCount;
        private int receiveCount;
        public Edge(int giveCount, int receiveCount) {
            this.giveCount = giveCount;
            this.receiveCount = receiveCount;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "giveCount=" + giveCount +
                    ", receiveCount=" + receiveCount +
                    '}';
        }
    }
}

