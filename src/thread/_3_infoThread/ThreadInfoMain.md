```
16:57:42.259 [     main] mainThread = Thread[#1,main,5,main]
16:57:42.263 [     main] threadId() = 1
16:57:42.263 [     main] threadName() = main
16:57:42.265 [     main] priority() = 5
16:57:42.265 [     main] threadGroup() = java.lang.ThreadGroup[name=main,maxpri=10]
16:57:42.265 [     main] state() = RUNNABLE
================================================================================================
16:57:42.266 [     main] myThread = Thread[#22,my-Thread,5,main]
16:57:42.266 [     main] myThreadId() = 22
16:57:42.266 [     main] myThreadName() = my-Thread
16:57:42.266 [     main] myThreadPriority() = 5
16:57:42.266 [     main] myThreadGroup() = java.lang.ThreadGroup[name=main,maxpri=10]
16:57:42.266 [     main] myThreadState() = NEW
```

---
### 스레드 상태
```
log("myThread.getState() = " + myThread.getState());
```
- **NEW** : 스레드가 아직 시작되지 않은 상태
- **RUNNABLE**      : 스레드가 아직 시작되지 않은 상태
- **BLOCKED**       : 스레드가 실행 중이거나 실행될 준비가 된 상태
- **WAITING**       : 스레드가 동기화 락을 기다리는 상태
- **TIMED_WAITING** : 일정 시간 동안 기다리는 상태
- **TERMINATED**    : 스레드가 실행을 마친 상태


```
16:57:42.265 [     main] state() = RUNNABLE
16:57:42.266 [     main] myThreadState() = NEW
```
- `main` 스레드는 실행중으로 `RUNNABLE`상태
- `mtThread` 스레드는 시작되지 않았기 때문에 `NEW` 상태
