## ReentrantLock
`synchronized` 와 `BLOCKED` 의 임계 영약 관리의 한계를 극복하기 위해 나온 개념
`Lock` 인터페이스와 `ReentrantLock` 구현체를 제공


```java
package java.util.concurrent.locks;

public interface Lock {
    /**
      락을 획득한다. 만약 다른 스레드가 이미 락을 획득했다면, 
      락이 풀릴 때까지 현재 스레드는 대기(`WAITING`)한다.
     */
    void lock();

    /**
        락 획득을 시도하되, 다른 스레드가 인터럽트할 수 있도록 한다.
        다른 스레드가 락을 획득했다면 대기하며 대기 중에 `InterruptedException`이 발생하면 락 획득 포기 
     */
    void lockInterruptibly() throws InterruptedException;
    
    // 락 획득을 시도하고 성공 여부를 반환
    boolean tryLock();
    
    // 주어진 시간 동안 락 획득을 시도한다. 주어진 시간안에 락을 획득하면 true 아니면 false
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
    
    /**
     * 락을 해제한다. 해제하면 락 획득을 대기중인 스레드 중 하나가 락 획득
     * 락을 획득한 스레드가 호출해야 함. 아니면 IllegalMonitorStateException 발생
     */
    void unlock();
    
    /**
     * 이 객체는 락과 결합되어 사용되며, 스레드가 특정 조건을 기다리거나 신호를 받을 수 있도록 한다.
     */
    Condition newCondition();
}
```
---

## 공정성
```java
public class ReentrantLock_V1 {

    // 비공정 모드 락
    private final Lock nonFairLock = new ReentrantLock();

    // 공정 모드 락
    private final Lock fairLock = new ReentrantLock(true);

    public void nonFairLockTest() {
        nonFairLock.lock();

        try {
            // 임계 영역
        } finally {
            nonFairLock.unlock();
        }
    }
    
    public void fairLockTest() {
        fairLock.lock();
        try {
            // 임계 영역
        } finally {
            fairLock.unlock();
        }
    }
}
```
---
### 공정성(fairness) 모드? 비공정(non-fair) 모드?

## 비공정 모드 (Non-fair mode)
`ReentrantLock` 기본 모드로 스레드가 락을 먼저 요청했다고 먼저 획득한다는 보장은 없다.
락을 빨리 획득할 수 있지만, 장기간 락을 획득하지 못할 가능성이 있음.

### 비공정 모드 특징
- **성능 우선** : 락을 획득하는 속도가 빠름
- **선점 가능** : 순서가 보장되지 않음 
- **기아 현상 가능성** : 특정 스레드가 계속해서 락을 획득하지 못할 수 있음.


## 공정 모드
생성자에서 `true`를 전달하면 된다. 
스레드의 요청 순서대로 락을 획득할 수 있게 한다.

### 공정 모드 특징
- **공정성 보장** : 대기 큐에서 먼저 대기한 스레드가 락을 먼저 획득
- **기아 현상 방지** : 모든 스레드가 락을 획득할 수 있게 보장
- **성능 저하** : 락을 획득하는 속도가 느려질 수 있음.


