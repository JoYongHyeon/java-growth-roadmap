package algorithm.codility.lesson_4;

import java.util.HashSet;
import java.util.Set;

public class _2_PermCheck {

    public static void main(String[] args) {

        // 순열은 1부터 N 까지 각 원소를 한 번씩 포함하는 시퀀스
        int[] A = {4, 1, 3, 2};
//        int[] A = {4, 1, 3};

        int N = A.length;
        Set<Integer> set = new HashSet<>();

        int maxNum = 0;
        for (int num : A) {
            maxNum = Math.max(maxNum, num);
            set.add(num);
        }

        /**
         * N=4
         * maxNum = 4;
         * set = {4, 1, 3, 2}
         */
        /**
         * N=3
         * maxNum = 4;
         * set = {4, 1, 3}
         */

        // 최댓값이 N 이어야 함 (예: 길이가 3인데 값이 4면 순열 불가능)
        if (maxNum != N) {
            System.out.println(0);
            return;
        }

        // 중복 없이 정확히 N개 있어야 함
        if (set.size() != N) {
            System.out.println(0);
            return;
        }

        // 순열
        System.out.println(1);
    }
}
