package thread.test;

/**
 * 익명 클래스 구현
 */
public class Counter_test_3_main {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        runnable.run();
    }
}
