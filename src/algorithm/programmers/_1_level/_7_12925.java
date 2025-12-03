package algorithm.programmers._1_level;

public class _7_12925 {

    public static void main(String[] args) {

        Solution solution = new Solution();

        // 1. 문자열 s 를 숫자로 변환 / - 가 붙으면 - 까지 포함
        int answer1 = solution.solution("1234");
        int answer2 = solution.solution("-1234");

        System.out.println(answer1);
        System.out.println(answer2);
    }

    public static class Solution {
        public int solution(String s) {

            return Integer.parseInt(s);
        }
    }
}
