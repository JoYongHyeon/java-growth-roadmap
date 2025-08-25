package thread.test;

import static util.ThreadLogger.log;

/**
 * 여러 스레드 사용
 */
public class Counter_test_4_main {

    public static void main(String[] args) {
        Thread task1 = new Thread(new PrintWork("A", 1000), "Thread-A");
        Thread task2 = new Thread(new PrintWork("B", 500), "Thread-B");

        task1.start();
        task2.start();
    }

    static class PrintWork implements Runnable {
        private String content;
        private int sleepMs;

        public PrintWork(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
