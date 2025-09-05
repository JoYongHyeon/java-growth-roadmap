package algorithm.codility.lesson_1;

import java.util.Scanner;

public class BinaryGap_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 지금 까지 본 gap 중 가장 긴 길이
        int maxGap = 0;
        // 현재 진행 중인 gap 의 길이 (-1 인 이유 : 아직 1을 만나지 않았을 때는 gap 을 세면 안되니까 "비활성화 상태" 표시
        int currentGap = -1;

        while (N > 0) {
            // N 을 2로 나눈 나머지 값 추출
            int bit = N % 2;

            // N == 9
            // 9 % 2 = 1
            // 4 % 2 = 0
            // 2 % 2 = 0
            // 1 % 2 = 1
            // 나머지가 1일 경우
            if (bit == 1) {
                if (currentGap > maxGap) {
                    maxGap = currentGap;
                }
                currentGap = 0; // 새로운 갭 시작
            } else if (currentGap >= 0) {
                currentGap++; // 0이면 갭 증가
            }

            N = N / 2; // 다음 비트로 이동
        }

        System.out.println(maxGap);
    }
}
