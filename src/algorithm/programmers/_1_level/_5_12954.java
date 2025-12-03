package algorithm.programmers._1_level;

import java.util.Arrays;

public class _5_12954 {

    public static void main(String[] args) {

        // 1. 정수 x 와 자연수 n 을 입력 받는다.
        int x = 2;
        int n = 5;
        long[] arr = new long[n];

        // 2. x 부터 시작해 x 씩 증가하는 숫자를 n 개 지니는 리스트를 리턴해야 함
        for (int i = 1; i <= n; i++) {
            arr[i-1] = (long) x * i;
        }

        System.out.println(Arrays.toString(arr));
    }
}
