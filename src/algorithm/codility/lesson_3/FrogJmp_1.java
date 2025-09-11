package algorithm.codility.lesson_3;

public class FrogJmp_1 {

    public static void main(String[] args) {

        // 현재 위치
        int X = 10;
        // 도착지
        int Y = 85;
        // 점프 크기
        int D = 30;


        System.out.println((Y - X + D - 1) / D);

    }
}
