package thread._2_manyThread;

import thread.runnable.FirstRunnable;

import static util.ThreadLogger.log;

public class ManyThread_2 {

    public static void main(String[] args) {

        log("main() start");
        FirstRunnable runnable = new FirstRunnable();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable, "Thread-" + i);
            thread.start();
        }
        log("main() end");
    }
}
