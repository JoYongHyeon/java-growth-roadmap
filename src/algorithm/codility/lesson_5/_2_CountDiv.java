package algorithm.codility.lesson_5;

public class _2_CountDiv {

    public static void main(String[] args) {

        int A = 6;
        int B = 11;
        int K = 2;

        // 1차 답안
//        System.out.println((B / K) - (A / K) + 1;

        /**
         * A % K == 0 일 경우에는 +1 을 해야함
         * 왜 ? A 숫자 부터 시작을 해야 되기 때문에 A 가 K 의 배수라면 시작 카운트로 +1 을 하는게 맞음
         */
        System.out.println((B / K) - (A / K) + (A % K == 0 ? 1 : 0));


    }
}
