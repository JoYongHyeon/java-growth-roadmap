package thread._9_concurrent;

import java.util.concurrent.locks.LockSupport;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class LockSupport_V2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new LockSupportTest(), "Thread-1");
        thread1.start();

        // 잠시 대기하여 thread1 이 park 상태로 빠질 시간을 줌
        sleep(100);
        log("Thread-1 state: " + thread1.getState());
    }

    static class LockSupportTest implements Runnable {

        @Override
        public void run() {
            log("park 시작, 2초 대기");
            LockSupport.parkNanos(2000_000000);;
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
