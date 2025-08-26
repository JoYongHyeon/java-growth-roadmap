package thread._2_manyThread;

import static util.ThreadLogger.log;

/**
 * 익명 클래스 사용
 * 람다를 사용하여 메서드 전달
 */
public class ManyThread_inner_4 {

    public static void main(String[] args) {
        log("main() start");

        Thread thread = new Thread(() -> log("run()"));
        thread.start();

        log("main() end");
    }
}
