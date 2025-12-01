package algorithm.programmers._1_level;

public class _3_12931 {

    public static void main(String[] args) {

        int n = 123;
        int sum = 0;
        String numToString = String.valueOf(n);
        for (int i = 0; i < numToString.length(); i++) {
            sum += Integer.parseInt(String.valueOf(numToString.charAt(i)));
        }

        System.out.println(sum);
    }
}
