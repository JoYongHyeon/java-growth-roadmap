package thread._10_product_consumer;

import static util.ThreadLogger.log;

public class BoundedQueue_V1_Producer implements Runnable{

    private BoundedQueue queue;
    private String request;

    public BoundedQueue_V1_Producer(BoundedQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("[생산 시도] " + request + " -> " + queue);
        queue.put(request);
        log("[생산 완료] " + request + " -> " + queue);
    }
}
