package thread._2_manyThread;

import thread.runnable.FirstRunnable;

import static util.ThreadLogger.log;

public class ManyThread_1 {

    public static void main(String[] args) {
        log("main() start");

        FirstRunnable runnable = new FirstRunnable();

        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        Thread thread3 = new Thread(runnable);
        thread3.start();

        log("main() end");
    }
}
