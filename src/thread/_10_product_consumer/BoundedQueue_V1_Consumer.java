package thread._10_product_consumer;

import static util.ThreadLogger.log;

public class BoundedQueue_V1_Consumer implements Runnable {

    private BoundedQueue queue;

    public BoundedQueue_V1_Consumer(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도]    ? <- " + queue);
        String data = queue.take();
        log("소비 완료] " + data + " <- " + queue);
    }
}
