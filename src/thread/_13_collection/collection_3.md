## 자바 동시성 컬렉션
**자바 1.5부터 동시성에 대한 많은 혁신이 이루어지고, 동시성을 위한 컬렉션도 있다.**

여기서 말하는 **동시성 컬렉션**은 멀티스레드에 안전한 컬렉션을 뜻한다.
`java.util.concurrent` 패키지에는 고성능 멀티스레드 환경을 지원하는 다양한 동시성 컬렉션 클래스들을 제공한다.

`ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue` 등,
`synchronized`, `Lock(ReentrantLock)`, `CAS`, `분할 잠금 기술` 등 다양한
방법을 섞어 정교한 동기화를 구현하면서 동시에 성능도 최적화했다.

필요한 경우 일부 메서드에 대해서만 동기화를 적용하는 등 유연한 동기화 전략을 제공한다.

---
### 동시성 컬렉션의 종류
- **List**
  - `CopyOnWriteArrayList` -> `ArrayList` 의 대안
- **Set**
  - `CopyOnWriteArraySet` -> `HashSet` 의 대안
  - `concurrentSkipListSet` -> `TreeSet` 의 대안 (정렬된 순서 유지, Comparator 사용)
- **Map**
  - `ConcurrentHashMap` : `HashMap` 의 대안
  - `ConcurrentSkipListMap`: `TreeMap`의 대안(정렬된 순서 유지, Comparator 사용 가능)
- **Queue**
  - `ConcurrentLinkedQueue`: 동시성 큐, 비 차단(non-blocking) 큐이다.
- **Deque**
  - `ConcurrentLinkedDeque`: 동시성 데크, 비 차단(non-blocking) 큐이다.

당연하게도 `LinkedHashSet`, `LinkedHashMap` 처럼 입력 순서를 유지하는 동시에 멀티스레드 환경에서
사용할 수 있는 `Set`, `Map` 구현체는 제공하지 않는다.

---
### 블로킹 큐 (스레드 차단)
- **BlockingQueue**
  - `ArrayBlockingQueue`
    - 크기가 고정된 블로킹 큐
    - 공정(fair)모드를 사용할 수 있다. -> 사용하면 성능 저하
  - `LinkedBlockingQueue`
    - 크기가 무한하거나 고정된 블로킹 큐
  - `PriorityBlockingQueue`
    - 우선순위가 높은 요소를 먼저 처리하는 블로킹 큐
  - `SynchronousQueue`
    - 데이터를 저장하지 않는 블로킹 큐로, 생산자가 데이터를 추가하면 소비자가 그 데이터를 받을 때까지 대기
      생산자-소비자 간의 직접적인 핸드오프 메커니즘을 제공 (**중간에 큐 없이 생산자, 소비자 직접 거래**)
  - `DelayQueue`
    - 지연된 요소를 처리하는 블로킹 큐로, 각 요소는 지정된 지연 시간이 지난 후에야 소비될 수 있다.
      일정 시간이 지난 후 작업을 처리해야 하는 스케줄링 작업에 사용
