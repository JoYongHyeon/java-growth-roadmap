## ExecutorService 의 종료 메서드

### ExecutorService 서비스 종료

```java
void shutdown();
```
- 새로운 작업을 받지 않고, 이미 제출된 작업을 모두 완료한 후에 종료한다.
- 논 블로킹 메서드(이 메서드를 호출한 스레드는 대기하지 않고 즉시 다음 코드를 호출한다.)

```java
List<Runnable> shutdownNow();
```
- 실행 중인 작업을 중단하고, 대기 중인 작업을 반환하며 즉시 종료한다.
- 실행 중인 작업을 중단하기 위해 인터럽트를 발생시킨다.
- 논 블로킹 메서드

---
### ExecutorService 상태 확인

```java
boolean isShutdown();
```
- 서비스가 종료되었는지 확인한다.

```java
boolean isTerminated();
```
- `shutdown(), shutdownNow()` 호출 후, 모든 작업이 완료되었는지 확인한다.

---
### 작업 완료 대기
```java
boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;
```
- 서비스 종료시 모든 작업이 완료될 때까지 대기한다. 이때 지정된 시간까지만 대기한다.
- 블로킹 메서드

---
### close()
- `close()` 는 자바 19부터 지원하는 서비스 종료 메서드. 이메서드는 `shutdown()` 과 같다고 생각하면 된다.
- 더 정확히는 `shutdown()` 을 호출하고, 하루를 기다려도 작업이 완료되지 않으면 `shutdownNow()` 를 호출한다.
- 호출한 스레드에 인터럽트가 발생해도 `shutdownNow()` 를 호출한다.

---
## ExecutorService 종료 구현

`shutdown()` 을 호출해서 이미 들어온 모든 작업을 다 처리하고 서비스를 우아하게 종료하는 것이 가장 이상적이지만,
갑자기 요청이 너무 많이 들어와서 큐에 대기중인 작업이 너무 많아 작업 완료가 어렵거나, 작업이 너무 오래 걸리거나,
또는 버그가 발생해서 특정 작업이 끝나지 않을 수 있다.

이럴 때는 보통 종료하는 시간을 정한다. 예를 들어서 60초까지는 작업을 다 처리할 수 있게 기다리는 것이다.
그리고 60초가 지나면, 무언가 문제가 있다고 가정하고 `shutdownNow()`를 호출해서 작업들을 강제로 종료한다.


### close()
`close()` 의 경우 이렇게 구현되어있다.
`shutdown()`을 호출하고, 하루를 기다려도 작업이 완료되지 않으면 `shutdownNow()` 을 호출한다.
그런데 대부분 하루를 기다릴 수는 없다.

그래서 위의 설명한데로 우선 `shutdown()`을 통해 종료를 시도하고, 10초간 종료되지 않으면 
`shutdownNow()` 를 통해 강제 종료하는 방식을 구현해보자.

**참고로 구현할 `shutdownAndAwaitTermination()` 은 `ExecutorService` 공식 API 문서에서 제한하는 방식이다.**

`_4_ExecutorService_Shutdown_Main` 실행해보자.

### 실행 결과

```java
10:04:48.276 [     main] [pool=2, active=2, queuedTasks=2, completedTasks=0]
10:04:48.277 [pool-1-thread-1] taskA 시작
10:04:48.276 [pool-1-thread-2] taskB 시작
10:04:48.278 [     main] == shutdown 시작 ==
10:04:48.279 [     main] 서비스 정상 종료 시도
10:04:49.285 [pool-1-thread-2] taskB 완료
10:04:49.286 [pool-1-thread-2] taskC 시작
10:04:49.285 [pool-1-thread-1] taskA 완료
10:04:49.290 [pool-1-thread-1] longTask 시작
10:04:50.287 [pool-1-thread-2] taskC 완료
10:04:58.284 [     main] 서비스 정상 종료 실패 -> 강제 종료 시도
10:04:58.285 [pool-1-thread-1] 인터럽트 발생, sleep interrupted
10:04:58.286 [     main] == shutdown 완료 ==
10:04:58.286 [     main] [pool=0, active=0, queuedTasks=0, completedTasks=4]
Exception in thread "pool-1-thread-1" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
	at thread.util.ThreadUtils.sleep(ThreadUtils.java:14)
	at thread._14_executor.RunnableTask_1.run(RunnableTask_1.java:23)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Caused by: java.lang.InterruptedException: sleep interrupted
	at java.base/java.lang.Thread.sleep0(Native Method)
	at java.base/java.lang.Thread.sleep(Thread.java:509)
	at thread.util.ThreadUtils.sleep(ThreadUtils.java:11)
	... 4 more
```

### 작업 처리에 필요한 시간
- `taskA`, `taskB`, `taskC`: 1초
- `longTask` : 100초


### 서비스 종료
```java
es.shutdown();
```
- 새로운 작업을 받지 않는다. 처리 중이거나, 큐에 이미 대기중인 작업은 처리한다. 이후에 풀의 스레드를 종료한다.
- `shutdown()`은 블로킹 메서드가 아니다. 서비스가 종료될 때까지 `main` 스레드가 대기하지 않는다.
    `main`스레드는 바로 다음 코드를 호출한다.


```java
if (!es.awaitTermination(10, TimeUnit.SECONDS)){...}
```
- 블로킹 메서드이다.
- `main` 스레드는 대기하며 서비스 종료를 10초간 기다린다.
  - 만약 10초 안에 모든 작업이 완료된다면 `true`를 반환
- 여기서 `taskA`, `taskB`, `taskC` 의 수행이 완료된다. 그런데 `longTask` 는 10초가 지나도 완료되지 않음
  - 따라서 `false` 를 반환

---

### 서비스 정상 종료 실패 -> 강제 종료 시도
```java
log("서비스 정상 종료 실패 -> 강제 종료 시도");
es.shutdownNow();
// 작업이 취소될 때 까지 대기한다.
if (!es.awaitTermination(10, TimeUnit.SECONDS)){
    log("서비스가 종료되지 않았습니다.");        
}
```
- 정상 종료가 10초 이상 너무 오래 걸렸다.
- `shutdownNow()`를 통해 강제 종료에 들어간다. `shutdown()` 과 마찬가지로 블로킹 메서드가 아니다.
- 강제 종료를 하면 작업 중인 스레드에 인터럽트가 발생한다. 다음 로그를 통해 인터럽트를 확인 가능
- 인터럽트가 발생하면서 스레드도 작업을 종료하고, `shutdownNow()`를 통해 강제 shutdown 도 완료된다.

```java
10:04:58.284 [     main] 서비스 정상 종료 실패 -> 강제 종료 시도
10:04:58.285 [pool-1-thread-1] 인터럽트 발생, sleep interrupted
10:04:58.286 [     main] == shutdown 완료 ==
```
---

### 서비스 종료 실패
**그런데 왜 마지막에 강제 종료인 `es.shutdownNow()`를 호출한 다음에 왜 10초간 또 기다릴까?**
`shutdownNow()` 가 작접 중인 스레드에 인터럽트를 호출하는 것은 맞다.
인터럽트를 호출하더라도 여러가지 이유로 작업에 시간이 걸릴 수 있다. 인터럽트 이후에 자원을 정리하는
어떤 간단한 작업을 수행할 수 도 있다. 이런 시간을 기다려 주는 것.

