import java.util.concurrent.atomic.AtomicInteger;

class Counter extends Thread {
    AtomicInteger handler;

    Counter() {
        handler = new AtomicInteger();
    }

    public void run() {
        int max = 10;
        for (int i = 0; i < max; i++) {
            handler.addAndGet(69);
        }
    }
}

public class Z2A {
    public static void main(String[] args) throws InterruptedException {
        Counter counterObject = new Counter();
        Thread first = new Thread(counterObject, "First");
        Thread second = new Thread(counterObject, "Second");
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(counterObject.handler);
    }
}
