package thread._14_executor;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class RunnableTask_1 implements Runnable {

    private final String name;
    private int sleepMs = 1000;

    public RunnableTask_1(String name) {
        this.name = name;
    }

    public RunnableTask_1(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public void run() {
        log(name + " 시작");
        sleep(sleepMs);
        log(name + " 완료");
    }
}
