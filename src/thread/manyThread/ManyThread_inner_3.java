package thread.manyThread;

import static util.ThreadLogger.log;

/**
 * 익명 클래스 사용
 * (변수 없이 직접 전달)
 */
public class ManyThread_inner_3 {

    public static void main(String[] args) {
        log("main() start");

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                log("run");
            }
        });
        thread.start();

        log("main() end");
    }
}
