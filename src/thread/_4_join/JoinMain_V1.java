package thread._4_join;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class JoinMain_V1 {

    public static void main(String[] args) {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "task-1");
        Thread thread2 = new Thread(task2, "task-2");

        thread1.start();
        thread2.start();


        // task-1, task-2 두 스레드의 작업이 끝나기도 전에 main 스레드가 종료되어버리기때문에 값은 0으로 뜸!!!
        log("task1.result = " + task1.sum);
        log("task2.result = " + task2.sum);

        int sumAll = task1.sum + task2.sum;
        log("task1 + task2 = " + sumAll);
        log("끝");
    }

    static class SumTask implements Runnable {

        int firstNum;
        int lastNum;
        int sum;

        public SumTask(int firstNum, int lastNum) {
            this.firstNum = firstNum;
            this.lastNum = lastNum;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            for (int i = firstNum; i <= lastNum; i++) {
                sum += i;
            }
            log("작업 완료" + sum);
        }
    }
}
