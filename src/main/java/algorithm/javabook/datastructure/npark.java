package algorithm.javabook.datastructure;

public class npark {
    public static void main(String[] args) {
        String[] park = {"OSO","OOO","OXO","OOO"};

        int sx = 0;
        int sy = 0;

        char[][] arr = new char[park.length][park[0].length()];

        for (int i = 0; i < park.length; i++) {
            arr[i] = park[i].toCharArray();

            if (park[i].contains("S")) {
                sy = i;
                sx = park[i].indexOf("S");
            }
        }
        System.out.println(park[0]);
        System.out.println(park[1]);
        System.out.println(sx);
        System.out.println(sy);
    }
    public int[] solution(String[] park, String[] routes) {
        int sx = 0;
        int sy = 0;

        char[][] arr = new char[park.length][park[0].length()];

        for (int i = 0; i < park.length; i++) {
            arr[i] = park[i].toCharArray();

            if (park[i].contains("S")) {
                sy = i;
                sx = park[i].indexOf("S");
            }
        }
        return null;
    }
}
