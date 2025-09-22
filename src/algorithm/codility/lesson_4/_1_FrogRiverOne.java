package algorithm.codility.lesson_4;

public class _1_FrogRiverOne {

    public static void main(String[] args) {

        // 반대편 강둑 위치
        int X = 5;

        int[] A = {1, 3, 1, 4, 2, 3, 5, 4};

        boolean[] used = new boolean[X + 1];

        int cnt = 0;
        int result = 0;

        // {false, true, false, false, false, false}
        // {false, true, false, true, false, false}
        // {false, true, false, true, false, false}
        // {false, true, false, true, true, false}
        // {false, true, true, true, true, false}
        // {false, true, true, true, true, false}
        // {false, true, true, true, true, true}
        for (int i = 0; i < A.length; i++) {
            if (!used[A[i]]) {
                used[A[i]] = true;
                cnt++;
            }

            if (cnt == X) {
                result = i;
                break;
            }

            if (i == A.length - 1) {
                result = -1;
                break;
            }
        }
        System.out.println(result);

    }
}
