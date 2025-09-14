package algorithm.codility.lesson_3;

import java.util.Arrays;

public class PermMissingElem {

    public static void main(String[] args) {

        // 서로 다른 정수로 구성된 배열 A
        int[] A = {2, 3, 1, 5};
        // 4
        int N = A.length;

        boolean[] used = new boolean[N+2];
        for (int j : A) {
            used[j] = true;
        }

        System.out.println(Arrays.toString(used));
        for (int i = 0; i < N; i++) {

            if (!used[N]) {
                System.out.println(N-i);
                break;
            }
        }
    }
}
