package thread._8_synchronized;

/**
 * 출금 담당 runnable
 */
public class WithDrawTask implements Runnable {

    private Account_V1 account;
    private int money;

    public WithDrawTask(Account_V1 account, int money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        account.withdraw(money);
    }
}
