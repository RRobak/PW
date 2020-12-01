import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class FutureCancel extends Thread {
    @Override
    public void run() {
        System.out.println("Started:     " + System.currentTimeMillis());
        try {

            TimeUnit.SECONDS.sleep(10);
            System.out.println("Betwen thread.sleep(10):   " + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(10);

        } catch (InterruptedException e) {
            System.out.println("Interrupted in: " + System.currentTimeMillis() + " " + isInterrupted());
        }
        System.out.println("Ended in: " + System.currentTimeMillis());
    }
}

public class Z1C {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(new FutureCancel());
        TimeUnit.MILLISECONDS.sleep(500);
        future.cancel(true);
        executor.shutdown();
    }
}