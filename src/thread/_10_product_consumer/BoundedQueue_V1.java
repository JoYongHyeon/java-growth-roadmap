package thread._10_product_consumer;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.ThreadLogger.log;

public class BoundedQueue_V1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueue_V1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if (queue.size() == max) {
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
