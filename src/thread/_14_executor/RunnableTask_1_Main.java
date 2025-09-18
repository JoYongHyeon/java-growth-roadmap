package thread._14_executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.util.ExecutorUtils.printState;
import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class RunnableTask_1_Main {

    public static void main(String[] args) {

        ThreadPoolExecutor es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        log("=== 초기 상태 ===");
        printState(es);
        es.execute(new RunnableTask_1("taskA"));
        es.execute(new RunnableTask_1("taskB"));
        es.execute(new RunnableTask_1("taskC"));
        es.execute(new RunnableTask_1("taskD"));
        log("=== 작업 수행 중 ===");
        printState(es);

        sleep(3000);
        printState(es);

        es.close();
        log("=== shutdown 완료 ===");
        printState(es);
    }
}
