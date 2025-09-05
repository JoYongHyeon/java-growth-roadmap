package algorithm.codility.lesson_2;

import java.util.Arrays;

/**
 * 딱히 어려운 부분은 없음
 */
public class CyclicRotation_2 {

    public static void main(String[] args) {

        int[] arr = {3, 8, 9, 7, 6};
        int k = 3;

        int[] result = solution(arr, k);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(int[] A, int K) {

        int n = A.length;
        if (n == 0) return A;

        // n = 5
        // K 가 N 보다 크면 불필요 회전 줄이기
        // K = 3 % 5;
        K = K % n;
        int[] result = new int[n];

        // 3, 8, 9, 7, 6;
        for (int i = 0; i < n; i++) {
            int newIndex = (i + K) % n;
            result[newIndex] = A[i];
        }

        return result;
    }
}
