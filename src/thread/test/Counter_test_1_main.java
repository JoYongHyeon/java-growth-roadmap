package thread.test;

import static util.ThreadLogger.log;

/**
 * Thread 상속 구현(비추)
 */
public class Counter_test_1_main {

    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread();
        counterThread.start();

    }

    private static class CounterThread extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                log(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
