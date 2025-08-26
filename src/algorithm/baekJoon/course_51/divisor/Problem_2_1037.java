package algorithm.baekJoon.course_51.divisor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_2_1037 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] arr = new int[count];
        int result;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 약수의 개수가 한 개 인경우 약수의 제곱근임
        if (count == 1) {
            result = arr[0] * arr[0];
        } else {
            result = arr[0] * arr[count - 1];
        }
        System.out.println(result);
    }
}
