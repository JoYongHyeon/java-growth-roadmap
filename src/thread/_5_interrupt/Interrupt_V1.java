package thread._5_interrupt;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

/**
 * 특정 스레드의 작업을 중단하는 가장 쉬운 방법
 */
public class Interrupt_V1 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 flag=false");
        task.flag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                log("작업 중");
                // flag 의 값을 false 로 바꿔도 sleep() 으로 인해 다음 while 문이 올때까지 기다려야함
                sleep(3000);
            }
            log("자원 정리");
            log("작업 종료");
        }
    }
}
