package algorithm.codility.lesson_6;

import java.util.Arrays;

public class _3_Triangle {

    public static void main(String[] args) {

        // 0 <= P < Q < R < N
//        int[] A = {10, 2, 5, 1, 8, 20};
        int[] A = {10, 50, 5, 1};
        int N = A.length;
        Arrays.sort(A);

        for (int i = 0; i < N; i++) {

            if (i + 2 > N) break;

            if (A[i] + A[i+1] > A[i+2] &&
                A[i+1] + A[i+2] > A[i] &&
                A[i+2] + A[i] > A[i+1]) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}
