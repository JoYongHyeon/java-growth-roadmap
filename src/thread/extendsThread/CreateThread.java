package thread.extendsThread;

public class CreateThread  extends Thread {

    @Override
    public void run() {
        // Thread.currentThread().getName() : 이 코드를 실행하는 스레드 객체의 이름을 조회
        // 스레드에 이름을 주지 않으면 자바는 스레드에 Thread-0, Thread-1 과 같은 임의의 이름을 부여함
        System.out.println(Thread.currentThread().getName() + " <= 실행되는 스레드 명");
    }
}
