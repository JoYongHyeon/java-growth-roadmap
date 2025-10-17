package algorithm.programmers._0_level;

import java.util.Arrays;

public class _11_Provisional_Mark {

    public static void main(String[] args) {

        // 학생 번호
        int[] numbers = {3, 4};
        // 학생들 가채점 점수(학생번호 순서)
        int[] our_score = {85, 93};
        // 학생들 실제 점수
        int[] score_list = {85, 92, 38, 93, 48, 85, 92, 56};
        String[] solution = solution(numbers, our_score, score_list);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public static String[] solution(int[] numbers, int[] our_score, int[] score_list) {
        int num_student = numbers.length;
        String[] answer = new String[num_student];

        for (int i = 0; i < num_student; i++) {
            // 바뀐 부분
            if (our_score[i] == score_list[numbers[i]-1]) {
//                if (our_score[i] == score_list[i]) {
                answer[i] = "Same";
            }
            else {
                answer[i] = "Different";
            }
        }

        return answer;
    }
}
