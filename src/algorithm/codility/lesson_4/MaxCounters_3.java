package algorithm.codility.lesson_4;

import java.util.Arrays;

public class MaxCounters_3 {

    public static void main(String[] args) {

        int N = 5;
        int[] K = new int[N];
        int[] A = {3, 4, 4, 6, 1, 4, 4};
        int M = A.length;
        int maxNum = 0;

        for (int i = 0; i < M; i++) {
            if (1 <= A[i] && A[i] <= N) {
                int i1 = A[i]; // 3
                K[i1-1]++;
                maxNum = Math.max(maxNum, K[i1 - 1]);
            }

            /**
             * 기존 방식
             * 문제점: N+1 연산이 나올 때마다 배열 전체를 반복문으로 갱신
             * N과 M이 크면 O(N*M) -> 타임아웃
             */
            if (A[i] == (N + 1)) {
                for (int j = 0; j < N; j++) {
                    K[j] = maxNum;
                }
            }
        }
        System.out.println(Arrays.toString(K));

        /**
         * 각 연산을 순서대로 적용하면 카운터 값의 변화는 다음과 같습니다:
         * (0, 0, 1, 0, 0)
         * (0, 0, 1, 1, 0)
         * (0, 0, 1, 2, 0)
         * (2, 2, 2, 2, 2)
         * (3, 2, 2, 2, 2)
         * (3, 2, 2, 3, 2)
         * (3, 2, 2, 4, 2)
         */
    }
}
