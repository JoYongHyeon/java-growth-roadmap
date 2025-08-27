package thread._5_interrupt;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

/**
 * thread.interrupt() : 해당 스레드에 인터럽트 신호를 보냄 (상태 플러그를 true 로 바꿈)
 * thread.isInterrupted() : 해당 스레드의 인터럽트 상태를 반환 (true/flase)
 * thread.interrupted() : 현재 실행 중인 스레드의 인터럽트 상태 확인 + 상태 플래그를 초기화 (false 로 리셋)
 */
public class Interrupt_V4 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            // 인터럽트 상태 변경
            while (!Thread.interrupted()) {
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(1000);
                log("자원 정리 완료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }
            log("작업 종료");
        }
    }
}
