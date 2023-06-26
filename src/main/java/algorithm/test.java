package algorithm;

public class test {
    public static void main(String[] args) {
        int CODE;
        int grade = 201912120;
        CODE = ((grade % 10000) * 3897 + (grade / 100000)*(grade / 100000) ) % 10000;

        System.out.println(CODE);
    }
}
