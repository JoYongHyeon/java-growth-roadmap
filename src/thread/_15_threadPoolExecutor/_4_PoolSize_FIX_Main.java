package thread._15_threadPoolExecutor;

import thread._14_executor.RunnableTask_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.util.ExecutorUtils.printState;
import static util.ThreadLogger.log;

public class _4_PoolSize_FIX_Main {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        log("pool 생성");
        printState(es);

        for (int i = 0; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask_1(taskName));
            printState(es, taskName);
        }
        es.close();
        log("== shutdown 완료 ==");
    }
}
