package thread;

public class CreateThreadMain {

    public static void main(String[] args) {
        // main 스레드가 실행
        System.out.println(Thread.currentThread().getName() + " <= 실행되는 스레드 명");

        // 객체 생성
        CreateThread createThread = new CreateThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        // 직접 만든 스레드 run() 실행 (스레드 간 실행 순서는 보장되지 않는다.)
        createThread.start();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");
    }
}
