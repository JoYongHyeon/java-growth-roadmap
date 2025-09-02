package thread._8_synchronized;

public interface Account_V1 {

    // 매개 변수로 받은 돈을 출금
    boolean withdraw(int money);
    // 잔액을 반환
    int getBalance();
}
