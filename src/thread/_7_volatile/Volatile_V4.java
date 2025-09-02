package thread._7_volatile;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

/**
 * volatile과  메모리 가시성
 */
public class Volatile_V4 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("flag = " + task.flag);
        t.start();

        sleep(1000);

        task.flag = false;
        log("flag = " + task.flag + ", count = " + task.count + " in main");
    }

    static class MyTask implements Runnable {

        volatile boolean flag = true;
         volatile long count;

        @Override
        public void run() {
            while (flag) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            /**
             * // count 값이 똑같음
             * 10:27:01.991 [     main] flag = false, count = 125172650 in main
             * 10:27:01.991 [     work] flag = false, count = 125172650 종료
             */
            log("flag = " + flag + ", count = " + count + " 종료");
        }
    }
}
