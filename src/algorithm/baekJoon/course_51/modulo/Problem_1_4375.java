package algorithm.baekJoon.course_51.modulo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 모듈러(나머지 연산)
 */
public class Problem_1_4375 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);

            int num = 1;
            int length = 1;

            while (num % n != 0) {

                num = (num * 10 + 1) % n;
                length++;
            }
            System.out.println(length);
        }
    }
}
