## BlockingQueue
스레드 관점에서 보면 큐가 특정 조건이 만족될 때까지 스레드의 작업을 차단(**blocking**)한다.

### 데이터 추가 차단
**큐가 가득 차면 데이터 추가 작업(`put()`)을 시도하는 스레드는 공간이 생길 때까지 차단된다.**

### 데이터 획득 차단
**큐가 비어 있으면 획득 작업(`take()`)을 시도하는 스레드는 큐에 데이터가 들어올 때까지 차단된다.**

---
대규모 시스템에서는 사실 `BlockingQueue` 하나만 잘 활용해도 `생산자–소비자` 문제를 손쉽게 해결할 수 있다.
하지만 이 강의에서는 곧바로 그런 고수준의 도구를 쓰는 대신, 
`생산자–소비자` 문제를 직접 구현하면서 멀티스레드의 기본 개념과 동작 원리를 체계적으로 배워 나가는 과정을 강조한다.

예를 들어, 왜 다른 스레드가 `BLOCKED` 상태에서 깨어나지 못하는지,
`synchronized`, `Object.wait()`, `Object.notify()` 같은 원시적인 동기화 도구가 어떤 역할을 하고 동시에 어떤 한계가 있는지, 
이러한 제약을 극복하기 위해 `ReentrantLock`과 `Condition` 같은 새로운 도구가 왜 만들어졌는지를 몸소 경험할 수 있다. 
또한 생산자와 소비자의 역할을 명확히 분리해야 하는 이유 역시 실습 속에서 자연스럽게 체득할 수 있다.

즉, 지금까지의 실습 내용은 단순히 문제를 해결하는 것에 목적을 두기보다는, 
멀티스레드 프로그래밍의 기초를 학습하고 그 원리를 깊이 이해하기 위한 가장 좋은 예시로 `생산자–소비자` 문제를 다루고 있는 것이다.
---
## BlockingQueue - 예제
자바는 생산자 소비자 문제를 해결하기 위해 `java.util.concurrent.BLockingQueue`라는 특별한 멀티스레드 자료 구조를 제공한다.
**(주요 메서드만 정리)**
```java
package java.util.concurrent;
public interface BlockingQueue<E> extends Queue<E> {
    // 데이터 추가 메서드
    boolean add(E e);
    boolean offer(E e);
    void put(E e) throws InterruptedException;
    boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;
    
    // 데이터 획득 메서드
    E take() throws InterruptedException;
    E poll(long timeout, TimeUnit unit) throws InterruptedException;
    boolean remove(Object o);

//...
```
### BlockingQueue 인터페이스의 대표적인 구현체
- `ArrayBlockingQueue`: **배열 기반으로 구현되어 있고, 버퍼의 크기가 고정되어 있다.**
- `LinkdedBLockingQueue`: **링크 기반으로 구현되어 있고, 버퍼의 크기를 고정할 수도, 또는 무한하게 사용할 수도 있다.**

### 참고
- `Deque`용 동시성 자료 구조인 `BlockingDeque`도 있다.
---
## BlockingQueue 코드 확인

### ArrayBlockingQueue.put()
```java
    // 참고로 주요 코드만 가지고옴
    final Object[] items;
    int count;
    ReentrantLock lock;
    Condition notEmpty; //소비자 스레드가 대기하는 condition
    Condition notFull;  //생산자 스레드가 대기하는 condition
    
    public void put(E e) throws InterruptedException {
        Objects.requireNonNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notFull.await();
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }
```
우리가 `BoundedQueue_V5` 와 거의 똑같이 구현되어 있다.
`ArrayBlockingQueue`는 내부에서 `ReentrantLock` 을 사용한다.
그리고 `생산자` 전용 대기실과 `소비자` 전용 대기실이 있다.

그나마 `BoundedQueue_V5` 와 차이가 있다면 인터럽트가 걸릴 수 있도록, `lock.lock()` 대신에
`lock.lockInterruptibly()`을 사용한 점과, 내부 자료 구조의 차이 정도이다.
(**참고로 lock.lock() 는 인터럽트를 무시한다.**)

`BoundedQueue_V6_Main` 실행결과는 같다.
---
## BlockingQueue 기능 설명
예를 들어 큐의 한계가 1000개라고 가정하자. 생산자 스레드는 순간적으로 1000개가 넘는 요청을 큐에 담을 수 있다.
소비자 스레드는 한 번에 겨우 10개 정도의 요청만 처리할 수 있다. 이상황에서 생산자 스레드는 계속 생산을 시도한다.
결국 소비가 생산을 따라가지 못하고, 큐가 가득 차게 된다.

이런 상황이 되면 수 많은 생산자 스레드는 큐 앞에서 대기하고, 결국 고객도 응답을 받지 못하고 무한 대기하게 된다.
고객 입장에서 무작정 무한 대기하고 결과도 알 수 없는 상황이 가장 나쁜 상황일 것이다.

이렇게 생산자 스레드가 큐에 데이터를 추가할 때 큐가 가득 찬 경우, 또는 큐에 데이터를 추가하기 위해 너무 오래 대기한 경우에는 
데이터 추가를 포기하고, 요청을 처리할 수 없다거나, 또는 나중에 다시 시도해달라고 하는 것이 더 나은 선택일 것이다.

### 큐가 가득 찼을 때 생각할 수 있는 선택지는 4가지가 있다.
- **예외를 던진다. 예외를 받아서 처리한다.**
- **대기하지 않는다. 즉시 false 반환**
- **대기한다.**
- **특정 시간 만큼만 대기한다.**

**이런 문제를 해결하기 위해 `BlockingQueue`는 각 상황에 맞는 다양한 메서드를 제공한다.
`BlockingQueue 의 다양한 기능 - 공식 API 문서`

| Operation   | Throws Exception | Special Value | Blocks         | Times Out            |
|-------------|------------------|---------------|----------------|----------------------|
| Insert(추가)  | add(e)          | offer(e)      | put(e)         | offer(e, time, unit) |
| Remove(제거)  | remove()        | poll()        | take()         | poll(time, unit)     |
| Examine(관찰) | element()       | peek()        | not applicable | not applicable       |

### Throws Exception - 대기시 예외
- `add(e)` : 지정된 요소를 큐에 추가하며, 큐가 가득 차면 `IllegalStateException` 예외를 던진다.
- `remove()` : 큐에서 요소를 제거하며 반환한다. 큐가 비어있으면 `NoSuchElementException` 예외를 던짐
- `element()` : 큐의 머리 요소를 반환하지만, 요소를 큐에서 제거하지 않는다. 큐가 비어있으면 `NoSuchElementException` 예외를 던진다.


### Special Value - 대기시 즉시 반환
- `offer(e)` : 지정된 요소를 큐에 추가하려고 시도하며, 큐가 가득 차면 `false` 반환
- `poll()` : 큐에 요소를 제거하고 반환한다. 큐가 비어 있으면 `null` 반환
- `peek()` : 큐의 머리 요소를 반환하지만, 요소를 큐에서 제거하지 않는다. 비어있으면 `null` 반환

### Blocks - 대기
- `put(e)` : 지정된 요소를 큐에 추가할 때까지 대기. 큐가 가득 차면 공간이 생길 때까지 대기
- `take()` : 큐에서 요소를 제거하고 반환한다. 큐가 비어 있으면 요소가 준비될 때까지 대기한다.
- `Examine(관찰)` : 해당 사항 없음.

### Times Out - 시간 대기
- `offer(e, time, unit)` : 지정된 요소를 큐에 추가하려고 시도하며, 지정된 시간 동안 큐가 비워지기를 기다리다가 시간이 초과되면 `false` 반환
- `poll(time, unit)` : 큐에서 요소를 제거하고 반환한다. 큐에 요소가 없다면 지정된 시간 동안 요소가 준비되기를 기다리다가 시간이 초과되면 `null` 반환
- `Examine(관찰)` : 해당 사항 없음

```java
// 다음 두 가지 기능을 이용한 예제를 만들어 보자.
// offer(e) : 지정된 요소를 큐에 추가하려고 시도하며, 큐가 가득 차면 false 반환
// poll() : 큐에 요소를 제거하고 반환한다. 큐가 비어 있으면 null 반환
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
```
`BoundedQueue_V6_2_Main` 실행 결과를 확인 해보자.
- 버퍼가 가득 차있는 경우 데이터를 추가하지 않고 즉시 `false`를 반환한다.
- 버퍼에 데이터가 없는 경우 대기하지 않고 `null`을 반환한다.
