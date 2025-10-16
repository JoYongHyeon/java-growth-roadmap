package algorithm.programmers._0_level;

// https://school.programmers.co.kr/learn/courses/30/lessons/340201
public class _7_Bus {

    public static void main(String[] args) {

        String[][] passengers = {
                {"On", "On", "On"},
                {"Off", "On", "-"},
                {"Off", "-", "-"}
        };

        int result =  solution(5, passengers);
        System.out.println("남은 빈 좌석 수 : " + result);

    }
    public static int solution(int seat, String[][] passengers) {

        int num_passenger = 0;

        for (int i = 0; i < passengers.length; i++) {
            num_passenger += func4(passengers[i]);
            num_passenger -= func3(passengers[i]);
        }

        return func1(seat - num_passenger);
    }

    public static int func1(int num) {
        if (0 > num) {
            return 0;
        } else {
            return num;
        }
    }

    public static int func2(int num) {
        if (num > 0) {
            return 0;
        } else {
            return num;
        }
    }

    public static int func3(String[] station){
        int num = 0;
        for(int i=0; i<station.length; i++){
            if(station[i].equals("Off")){
                num += 1;
            }
        }
        return num;
    }

    public static int func4(String[] station){
        int num = 0;
        for(int i=0; i<station.length; i++){
            if(station[i].equals("On")){
                num += 1;
            }
        }
        return num;
    }
}
