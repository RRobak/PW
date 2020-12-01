import java.util.concurrent.TimeUnit;

class InterruptableTask extends Thread {
    public void Cancel() {
        System.out.println("Cancel: " + System.currentTimeMillis());
        interrupt();
    }

    @Override
    public void run() {
        System.out.println("Started:" + System.currentTimeMillis());
        try {
            while (!isInterrupted()) {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("Betwen:" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted in: " + System.currentTimeMillis() + " " + isInterrupted());
        }
        System.out.println("Ended in: " + System.currentTimeMillis());
    }
}

public class Z1B {
    public static void main(String[] args) throws InterruptedException {
        InterruptableTask thread = new InterruptableTask();
        thread.start();
        TimeUnit.MILLISECONDS.sleep(500);
        thread.Cancel();
    }
}