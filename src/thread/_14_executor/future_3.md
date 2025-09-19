## Future 

### Runnable 과 Callable 비교

```java
public interface Runnable {
    void run();
}
```
- `Runnable`의 `run()` 은 반환 타입이 void 이다. 따라서 값을 반환할 수 없다.
- 예외가 선언 되어 있지 않다. 따라서 해당 인터페이스를 구현하는 모든 메서드는 체크 예외를 던질 수 없다.
  - 참고로 자식은 부모의 예외 범위를 넘어설 수 없다. 부모에 예외가 선언되어 있지 않으므로 예외를 던질 수 없다.
  - 런타임은 제외다.

---
```java
package java.util.concurrent;

public interface Callable<V> {
    V call() throws Exception;
}
```
- `Callable` 의 `call()` 은 반환 타입이 제네릭 `V` 이다. 따라서 값을 반환할 수 있다.
- `throws Exception` 예외가 선언되어 있다. 따라서 해당 인터페이스를 구현하는 모든 메서드는 체크 예외인 `Exception` 과 그 하위 예외를 모두 던질 수 있다.
---

`Callable_1_Main` 실행해보자.
**참고로 `java.util.concurrent.Executors`가 제공하는 `newFixedThreadPool(size)` 를 사용하면 편리하게 `ExecutorService`를 생성할 수 있다.**


### 기존 코드
```java
ExecutorService es = new ThreadPoolExecutor(1,1,0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
```

### 편의 코드
```java
ExecutorService es = Executors.newFixedThreadPool(1);
```

### 실행 결과
```
11:00:18.599 [pool-1-thread-1] Callable 시작
11:00:20.608 [pool-1-thread-1] create value = 8
11:00:20.610 [pool-1-thread-1] Callable 완료
11:00:20.612 [     main] result value = 8
```
**먼저 `MyCallable`을 구현하는 부분을 보자.**
- 숫자를 반환하므로 반환할 제네릭 타입은 `<Integer>`로 선언
- 구현은 `Runnable`과 비슷하지만 유일한 차이는 결과를 필드에 담아두는 것이 아니라, 결과를
  반환한다는 점이다. 따라서 결과를 보관할 별도의 필드를 만들지 않아도 된다.

---

### submit
```java
//인터페이스 정의
<T> Future<T> submit(Callable<T> task); 
```
**`ExecutorService` 가 제공하는 `submit()` 을 통해 `Callable` 을 작업으로 전달할 수 있다.***

```java
Future<Integer> future = es.submit(new MyCallable());
```
- `MyCallable` 인스턴스가 블로킹 큐에 전달되고, 스레드 풀의 스레드 중 하나가 이 작업을 실행할 것이다.
- 작업의 처리 결과는 직접 반환되는 것이 아니라 `Future` 라는 특별한 인터페이스를 통해 반환

```java
Integer result = future.get();
```

---
## Executor 프레임워크의 강점

요청 스레드가 결과를 받아야 하는 상황이라면, `Callable`을 사용한 방식은 `Runnable`을 사용하는 방식보다
훨씬 편리하다. 이 과정에서 내가 스레드를 생성한다거나, `join()`으로 스레드를 제어하거나 한 코드는 하나도 없다.
심지어 `Thread` 라는 코드도 없다. 단순하게 `ExecutorService`에 필요한 작업을 요청하고 결과를 받아서 쓰면된다.


**하지만 여기서 조금 헷갈리는 부분이 생긴다.**
```java
    Future<Integer> future = es.submit(new MyCallable());
    Integer result = future.get();
```
`future.get()`을 호출하는 요청 스레드(`main`)는 `future.get()` 을 호출 했을 때 2가지 상황으로 나뉘게 된다.
- `MyCallable`작업을 처리하는 스레드 풀의 스레드가 작업을 완료했다.
- `MyCallable` 작업을 처리하는 스레드 풀의 스레드가 아직 작업을 완료하지 못했다.


**만약 작업을 처리중이라면 어떻게 될까?**
**또 왜 결과를 바로 반환하지 않고, 불편하게 `Future`라는 객체를 대신 반환할까?**
