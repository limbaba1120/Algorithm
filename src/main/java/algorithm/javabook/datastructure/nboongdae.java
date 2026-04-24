package algorithm.javabook.datastructure;

public class nboongdae {
    public static void main(String[] args) {
        int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
        int now = 30;
        int v1, v2;
        for (int[] atk: attacks) {
            if (now <= 0) {
                break;
            }
            System.out.println(atk[0]);

        }
    }
}
