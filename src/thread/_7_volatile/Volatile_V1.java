package thread._7_volatile;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

/**
 * volatile과  메모리 가시성
 */
public class Volatile_V1 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("flag = " + task.flag);
        t.start();

        sleep(1000);
        log("flag 를 false 로 변경 시도");
        task.flag = false;
        log("flag = " + task.flag);
        log("main 종료");
    }

    static class MyTask implements Runnable {

        // work 스레드가 while 문에서 나오지 못하고 계속 실행된다. -> 메모리 가시성 문제
        boolean flag = true;

        @Override
        public void run() {
            log("task 시작");
            while (flag) {
                // flag 가 false 로 바뀌면 탈출
            }
            log("task 종료");
        }
    }
}
