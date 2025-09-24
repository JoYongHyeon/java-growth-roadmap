package algorithm.codility.lesson_5;

public class _1_PassingCars {

    public static void main(String[] args) {

        int[] A = {0, 1, 0, 1, 1};

        // 1. 0(오른쪽으로 가는 차) 으로 가면 1(왼쪽으로 가는 차) 를 어쨋든 다 만남.
        // 2. A[0] 은 1을 총 3번 만남. A[2] 는 1을 총 2번만남.
        // 3. 차례로 가면서 1을 만날때까지 0의 갯수를 카운트

        int eastCount = 0;
        int passingCars = 0;

        for (int car : A) {

            if (car == 0) {
                // 동쪽 -> 서쪽 차량 카운트
                eastCount++;
            } else if (car == 1) {
                // 동쪽 <- 서쪽 차량 카운트, 그리고 지금까지 동 -> 서쪽으로 가는 차량과 모두 만남
                passingCars += eastCount;

                // 10억이 넘으면 break
                if (passingCars > 1_000_000_000) {
                    passingCars = -1;
                    break;
                }
            }
        }
        System.out.println(passingCars);
    }
}
