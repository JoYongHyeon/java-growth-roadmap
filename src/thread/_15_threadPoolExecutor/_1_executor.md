## Executor 스레드 풀 관리

`ExecutorService` 의 기본 구현체인 `ThreadPoolExecutor` 의 생성자는 다음 속성을 사용
- `corePoolSize` : 스레드 풀에서 관리되는 기본 스레드의 수
- `maximumPoolSize` : 스레드 풀에서 관리되는 최대 스레드 수
- `keepAliveTime`, `TimeUnit unit` : 기본 스레드 수를 초과해서 만들어진 초과 스레드가 생존할 수 있는 대기 시간,
이 시간 동안 처리할 작업이 없다면 초과 스레드는 제거.
- 
- `BlockingQueue workQueue` : 작업을 보관할 블로킹 큐


**`corePoolSize` 와 `maximumPoolSize` 의 차이를 알아보기 위해 다음을 실행해보자.**

`_2_PoolSize_Main`

```java
ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor es = new ThreadPoolExecutor(
                2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
```
- 작업을 보관할 블로킹 큐의 구현체로 `ArrayBlockingQueue(2)` 를 사용. 최대 2개까지 작업 큐에 보관 가능
- `corePoolSize=2`, `maximumPoolSize=4` 를 사용, 기본 스레드 2개, 최대 스레드는 4개로 설정
  - 스레드 풀에 기본 2개의 스레드를 운영한다. 요청이 많을 경우 스레드 풀은 최대 4개까지 스레드를 증가
- `3000`, `TimeUnit.MILLISECONDS` 
  - 초과 스레드가 생존할 수 있는 대기 시간.
  - 여기서는 3000 밀리초(3초)를 설정했으므로, 3초간 작업을 하지 않고 대기하면 스레드 풀에서 제거된다.


### 실행 결과
```
10:26:33.629 [     main] [pool=0, active=0, queuedTasks=0, completedTasks=0]
10:26:33.633 [pool-1-thread-1] task1 시작
10:26:33.638 [     main] task1 -> [pool=1, active=1, queuedTasks=0, completedTasks=0]
10:26:33.639 [     main] task2 -> [pool=2, active=2, queuedTasks=0, completedTasks=0]
10:26:33.639 [     main] task3 -> [pool=2, active=2, queuedTasks=1, completedTasks=0]
10:26:33.639 [     main] task4 -> [pool=2, active=2, queuedTasks=2, completedTasks=0]
10:26:33.639 [pool-1-thread-2] task2 시작
10:26:33.639 [     main] task5 -> [pool=3, active=3, queuedTasks=2, completedTasks=0]
10:26:33.639 [pool-1-thread-3] task5 시작
10:26:33.640 [     main] task6 -> [pool=4, active=4, queuedTasks=2, completedTasks=0]
10:26:33.640 [pool-1-thread-4] task6 시작
10:26:33.640 [     main] task7 실행 거절 예외 발생 java.util.concurrent.RejectedExecutionException: Task thread._14_executor.RunnableTask_1@277050dc rejected from java.util.concurrent.ThreadPoolExecutor@2f4d3709[Running, pool size = 4, active threads = 4, queued tasks = 2, completed tasks = 0]
10:26:34.639 [pool-1-thread-1] task1 완료
10:26:34.639 [pool-1-thread-1] task3 시작
10:26:34.643 [pool-1-thread-4] task6 완료
10:26:34.643 [pool-1-thread-4] task4 시작
10:26:34.644 [pool-1-thread-2] task2 완료
10:26:34.645 [pool-1-thread-3] task5 완료
10:26:35.644 [pool-1-thread-1] task3 완료
10:26:35.645 [pool-1-thread-4] task4 완료
10:26:36.646 [     main] == 작업 수행 완료 ==
10:26:36.646 [     main] [pool=4, active=0, queuedTasks=0, completedTasks=6]
10:26:39.651 [     main] == maximumPoolSize 대기 시간 초과 ==
10:26:39.651 [     main] [pool=2, active=0, queuedTasks=0, completedTasks=6]
10:26:39.651 [     main] == shutdown 완료 ==
10:26:39.652 [     main] [pool=0, active=0, queuedTasks=0, completedTasks=6]
```

### Executor 스레드 풀 관리
1. 작업을 요청하면 core 사이즈 만큼 스레드를 만든다.
2. core 사이즈를 초과하면 큐에 작업을 넣는다.
3. 큐를 초과하면 max 사이즈 만큼 스레드를 만든다.
4. max 사이즈를 초과하면 요청을 거절한다. 예외가 발생한다.

---
## 스레드 미리 생성하기

**응답시간이 아주 중요한 서버라면, 고객의 첫 요청을 받기 전에 스레드를 스레드 풀에 미리 생성해두고 싶을 수 있다.**
스레드를 미리 생성해두면, 처음 요청에서 사용되는 스레드의 생성 시간을 줄일 수 있다.
`ThreadPoolExecutor.prestartAllCoreThreads()` 를 사용하면 기본 스레드를 미리 생성할 수 있다.

**참고로 `ExecutorService` 는 이 메서드를 제공하지 않는다.**

---

`_3_PrestartPool_Main` 

### 결과
```java
10:43:40.931 [     main] [pool=0, active=0, queuedTasks=0, completedTasks=0]
10:43:40.977 [     main] [pool=1000, active=0, queuedTasks=0, completedTasks=0]
```

---
## Executor - 고정 풀 전략

### Executor 스레드 풀 관리 - 다양한 전략
**`ThreadPoolExecutor` 를 사용하면 스레드 풀에 사용되는 숫자와 블로킹 큐등 다양한 속성을 조절할 수 있다.**
- `corePoolSize` : 스레드 풀에서 관리되는 기본 스레드의 수
- `maximumPoolSize` : 스레드 풀에서 관리되는 최대 스레드 수
- `keepAliveTime`, `TImeUnit unit` : 기본 스레드 수를 초과해서 만들어진 스레드가 생존할 수 있는 대기 시간,
이 시간 동안 처리할 작업이 없다면 초과 스레드는 제거
- `BlockingQueue workQueue` : 작업을 보관할 블로킹 큐


**이런 속성들을 조절하면 자신에게 맞는 스레드 풀 전략을 사용할 수 있다.**
- **newSingleThreadPool()** : 단일 스레드 풀 전략
- **newFixedThreadPool(nThreads)** : 고정 스레드 풀 전략
- **newCachedThreadPool()** : 캐시 스레드 풀 전략

---

## Executor - 고정 풀 전략

**newFixedThreadPool(nThreads)**
- 스레드 풀에 `nThreads` 만큼의 기본 스레드를 생성한다. 초과 스레드는 생성하지 않는다.
- 큐 사이즈에 제한이 없다. (`LinkedBlockingQueue`)
- 스레드 수가 고정되어 있기 때문에 CPU, 메모리 리소스가 어느정도 예측 가능한 안정적인 방식

```java
new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>())
```

`_4_PoolSize_FIX_Main`

### 특징
스레드 수가 고정되어 있기 때문에 CPU, 메모리 리소스가 어느정도 예측 가능한 안정적인 방식
큐 사이즈도 제한이 없어서 작업을 많이 담아두어도 문제가 없다.

### 주의 
가장 큰 장점이 상황에 따라 가장 큰 단점이 되기도 한다.

**상황1 - 점진적인 사용자 확대**
- 개발한 서비스가 잘 되어서 사용자가 점점 늘어난다.
- 고정 스레드 전략을 사용해서 서비스를 안정적으로 잘 운영했는데, 언젠가부터 응답이 점전 느려진다고 항의한다.

**상황2 - 갑작스런 요청 증가**
- 마케팅 팀의 이벤트가 대성공 하면서 갑자기 사용자가 폭증
- 고객은 응답을 받지 못한다고 항의

**이유**
- 개발자는 급하게 CPU, 메모리 사용량을 확인해보는데, 아무런 문제 없이 여유있고, 안정적으로 서비스가 운영되고있음.
- 고정 스레드 전략은 사용자가 늘어나도 CPU, 메모리 사용량이 확 늘어나지 않는다.
- 큐의 사이즈를 확인해보니 요청이 수 만 건이 쌓여있다. 요청이 처리되는 시간보다 쌓이는 시간이 더 빠름(큐 사이즈는 무한)
- 초기에는 사용자가 적기 때문에 이런 문제가 없지만, 사용자가 늘어나면 문제가 될 수 있다.

**서버 자원은 여유가 있는데, 사용자만 점점 느려지는 문제가 발생**

---
## Executor - 캐시 풀 전략

**newCachedThreadPool()**
- 기본 스레드를 사용하지 않고, 60초 생존 주기를 가진 초과 스레드만 사용
- 초과 스레드의 수는 제한이 없다.
- 큐에 작업을 저장하지 않는다. (`synchronousQueue`)
  - 대신에 생산자의 요청을 스레드 풀의 소비자 스레드가 직접 받아서 바로 처리
- 모든 요청이 대기하지 않고 스레드가 바로바로 처리한다. 따라서 빠른 처리 가능

```java
new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                        new SynchronousQueue<Runnable>());
```
**`SynchronousQueue`는 아주 특별한 블로킹 큐이다.**

- `BlockingQueue` 인터페이스의 구현체 중 하나다.
- 이 큐는 내부에 저장 공간이 없다. 대신에 생산자의 작업을 소비자 스레드에게 직접 전달한다.
- 저장 공간의 크기가 0이고, 생산자 스레드가 큐가 작업을 전달하면 소비자 스레드가 큐에서 작업을 꺼낼 때 까지 대기
- 이름 그대로 생산자와 소비자를 동기화하는 큐이다.
- 직거래 라고 생각하자.

`_5_PoolSize_CACHE_Main`

**캐시 스레드 풀 전략은 초과 스레드가 60초의 생존 주기를 가지지만, 여기서는 3초로 조절**


### 특징
캐시 스레드 풀 전략은 매우 빠르고, 유연한 전략이다.
기본 스레드도 없고, 대기 큐에 작업도 쌓이지 않는다. 요청이 오면 초과 스레드로 바로바로 처리한다.
초과 스레드의 제한이 없기 때문에 CPU, 메모리 자원만 허용한다면 시스템의 자워능ㄹ 최대로 사용할 수 있다.

초과 스레드는 60초간 생존하기 때문에 작업 수에 맞추어 적절한 수의 스레드가 재사용된다. 이런 특징 때문에
요청이 갑자기 증가하면 스레드도 갑자기 증가하고, 요청이 줄어들면 스레드도 점점 줄어든다.
**요청 수에 따라서 증가와 감소가 매우 유연한 전략이다.**


### 초과 스레드만 만들 수 있는 이유
1. 작업을 요청하면 core 사이즈 만큼 스레드 생성 -> **core 사이즈가 없어 바로 core 사이즈 초과**
2. core 사이즈를 초과하면 큐에 작업을 넣는다 -> **큐에 작업을 넣을 수 없다.`SynchronousQueue`는 저장공간이 0**
3. 큐를 초과하면 max 사이즈 만므 스레드를 생성 -> **초과 스레드가 생성된다. 물론 풀에 대기하는 초과 스레드가 있다면 재사용**
4. max 사이즈를 초과하면 요청을 거절한다. 예외가 발생 -> **참고로 max 사이즈가 무제한이다. 따라서 초과 스레드를 무제한으로 만들 수 있다.**


**결과적으로 이 전략의 모든 작업은 초과 스레드가 처리한다.**


### 주의
이 방식은 작업 수에 맞추어 스레드 수가 변하기 때문에, 작업 처리 속도가 빠르고, CPU, 메모리를 매우 유연하게
사용할 수 있다는 장점이 있다. 하지만 상황에 따라서 장점이 가장 큰 단점이 된다.


**상황1 - 점진적인 사용자 확대**
- 사용자가 점점 늘어난다.
- 캐시 스레드 전략을 사용하면 이런 경우 큰 문제가 되지 않는다.
- 캐시 스레드 전략은 사용자가 점점 증가하면서 스레드 사용량도 함께 늘어난다. 따라서 CPU, 메모리의 사용량도 자연스럽게 증가한다.
- 물론 CPU, 메모리 자원은 한계가 있기 때문에 적절한 시점에 시스템을 증설해야 한다. 그렇지 않으면 한계에 다다르고 시스템이 다운될 수 있다.


### 정리
`고정 스레드 풀 전략` 은 서버 자원은 여유 있는데, 사용자만 점점 느려지는 문제가 발생할 수 있다.
반면에 `캐시 스레드 풀 전략`은 서버의 자원을 최대한 사용하지만, 서버가 감당할 수 있는 임계점을 넘는 순간 시스템이 다운될 수 있다.


---
## 사용자 정의 풀 전략

**상황1 - 점진적인 사용자 확대**
- 개발한 서비스가 잘 되어서 사용자가 점점 늘어난다.

**상황2 - 갑작스런 요청 증가**
- 마케팅 팀의 이벤트가 대성공 하면서 갑자기 사용자가 폭증

**다음과 같이 새분화된 전략을 사용하면 상황1, 상황2 를 모두 어느정도 대응 가능**
- **일반** : 일반적인 상황에는 CPU, 메모리 자원을 예측할 수 있도록 고정 크기의 스레드로 서비스를 안정적으로 운영
- **긴급** : 사용자의 요청이 갑자기 증가하면 긴급하게 스레드를 추가로 투입해서 작업을 빠르게 처리
- **거절** : 사용자의 요청이 폭증해서 긴급 대응도 어렵다면 사용자의 요청을 거절

---
## 세분화 전략

```java
ExecutorService es = new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS,
                                            new ArrayBlockingQueue<>(1000));
```
- 100 개의 기본 스레드를 사용
- 추가로 긴급 대응 가능한 긴급 스레드 100개를 사용한다. 긴급 스레드는 60초의 생존 주기를 가진다.
- 1000개의 작업이 큐에 대기할 수 있다.

`_6_PoolSize_DETAIL_Main` 코드를 실행해보자.

**시나리오 별 예측**
- **일반** : 1000개 이하의 작업이 큐에 담겨있다. -> 100개의 기본 스레드가 처리
- **긴급** : 큐에 담긴 작업이 1000개를 초과한다. -> 100개의 기본 스레드 + 100개의 초과 스레드가 처리
- **거절** : 초과 스레드를 투입했지만, 큐에 담긴 작업 1000개를 초과하고 또 초과 스레드도 넘어간 상황. 이 경우 예외

---
## 실무에서 자주 하는 실수

**만약 다음과 같이 설정한다면?**
```java
new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
```
- **기본 스레드 100개**
- **최대 스레드 200개**
- **큐 사이즈: 무한대**

이렇게 설정하면 절대로 최대 사이즈 만큼 늘어나지 않는다. 왜냐하면 큐가 가득차야 긴급 상황으로 인지 되는데,
`LinkedBLockingQueue` 를 기본 생성자를 통해 무한대의 사이즈로 사용하게 되면, 큐가 가득찰 수 가 없다.
결국 기본 스레드 100개만으로 무한대의 작업을 처리해야 하는 문제가 발생.

---
## 정리

**실무 전략 선택**
- **고정 스레드 풀 전략** : 트래픽이 일정하고, 시스템 안전성이 가장 중요
- **캐시 스레드 풀 전략** : 일반적인 성장하는 서비스
- **사용자 정의 풀 전략** : 다양한 상황에 대응

