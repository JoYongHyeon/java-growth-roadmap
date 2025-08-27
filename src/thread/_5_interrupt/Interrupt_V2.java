package thread._5_interrupt;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class Interrupt_V2 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 thread.interrupt()");

        // interrupt() 를 호출했다고 즉각 InterruptedException 이 발생하는 것은 아님
        // sleep() 처럼 InterruptedException 을 던지는 메서드를 호출 하거나 호출 중일 때 만 예외가 발생함
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    log("작업 중");
                    // 인터럽트를 받은 스레드는 대기 상태에서 깨어나 RUNNABLE 상태가 됨
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                    log("interrupt message = " + e.getMessage());
                    log("state= " + Thread.currentThread().getState());
                }
                log("자원 정리");
                log("작업 종료");
            }
        }
    }
}
