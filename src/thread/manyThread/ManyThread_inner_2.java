package thread.manyThread;

import static util.ThreadLogger.log;

/**
 * 익명 클래스 사용
 * (특정 메서드 안에서만 간단히 정의하고 사용)
 */
public class ManyThread_inner_2 {

    public static void main(String[] args) {
        log("main() start");

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                log("run()");
            }
        };
    }
}
