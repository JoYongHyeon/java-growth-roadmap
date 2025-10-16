package algorithm.programmers._0_level;

// https://school.programmers.co.kr/learn/courses/30/lessons/340200
public class _8_Nickname {

    public static void main(String[] args) {

//        String result = solution("GO");
        String result = solution("WORLDworld");
        System.out.println(result);
    }

    public static String solution(String nickname) {
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<nickname.length(); i++) {
            if(nickname.charAt(i) == 'l'){
                answer.append("I");
            }
            else if(nickname.charAt(i) == 'w'){
                answer.append("vv");
            }
            else if(nickname.charAt(i) == 'W'){
                answer.append("VV");
            }
            else if(nickname.charAt(i) == 'O'){
                answer.append("0");
            }
            else{
                answer.append(nickname.charAt(i));
            }
        }
        // 바뀐 부분
        while (answer.length() < 4) {
            answer.append("o");
        }

//        if(answer.length() < 3){
//            answer.append("o");
//        }

        if(answer.length() > 8){
            answer = new StringBuilder(answer.substring(0, 8));
        }
        return answer.toString();
    }
}
