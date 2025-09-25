package thread._15_threadPoolExecutor;

import thread._14_executor.RunnableTask_1;

import java.util.concurrent.*;

import static thread.util.ExecutorUtils.printState;
import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class _5_PoolSize_CACHE_Main {

    public static void main(String[] args) {

        ThreadPoolExecutor es = new ThreadPoolExecutor(
                0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS, new SynchronousQueue<>());
        log("pool 생성");
        printState(es);

        for (int i = 0; i <= 4; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask_1(taskName));
            printState(es, taskName);
        }

        sleep(3000);
        log("== 작업 수행 완료 == ");
        printState(es);

        sleep(3000);
        log("== maximumPoolSIze 대기 시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
