package thread.runnable;

public class FirstRunnableMain {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + ": main() start");

        // CreateThread.java 의 Thread 상속 방식보다 Runnable 구현을 사용하자.
        FirstRunnable runnable = new FirstRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
