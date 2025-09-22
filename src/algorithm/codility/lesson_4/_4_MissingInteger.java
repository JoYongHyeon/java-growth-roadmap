package algorithm.codility.lesson_4;


public class _4_MissingInteger {

    public static void main(String[] args) {

        /**
         * 예를 들어, A = [1, 3, 6, 4, 1, 2]인 경우 함수는 5를 반환해야 합니다.
         * A = [1, 2, 3]인 경우 함수는 4를 반환해야 합니다. -> O
         * A = [−1, −3]인 경우 함수는 1을 반환해야 합니다. -> O
         */
//        int[] A = {1, 3, 6, 4, 1, 2};
//        int[] A = {1, 2, 3};
        int[] A = {-1, -3};

        int N = A.length;

        // {false, false, false, false, false, false, false}
        boolean[] used = new boolean[N + 1];


        // {false, true, true, true, true, false, true}
        for (int num : A) {
            if (num > 0 && num <= N) {
                used[num] = true;
            }
        }

        // {-1, -3} 일 경우 애초에 따로 로직을 만들 필요가 없음
        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(N + 1);

    }
}
