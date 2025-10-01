package algorithm.codility.lesson_6;

import java.util.HashSet;

public class _1_Distinct {

    public static void main(String[] args) {

        HashSet<Integer> set = new HashSet<>();

        // N 개의 정수로 구성된 배열 A
        int[] A = {2, 1, 1, 2, 3, 1};

        for (int num : A) {
            set.add(num);
        }

        System.out.println(set.size());
    }
}
