## synchronized 의 한계

### 1. 무한 대기
- `BLOCKED` 상태의 스레드는 락이 풀릴 때까지 무한 대기
- 타임아웃 또는 중간에 인터럽트도 불가능
- `BLOCKED` 된 스레드 중에 순서와 상관 없이 어떤 스레드가 락을 획득할 지 알 수 없다.
  최악의 경우 계속 기다려야 되는 상황이 생길 수 있음.

## 2. LockSupport
`synchronized` 와 같은 문제를 해결하기 위해 나온것이 `LockSupport` 이다.
`LockSupport`는 스레드를 `WAITING` 상태로 변경한다.
`WAITING` 상태는 누가 깨워주기 전까지는 계속 대기한다.

### 2-1. LockSupport 기능
- `park` : 스레드를 `WAITING` 상태로 변경
- `parkNanos` : 스레드를 나노초 동안만 `TIMED_WAITING` 상태로 변경한다.
    나노초가 지나면 `RUNNABLE` 상태로 변경됨
- `unpark` : `WAITING` 상태의 대상 스레드를 `RUNNABLE` 상태로 변경한다.

```java
 // 매개변수 없이 스스로 'WAITING` 상태로 가능
 LockSupport.park();
 // 실행 상태로 바꾸기 위한 매개변수(스레드)가 필요함
 LockSupport.unpark(thread1);
```
---

## BLOCKED 와 WAITING 비교
- `WAITING`, `TIMED_WAITING` 상태는 인터럽트가 걸리면 대기 상태를 빠져나오면서 `RUNNABLE`상태로 변함
- `BLOCKED` 상태는 인터럽트가 걸려도 대기 상태를 빠져나오지 못하고 계속 `BLOCKED` 상태로 있는다.
---

## 정리
`LockSupport` 를 사용하면 `WAITING`, `TIMED_WAITING` 상태로 변경할 수 있고, 인터럽트를 받아
스레드를 꺠우는 것도 가능하다. 이런 기능들을 잘 활용하면 `synchronized` 의 단점인
무한 대기 문제를 해결할 수 있다.
