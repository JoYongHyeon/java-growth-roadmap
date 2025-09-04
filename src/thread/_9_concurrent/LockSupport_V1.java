package thread._9_concurrent;

import java.util.concurrent.locks.LockSupport;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class LockSupport_V1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new LockSupportTest(), "Thread-1");
        thread1.start();

        // 잠시 대기하여 thread1 이 park 상태로 빠질 시간을 줌
        sleep(100);
        log("Thread-1 state: " + thread1.getState());

        log("main -> unpark(Thread-1)");
        // 1. unpark 사용
        LockSupport.unpark(thread1);
        // 2. interrupt() 사용
        // thread1.interrupt();
    }

    static class LockSupportTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
