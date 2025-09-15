package thread._12_atomic_operation;


// synchronized 사용
public class BasicInteger_V3 implements IncrementInteger {

    private int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
