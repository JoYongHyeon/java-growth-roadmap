## ExecutorService

**`ExecutorService` 는 여러 작업을 한 번에 편리하게 처리하는 `invokeAll()`, `invokeAny()` 기능을 제공**

### 작업 컬렉션 처리

**`invokeAll()`**
```java
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
        throws InterruptedException
```
- 모든 `Callable` 작업을 제출하고, 모든 작업이 완료될 때까지 기다린다.

```java
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
    long timeout, TimeUnit unit) throws InterruptedException
```
- 지정된 시간 내에 모든 `Callable` 작업을 제출하고 완료될 때까지 기다린다.


**`invokeAny()`**
```java
<T> T invokeAny(Collection<? extends Callable<T>> tasks)
        throws InterruptedException
```
- 하나의 `Callable` 작업이 완료될 때까지 기다리고, 가장 먼저 완료된 작업의 결과를 반환한다.
- 완료되지 않은 나머지 작업은 취소한다.


```java
<T> T invokeAny(Collection<? extends Callable<T>> tasks,
      long timeout, TimeUnit unit) throws InterruptedException
```
- 지정된 시간 내에 하나의 `Callable` 작업이 완료될 때까지 기다리고, 가장 먼저 완료된 작업의 결과를 반환한다.
- 완료되지 않은 나머지 작업은 취소한다.


**`invokeAll()`, `invokeAny()` 를 사용하면 한꺼번에 여러 작업을 요청할 수 있다.**

---

### ExecutorService - invokeAll()

`_2_ExecutorService_InvokeAll_Main` 

**한 번에 여러 작업을 제출하고, 모든 작업이 완료될 때 까지 기다린다.**

---

### ExecutorService - invokeAny()

`_3_ExecutorService_InvokeAny_Main`

**한 번에 여러 작업을 제출하고, 가장 먼저 완료된 작업의 결과를 반환, 이때 완료되지 않은 나머지 작업은 인터럽트**




