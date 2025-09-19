## Future - 분석

**다음 코드를 보자**
```java
Future<Integer> future = es.submit(new MyCallable());
```
- `submit()` 의 호출로 `MyCallable의 인스턴스를 전달한다.
- 이때 반환값을 보면 숫자 대신에 `Future` 객체를 반환한다.
- 생각해보면 `MyCallable` 이 즉시 실행되어서 즉시 결과를 반환하는 것은 불가능하다.
  왜냐하면 `MyCallable`은 즉시 실행되는 것이 아니다. 스레드 풀의 스레드가 미래의 어떤 시점에 이 코드를 대신 실행해야 한다.
- `MyCallable.call()` 메서드는 호출 스레드가 실행하는 것도 아니고, 스레드 풀의 다른 스레드가 실행하기 때문에
  언제 실행이 완료되어서 결과를 반환할 지 알 수 없다.
- 따라서 결과를 즉시 받는 것은 불가능하다. 이런 이유로 `es.submit()`은 `MyCallable`의 결과를 
  반환하는 대신에 `MyCallable`의 결과를 나중에 받을 수 있는 `Future` 라는 객체를 대신 제공한다.
- 정리하면 `Future` 는 전달한 작업의 미래이다. 이 객체를 통해 전달한 작업의 미래 결과를 받을 수 있다.

---
### Future - 어떻게 작동하는가?

`Callable_2_Main` 실행결과를 보자
```java
11:21:46.163 [     main] submit() 호출
11:21:46.165 [pool-1-thread-1] Callable 시작
11:21:46.166 [     main] future 즉시 반환, future = java.util.concurrent.FutureTask@6576fe71[Not completed, task = thread._14_executor.Callable_2_Main$MyCallable@7eda2dbb]
11:21:46.166 [     main] future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING
11:21:48.174 [pool-1-thread-1] create value = 9
11:21:48.175 [pool-1-thread-1] Callable 완료
11:21:48.176 [     main] future.get() [블로킹] 메서드 호출 완료 -> main 스레드 RUNNABLE
11:21:48.177 [     main] result value = 9
11:21:48.178 [     main] future 완료, future = java.util.concurrent.FutureTask@6576fe71[Completed normally]
```


### 정리
```java
Future<Integer> future = es.submit(new MyCallable());
```
- `Future`는 작업의 미래 결과를 받을 수 있는 개체이다.
- `submit()`호출 시 `future` 는 즉시 반환된다. 덕분에 요청 스레드(`main`)는 블로킹 되지 않고,
  필요한 작업을 할 수 있다.


```java
Integer result = future.get();
```
- 작업 결과가 필요하면 `Future.get()` 을 호출하면 된다.
- **Future 가 완료 상태**: `Future`가 완료 상태면 `Future`에 결과도 포함되어 있다. 이 경우 요청 스레드는 대기하지 않고, 값을 즉시 받는다.
- **Future 가 완료 상태가 아님**: 작업이 아직 수행되지 않았거나 또는 수행중으로, 이때는 어쩔 수 없이 요청 스레드가 결과를 받기 위해 블로킹 상태로 대기

---
### Future 가 필요한 이유?

