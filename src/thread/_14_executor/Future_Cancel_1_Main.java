package thread._14_executor;

import java.util.concurrent.*;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class Future_Cancel_1_Main {

    private static boolean mayInterruptIfRunning = true; // 변경
//    private static boolean mayInterruptIfRunning = false; // 변경

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new myTask());
        log("Future.state: " + future.state());

        // 일정 시간 후 취소 시도
        sleep(3000);

        // cancel() 호출
        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult1 = future.cancel(mayInterruptIfRunning);

        log("Future.state: " + future.state());
        log("cancel(" + mayInterruptIfRunning + ") result: " + cancelResult1);

        // 결과 확인
        try {
            log("Future result: " + future.get());
        } catch (CancellationException e) {
            log("Future 는 이미 취소 되었습니다.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.close();

    }

    static class myTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "Interrupted";
            }
            return "Completed";
        }
    }
}
