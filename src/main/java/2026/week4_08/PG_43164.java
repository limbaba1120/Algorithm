import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PG_43164 {
    static boolean[] isVisited;
    static List<String> route = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        route.clear();

        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] first, String[] second) {
                if (first[0].equals(second[0])) {
                    return first[1].compareTo(second[1]);
                }

                return first[0].compareTo(second[0]);
            }
        });

        isVisited = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        return route.get(0).split(" ");
    }

    public void dfs(
            int depth,
            String now,
            String path,
            String[][] tickets
    ) {
        if (depth == tickets.length) {
            route.add(path);
            return;
        }

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i] && now.equals(tickets[i][0])) {
                isVisited[i] = true;

                dfs(
                        depth + 1,
                        tickets[i][1],
                        path + " " + tickets[i][1],
                        tickets
                );

                isVisited[i] = false;
            }
        }
    }
}
