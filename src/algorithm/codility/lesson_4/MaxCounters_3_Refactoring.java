package algorithm.codility.lesson_4;

import java.util.Arrays;

public class MaxCounters_3_Refactoring {

    public static void main(String[] args) {
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
        int N = 5;
        int[] K = new int[N];
        int[] A = {3, 4, 4, 6, 1, 4, 4};
        int M = A.length;

        int maxNum = 0;       // 현재까지 최대값
        int lastUpdate = 0;   // 마지막으로 N+1 연산에서 적용된 값

        for (int i = 0; i < M; i++) {
            if (1 <= A[i] && A[i] <= N) {
                int idx = A[i] - 1;

                // lazy 적용: 현재 값이 lastUpdate보다 작으면 업데이트
                if (K[idx] < lastUpdate) {
                    K[idx] = lastUpdate;
                }

                K[idx]++;
                maxNum = Math.max(maxNum, K[idx]);

                /**
                 * 리팩토링 버전
                 * N+1 연산 시 배열 전체를 갱신 제거 -> O(M+N)
                 * 각 카운터 증가 시점에만 필요한 경우에 lazy 적용
                 * 마지막에 한 번만 전체 적용 -> 불필요한 반복 최소화
                 */
            } else if (A[i] == N + 1) {
                lastUpdate = maxNum;
            }
        }

        // 마지막에 모든 카운터에 lazy 적용
        for (int i = 0; i < N; i++) {
            if (K[i] < lastUpdate) {
                K[i] = lastUpdate;
            }
        }

        System.out.println(Arrays.toString(K));

    }
}

// 403 테스트
