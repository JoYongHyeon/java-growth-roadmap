package thread._4_join;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class JoinMain_V0 {

    public static void main(String[] args) {

        log("Start");
        Thread thread1 = new Thread(new Job(), "job-1");
        Thread thread2 = new Thread(new Job(), "job-2");

        thread1.start();
        thread2.start();

        log("end");
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("작업 start");
            sleep(2000);
            log("작업 end");
        }
    }
}
