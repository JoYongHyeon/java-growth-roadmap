package thread._12_atomic_operation;

import java.util.ArrayList;
import java.util.List;

import static thread.util.ThreadUtils.sleep;

public class AtomicInteger_V1_Main {

    public static final int THREAD_COUNT = 1000;
    
    public static void main(String[] args) throws InterruptedException {
        test(new AtomicInteger_V1());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                sleep(10);
                incrementInteger.increment();
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result: " + result);
    }
}
