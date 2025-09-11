package thread._11_product_consumer_2;

import thread._10_product_consumer.BoundedQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.ThreadLogger.log;

public class BoundedQueue_V6_2 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueue_V6_2(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data);
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
