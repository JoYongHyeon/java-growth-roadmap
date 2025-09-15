package thread._12_atomic_operation;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInteger_V1 implements IncrementInteger {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void increment() {
        // 값을 하나 증가하고 증가된 결과를 반환
        atomicInteger.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }
}
