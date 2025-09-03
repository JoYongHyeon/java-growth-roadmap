package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);

            int num = 1;
            int length = 1;

            // num = 1, n = 3
            while (num % n != 0) {

                num = (num * 10 + 1) % n;
                length++;
            }

            System.out.println(length);
        }
    }
}
