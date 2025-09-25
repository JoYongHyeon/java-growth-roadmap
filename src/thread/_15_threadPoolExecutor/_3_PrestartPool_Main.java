package thread._15_threadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.util.ExecutorUtils.printState;

public class _3_PrestartPool_Main {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1000);

        printState(es);
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) es;
        poolExecutor.prestartAllCoreThreads();
        printState(es);
    }
}
