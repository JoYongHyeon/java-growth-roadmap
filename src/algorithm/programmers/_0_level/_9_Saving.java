package algorithm.programmers._0_level;

import java.io.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/250130
public class _9_Saving {

    public static void main(String[] args) throws IOException {

        int start  = 28;
        int before = 6;
        int after  = 8;
//        int start  = 75;
//        int before = 8;
//        int after  = 25;

        int money = start;
        int month = 1;
        while (money < 70) {
            money += before;
            month++;
        }

        while (money < 100) {
            money += after;
            month++;
        }

        System.out.println(month);
    }
}
