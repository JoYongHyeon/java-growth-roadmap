package algorithm.baekJoon.course_51.divisor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_4_17425 {
    static final int MAX = 1_000_000;
    static long[] g = new long[MAX + 1]; // g(n) 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1️⃣ g(n) 미리 전처리
        for (int i = 1; i <= MAX; i++) {
            for (int j = i; j <= MAX; j += i) {
                g[j] += i; // i는 j의 약수
            }
        }
        for (int i = 1; i <= MAX; i++) {
            g[i] += g[i - 1]; // 누적합으로 g(n) 완성
        }

        // 2️⃣ 입력 처리
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(g[N]).append("\n");
        }

        System.out.print(sb);
    }
}
