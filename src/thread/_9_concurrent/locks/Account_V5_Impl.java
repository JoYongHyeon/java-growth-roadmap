package thread._9_concurrent.locks;

import thread._8_synchronized.Account_V1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;


/**
 * tryLock() : 락 획득을 시도하고, 즉시 성공 여부를 반환한다. 만약 다른 스레드가 이미 락을 획득했다면 `false` 를 반환하고,
 * 그렇지 않으면 락을 획득하고 `true` 를 반환한다
 */
public class Account_V5_Impl implements Account_V1 {

    private int balance;

    private final Lock lock = new ReentrantLock();

    // 초기 잔액 저장
    public Account_V5_Impl(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int money) {
        log("거래 시작: " + getClass().getSimpleName());

        if (!lock.tryLock()) {
            log("[진입 실패] 이미 처리중인 작업이 있습니다.");
            return false;
        }

        try {
            log("[검증 시작] 출금액: " + money + ", 잔액: " + balance);
            if (balance < money) {
                log("[검증 실패] 출금액: " + money + ", 잔액: " + balance);
                return false;
            }
            sleep(1000);
            balance -= money;
            log("[출금 완료] 출금액: " + money + ", 변경 잔액: " + balance);
        } finally {
            lock.unlock();
        }
        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        // ReentrantLock 이용하여 lock 걸기
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
