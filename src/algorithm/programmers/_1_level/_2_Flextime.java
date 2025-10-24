package algorithm.programmers._1_level;

import java.util.stream.IntStream;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/388351"></a>
 * 프로그래머스: 플렉스타임 문제123
 * 각 직원의 희망 출근 시각과 실제 출근 로그를 기반으로
 * 평일 기준 5일 이상 제시간(희망시간 + 10분 이내) 출근한 직원 수를 계산
 */
public class _2_Flextime {

    public static void main(String[] args) {
        // 직원별 희망 출근 시각
        int[] schedules = {700, 800, 1100};
        int[][] timeLogs = {
                {710, 2359, 1050, 700, 650, 631, 659},
                {800, 801, 805, 800, 759, 810, 809},
                {1105, 1001, 1002, 600, 1059, 1001, 1100}
        };

        // 1=월요일, 7=일요일 기준 (예: 5=금요일)
        int startDay = 5;

        int result = countOnTimeEmployees(schedules, timeLogs, startDay);
        System.out.println(result);
    }

    /**
     * 전체 직원 중 제시간 출근한 직원 수를 계산
     */
    private static int countOnTimeEmployees(int[] schedules, int[][] timeLogs, int startDay) {
        // 직원 인덱스(i) 순회 → 조건 만족하는 경우만 필터링 후 카운트
        return (int) IntStream.range(0, schedules.length)
                .filter(i -> isEmployeeOnTime(schedules[i], timeLogs[i], startDay))
                .count();
    }

    /**
     * 특정 직원이 평일 기준 5일 이상 제시간 출근했는지 판별
     */
    private static boolean isEmployeeOnTime(int schedule, int[] logs, int startDay) {
        int allowedTime = addMinutes(schedule, 10); // 희망 시각 + 10분 허용
        int onTimeDays = 0;

        // 주어진 주(startDay 기준) 7일 간 출근 로그 검사
        for (int d = 0; d < logs.length; d++) {
            int dayOfWeek = ((startDay + d - 1) % 7) + 1; // 요일 계산 (1~7 반복)
            if (!isWeekend(dayOfWeek) && logs[d] <= allowedTime) {
                onTimeDays++; // 평일에 제시간 출근 시 카운트 증가
            }
        }
        return onTimeDays >= 5; // 평일 기준 5일 이상이면 ‘정시 출근자’
    }

    /**
     * 주어진 요일이 주말(토, 일)인지 확인
     */
    private static boolean isWeekend(int dayOfWeek) {
        return dayOfWeek == 6 || dayOfWeek == 7;
    }

    /**
     * HHmm 형식의 시각에 분 단위로 덧셈 처리
     * 예: addMinutes(958, 10) → 1008
     */
    private static int addMinutes(int time, int minutesToAdd) {
        int hour = time / 100;
        int minute = time % 100 + minutesToAdd;

        // 60분 이상일 경우 시(hour) 증가 및 분(minute) 조정
        hour += minute / 60;
        minute %= 60;

        return hour * 100 + minute;
    }
}
