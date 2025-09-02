package thread._7_volatile;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

/**
 * volatile과  메모리 가시성
 */
public class Volatile_V3 {

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

        boolean flag = true;
        long count;

        @Override
        public void run() {
            while (flag) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            // 모든 반복문이 종료되고 이 시점에 work 스레드의 캐시 메모리가 false 로 변경
            log("flag = " + flag + ", count = " + count + " 종료");
            /**
             * 두 스레드의 값이 다름
             *  main : 1031698859
             *  task : 1100000000
             */
        }
    }
}
