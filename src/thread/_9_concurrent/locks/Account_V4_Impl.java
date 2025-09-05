package thread._9_concurrent.locks;

import thread._8_synchronized.Account_V1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;


/**
 * 기존의 synchronized 대신에 Lock, ReentrantLock 사용하도록 변경
 */
public class Account_V4_Impl implements Account_V1 {

    private int balance;

    private final Lock lock = new ReentrantLock();

    // 초기 잔액 저장
    public Account_V4_Impl(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int money) {
        log("거래 시작: " + getClass().getSimpleName());

        // ReentrantLock 이용하여 lock 을 검
        lock.lock();

        try {
            log("[검증 시작] 출금액: " + money + ", 잔액: " + balance);
            if (balance < money) {
                log("[검증 실패] 출금액: " + money + ", 잔액: " + balance);
                return false;
            }
            log("[검증 완료] 출금액: " + money + ", 잔액: " + balance);
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
