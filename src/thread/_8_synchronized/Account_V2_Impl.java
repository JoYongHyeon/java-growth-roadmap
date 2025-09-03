package thread._8_synchronized;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class Account_V2_Impl implements Account_V1 {

    private int balance;

    // 초기 잔액 저장
    public Account_V2_Impl(int balance) {
        this.balance = balance;
    }

    @Override
    public synchronized boolean withdraw(int money) {
        log("거래 시작: " + getClass().getSimpleName());

        log("[검증 시작] 출금액: " + money + ", 잔액: " + balance);

        if (balance < money) {
            log("[검증 실패] 출금액: " + money + ", 잔액: " + balance);
            return false;
        }
        log("[검증 완료] 출금액: " + money + ", 잔액: " + balance);
        // 출금 시간
        sleep(1000);
        balance -= money;
        log("[출금 완료] 출금액: " + money + ", 변경 잔액: " + balance);

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
