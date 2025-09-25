package thread._15_threadPoolExecutor;

import thread._14_executor.RunnableTask_1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.util.ExecutorUtils.printState;
import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class _2_PoolSize_Main {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor es = new ThreadPoolExecutor(
                2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);

        printState(es);

        es.execute(new RunnableTask_1("task1"));
        printState(es, "task1");
        es.execute(new RunnableTask_1("task2"));
        printState(es, "task2");
        es.execute(new RunnableTask_1("task3"));
        printState(es, "task3");
        es.execute(new RunnableTask_1("task4"));
        printState(es, "task4");
        es.execute(new RunnableTask_1("task5"));
        printState(es, "task5");
        es.execute(new RunnableTask_1("task6"));
        printState(es, "task6");

        try {
            es.execute(new RunnableTask_1("task7"));
        } catch (RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생 " + e);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
