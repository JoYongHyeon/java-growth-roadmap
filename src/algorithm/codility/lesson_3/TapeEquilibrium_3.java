package algorithm.codility.lesson_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TapeEquilibrium_3 {

    public static void main(String[] args) {

        // 1. N 개의 정수로 구성된 배열 A
        int[] A = {3, 1, 2, 4, 3};

        int sum = 0;
        for (int i : A) {
            sum += i;
        }
        /**
         * P = 1, 차이 = |3 − 10| = 7
         * P = 2, 차이 = |4 − 9| = 5
         * P = 3, 차이 = |6 − 7| = 1
         * P = 4, 차이 = |10 − 3| = 7
         */
        int n = 0;
        List<Integer> list = new ArrayList<>();
        /**
         * A = {3, 1, 2, 4, 3};
         * sum = 13
         * list.add(13 - (3 * 2) - (0 * 2)) = 7;
         * list.add(13 - (1 * 2) - (3 * 2)) = 5;
         * list.add(13 - (2 * 2) - (4 * 2)) = 1;
         * list.add(13 - (4 * 2) - (6 * 2)) = 7;
         */
        for (int i = 0; i < A.length-1; i++) {
            list.add(sum - (A[i] * 2) - (n * 2));
            n += A[i];
        }

        list.replaceAll(Math::abs);
        System.out.println(Collections.min(list));
    }
}
