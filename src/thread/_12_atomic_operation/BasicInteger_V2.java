package thread._12_atomic_operation;

public class BasicInteger_V2 implements IncrementInteger {

    // volatile 변수 사용
    private volatile int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
