package algorithm.codility.lesson_2;

import java.io.IOException;

public class OddOccurrencesInArray_2 {

    public static void main(String[] args) throws IOException {

        int[] arr = {9, 3, 9, 3, 9, 7, 9};

        int result = 0; // 초기값
        System.out.println("초기 result = " + result);

        for (int i = 0; i < arr.length; i++) {
            int before = result;
            int num = arr[i];
            result = result ^ num; // XOR 수행
            System.out.printf("단계 %d: %d ^ %d = %d%n", i + 1, before, num, result);
        }

        System.out.println("최종 결과(짝 없는 값) = " + result);
    }
}
