## AtomicInteger 원자적 연산

자바는 앞에서 직접 만들어 보았던 멀티스레드 상황에서 안전하게 증가 연산을 수행할 수 있는
`AtomicInteger` 라는 클래스를 제공한다. 

```java
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
```

`AtomicInteger_V1_Main` 

**실행 결과** : 1000

`AtomicInteger` 의 결과도 1000 인 것을 확인할 수 있다.

### 정리
`AtomicInteger` 는 멀티스레드 상황에 안전하고 또 다양한 값 증가, 감소 연산을 제공한다.
특정 값을 증가하거나 감소해야 하는데 여러 스레드가 공유해야 한다면, `AtomicInteger`를 사용

### 참고
`AtomicInteger`, `AtomicLong`, `AtomicBoolean` 등 다양한 `AtomicXxx` 클래스 존재

---

## 성능 테스트
`Increment_performance_main`

```java

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
```

### 1억 번 값 증가 테스트 실행 결과
```
BasicInteger_V1: ms= 5
BasicInteger_V2: ms= 633
BasicInteger_V3: ms= 979
AtomicInteger_V1: ms= 696
```

---

### BasicInteger_V1 
- 가장 빠르다.
- CPU 캐시를 적극 사용한다. (CPU 캐시의 위력을 알 수 있다.)
- 안전한 임계 영역도 없고, `volatile` 도 사용하지 않기 때문에 멀티스레드 상황에는 사용할 수 없다.
- 단일 스레드가 사용하는 경우에 효율적

### BasicInteger_V2 (volatile 사용)
- `volatile` 을 사용해서 CPU 캐시를 사용하지 않고 메인 메모리 사용
- 안전한 임계 영역이 없기 때문에 멀티스레드 상황에는 사용할 수 없다.
- 단일 스레드가 사용하기에는 `BasicInteger_V1` 보다 느리다. 그렇다고 멀티스레드 상황에도 안전하지 않음

### BasicInteger_V3 (synchronized 사용)
- `synchronized` 를 사용한 안전한 임계 영역이 있기 때문에 멀티스레드 상황에도 안전하게 사용 가능
- `AtomicInteger_V1` 보다 성능이 느림

### AtomicInteger_V1 (AtomicInteger 사용)
- 자바가 제공하는 `AtomicInteger`를 사용, 멀티스레드 상황에서 안전하게 사용 가능
- 성능도 `synchronized`, `Lock(ReentrantLock)` 을 사용하는 경우보다 1.5 ~ 2배 정도 빠르다.

---

### AtomicInteger_V1 을 사용하는게 왜 synchronized 를 사용하는 BasicInteger_V3 보다 빠를까?
`i++` 는 분명 원자적인 연산이 아니다. 따라서 `synchronized`, `Lock(ReentrantLock)` 과 같이
락을 통해 `임계 영역`을 만들어야 할 것 같지만, 
`AtomicInteger` 가 제공하는 `incrementAndGet()` 메서드는 락을 사용하지 않고, 원자적 연산을 만들어 낸다.

---
## CAS 연산
**강의 자료 중 일부**

참고: CAS 연산은 심화 내용이다. 이해가 어렵다면 가볍게 듣고 넘어가도 괜찮다. 왜냐하면 우리가 직접 CAS 연산을
사용하는 경우는 거의 없기 때문이다. 대부분 복잡한 동시성 라이브러리들이 CAS 연산을 사용한다. 우리는
AtomicInteger와 같은 CAS 연산을 사용하는 라이브러리들을 잘 사용하는 정도면 충분하다.**
