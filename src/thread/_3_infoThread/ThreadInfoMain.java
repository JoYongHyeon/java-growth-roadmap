package thread._3_infoThread;

import thread.runnable.FirstRunnable;

import static util.ThreadLogger.log;

/**
 * 기본으로 제공되는 main 스레드의 정보
 */
public class ThreadInfoMain {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);

        long threadId = mainThread.threadId();
        log("threadId() = " + threadId);

        String threadName = mainThread.getName();
        log("threadName() = " + threadName);

        int priority = mainThread.getPriority();
        log("priority() = " + priority);

        ThreadGroup threadGroup = mainThread.getThreadGroup();
        log("threadGroup() = " + threadGroup);

        Thread.State state = mainThread.getState();
        log("state() = " + state);
        System.out.println("================================================================================================");
        // 직접 생성한 스레드
        Thread myThread = new Thread(new FirstRunnable(), "my-Thread");
        log("myThread = " + myThread);

        long myThreadId = myThread.threadId();
        log("myThreadId() = " + myThreadId);

        String myThreadName = myThread.getName();
        log("myThreadName() = " + myThreadName);

        int myThreadPriority = myThread.getPriority();
        log("myThreadPriority() = " + myThreadPriority);

        ThreadGroup myThreadGroup = myThread.getThreadGroup();
        log("myThreadGroup() = " + myThreadGroup);

        Thread.State myThreadState = myThread.getState();
        log("myThreadState() = " + myThreadState);
    }
}
