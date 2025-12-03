package algorithm.programmers._1_level;


public class _6_87389 {

    public static void main(String[] args) {

        Solution solution = new Solution();

        // 1. 자연수 n 이 매개변수로 주어진다.
        int answer = solution.solution(10);

        System.out.println(answer);
    }

    public static class Solution {
        public int solution(int n) {
            // 2. n 을 x 로 나눈 나머지가 1이 되도록 하는 가장 작은 자연수 x 를 구하라
            int num = 1;
            while (n % num != 1) {
                num++;
            }
            return num;
        }
    }
}
