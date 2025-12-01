package algorithm.programmers._1_level;

import java.util.Arrays;

public class _4_12944 {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};
        double sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println(sum / arr.length);

    }
}
