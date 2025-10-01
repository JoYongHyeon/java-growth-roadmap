package algorithm.codility.lesson_6;

import java.util.Arrays;

public class _2_MaxProductOfThree {

    public static void main(String[] args) {

        // N 개의 정수로 구성된 배열 A
        // 0 <= P < Q < R < N

        int[] A = {-3, 1, 2, -2, 5, 6};

        // -3, -2, 1, 2, 5, 6
        Arrays.sort(A);

        int N =A.length;

        int case1 = A[N-1] * A[N-2] * A[N-3];
        int case2 = A[0] * A[1] * A[N-1];

        System.out.println(Math.max(case1, case2));

        /**
         * 문제에서 보면 Your goal is to find the maximal product of any triplet. 가장 큰 값을 찾으라고 한다.
         *
         * 이 문제를 풀면서 깨달은 건 단순했다.
         * 세 개 숫자의 곱 중 최댓값을 찾는 문제인데, 처음엔 모든 조합을 생각했지만 불필요했음
         * 결국 경우의 수는 두 가지뿐이다......
         *
         * 1. 배열에서 가장 큰 세 수를 곱하는 경우
         * 2. 배열에서 가장 작은 두 수(큰 음수 두 개)와 가장 큰 수를 곱하는 경우.
         *
         * 이유는 음수 두 개를 곱하면 큰 양수가 될 수 있고, 여기에 가장 큰 양수를 곱하면 최대가 될 수 있기 때문..
         * 따라서 어떤 배열이든 최댓값은 이 두 후보 중 하나에서만 나온다.
         */
    }
}
