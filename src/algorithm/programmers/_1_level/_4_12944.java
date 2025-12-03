package algorithm.programmers._1_level;


public class _4_12944 {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};
        double sum = 0;

        for (int j : arr) {
            sum += j;
        }
        System.out.println(sum / arr.length);

    }
}
