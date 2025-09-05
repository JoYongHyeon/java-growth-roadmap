package thread._8_synchronized;

import thread._9_concurrent.locks.Account_V4_Impl;
import thread._9_concurrent.locks.Account_V5_Impl;

import static thread.util.ThreadUtils.sleep;
import static util.ThreadLogger.log;

public class Account_Main {

    public static void main(String[] args) throws InterruptedException {

//        Account_V1_Impl account = new Account_V1_Impl(1000);
//        Account_V2_Impl account = new Account_V2_Impl(1000);
//        Account_V3_Impl account = new Account_V3_Impl(1000);

        // 여기서는 ReentrantLock
//        Account_V4_Impl account = new Account_V4_Impl(1000);
        // tryLock 사용
        Account_V5_Impl account = new Account_V5_Impl(1000);

        Thread t1 = new Thread(new WithDrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithDrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        // 검증 완료까지 대기
        sleep(500);
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());

        t1.join();
        t2.join();
        log("최종 잔액: " + account.getBalance());
    }
}
