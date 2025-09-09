package thread._10_product_consumer;

import thread._10_product_consumer.BoundedQueue;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class BoundedQueue_V3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueue_V3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐가 가득 참, 생산자 대기");
            try {
                // RUNNABLE -> WAITING, 락 반납!!! 가장 중요
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");
        // 대기 스레드, WAIT -> BLOCKED
        notify();
        // 모든 대기 스레드, WAIT -> BLOCKED
//        notifyAll();
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기");

            try {
                wait();
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] 소비자 데이터 획득, notify() 호출");
        // 대기 스레드, WAIT -> BLOCKED
        notify();
        // 모든 대기 스레드, WAIT -> BLOCKED
//        notifyAll();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
