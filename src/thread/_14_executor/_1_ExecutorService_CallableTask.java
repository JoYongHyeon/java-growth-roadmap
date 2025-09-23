package thread._14_executor;

import java.util.concurrent.Callable;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class _1_ExecutorService_CallableTask implements Callable<Integer> {

    String name;
    private int sleepMs = 1000;

    public _1_ExecutorService_CallableTask(String name) {
        this.name = name;
    }

    public _1_ExecutorService_CallableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public Integer call() throws Exception {
        log(name + " 실행");
        sleep(sleepMs);
        log(name + " 완료, return = " + sleepMs);
        return sleepMs;
    }
}
