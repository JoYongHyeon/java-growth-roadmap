package thread._12_atomic_operation;

public class Increment_performance_main {

    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {

        test(new BasicInteger_V1());
        test(new BasicInteger_V2());
        test(new BasicInteger_V3());
        test(new AtomicInteger_V1());
    }

    private static void test(IncrementInteger incrementInteger) {
        long startMs = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }
        long endMs = System.currentTimeMillis();

        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms= " + (endMs - startMs));
    }
}
