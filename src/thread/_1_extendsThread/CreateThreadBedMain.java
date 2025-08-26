package thread._1_extendsThread;

public class CreateThreadBedMain {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + ": main() start");

        CreateThread createThread = new CreateThread();
        System.out.println(Thread.currentThread().getName() + ": run() 호출 전");
        createThread.run();
        System.out.println(Thread.currentThread().getName() + ": run() 호출 후");
    }
    /**
     * 실행결과를 보면 main 스레드가 전부 호출함
     * 스레드의 start() 메서드는 스레드에 스택 공간을 할당하면서 스레드를 시작하는 메서드이다.
     * 그리고 해당 스레드에서 run() 메서드를 실행함.
     */
}
