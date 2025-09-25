package thread._15_threadPoolExecutor;

import thread._14_executor.RunnableTask_1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.util.ExecutorUtils.printState;
import static util.ThreadLogger.log;

public class _6_PoolSize_DETAIL_Main {

//    static final int TASK_SIZE = 1100;  // 1. 일반
//    static final int TASK_SIZE = 1200;  // 2. 긴급
    static final int TASK_SIZE = 1201;  // 3. 거절

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor es = new ThreadPoolExecutor(
                100, 200, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

        printState(es);

        long startMs = System.currentTimeMillis();

        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;

            try {
                es.execute(new RunnableTask_1(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }
        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));
    }
}
