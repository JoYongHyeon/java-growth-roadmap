## 원자적 연산(atomic operation)
- 연산이 더 이상 나눌 수 없는 단위로 수행되는 것
- 다른 연산과 간섭 없이 완전히 실행되거나 전혀 실행되지 않는 성질을 가지고 있음.

**멀티스레드 상황에서 다른 스레드의 간섭 없이 안전하게 처리되는 연산이라는 뜻**

### 원자적 연산
```java
// 오른쪽에 있는 1의 값은 왼쪽의 i 변수에 대입
volatile int i = 0;
i = 1;
```

### 원자적 연산 X
```java
// 이 연산은 다음 순서로 나누어 실행된다.
i = i + 1;
```
- 오른쪽에 있는 i 의 값을 읽는다. i 의 값은 10이다.
- 읽은 10에 1을 더해서 11을 만든다.
- 더한 11을 왼쪽의 i 변수에 대입한다.

**원자적 연산은 멀티스레드 상황에서 아무런 문제가 발생하지 않는다. 하지만 원자적 연산이 아닌 경우에는
`synchronized` 블럭이나 `Lock` 등을 사용해서 안전한 임계 영역을 만들어야 한다.**
---
- `BasicInteger_V1` 클래스의 `increment()`를 호출하면 `value++` 를 통해서 값을 하나 증가한다.
  - `value` 값은 인스턴스의 필드이기 때문에, 여러 스레드가 공유할 수 있다. 이렇게 공유 가능한 자원예 
    `++`와 같은 원자적이지 않은 연산을 사용하면 멀티스레드 상황에 문제가 될 수 있다.
---

**다음 예제를 보자**
`IncrementThreadMain_V1`
```java
public class IncrementThreadMain_V1 {

    public static final int THREAD_COUNT = 1000;
    
    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger_V1());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                sleep(10);
                incrementInteger.increment();
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result: " + result);
    }
}
```
**위 소스를 분석해보면**
- `THREAD_COUNT` 수 만큼 스레드를 생성하고 `incrementInteger.increment()` 를 호출
- 스레드를 1000개 생성 후 `increment()` 메서드 1000 번 호출
- `run()` 메서드에 `sleep(10)`을 두어, 최대한 많은 스레드가 동시에 `increment()` 를 호출하도록 함.

**예상 값** : `increment()` 메서드가 1000번 호출 되었기 때문에 결과는 1000이 나와야 한다.

**실행 결과** : 1000 보다 작은 값 중 하나

---

**그렇다면 이전에 학습했던 `volatile` 을 적용하면 해결이 될까? 두번째 예제를 만들어보자.**

`IncrementThreadMain_V2`
```java
// 1. volatile 추가
private volatile int value;

// 2. IncrementThread_Main_V2 호출 변수 변경
test(new BasicInteger_V2());
```

**실행 결과** : 똑같이 1000 미만의 값이 나온다.

---
`Volatile_V1.md` 에서 설명 했던 것 처럼 `volatile` 은 `CPU` 사이에 발생하는 `캐시 메모리`와 `메인 메모리가`
가 동기화 되지 않는 문제를 해결할 뿐이다.
`volatile`을 사용하면 CPU 의 캐시 메모리를 무시하고, 바로 메인 메모리를 직접 사용하도록 하지만, 
지금 위 `IncrementThreadMain_V2`에서 생기는 문제는 그것 보다는 **연산 자체가 나누어져 있기 때문에 발생한다.**
`volatile` 은 연산 자체를 원자적으로 묶어주는 기능이 아니다.

**이렇게 연산 자체가 나누어진 경우에는 `synchronized`블럭이나 `Lock` 등을 사용해서 안전한 임계 영역을 만들어야 한다.**

---
**다음 synchorinized 를 사용한 예제를 보자**
```java
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
```
- `value++` 연산은 `synchronized` 를 통해 임계 영역 안에서 안전하게 수행되어 한번에 하나의 스레드만 할당된다.

`IncrementThreadMain_V3` 실행
**실행 결과** : 1000
