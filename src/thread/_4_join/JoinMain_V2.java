package thread._4_join;


import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

// sleep() 을 사용하여 시간적으로 비효율적이다.
public class JoinMain_V2 {

    public static void main(String[] args) {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "task-1");
        Thread thread2 = new Thread(task2, "task-2");

        thread1.start();
        thread2.start();

        log("main 스레드 sleep() 으로 강제 기다림");
        sleep(3000);
        log("main 스레드 sleep() 깨어남");

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
