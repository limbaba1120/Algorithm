package algorithm.javabook.datastructure;

import java.util.*;
import java.util.Map.*;


public class Num {
    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3, 4, 5};
        int sum = Arrays.stream(arr).sum();

        Map<String, Integer> hashMap = new HashMap();
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.put("C", 3);

        for (Entry<String, Integer> entry : hashMap.entrySet()) {

        }
    }
}
