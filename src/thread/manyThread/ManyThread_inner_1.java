package thread.manyThread;

import static util.ThreadLogger.log;

/**
 * 정적 중첩 클래스(특정 클래스 안에서만 사용되는 경우)
 */
public class ManyThread_inner_1 {


    public static void main(String[] args) {
        log("main() start");
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        log("main() end");
    }


    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            log("run()");
        }
    }
}
