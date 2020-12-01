import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class FutureCancellableTask extends Thread {
	public void run() {
		System.out.println("BEGIN     " + System.nanoTime());
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("BETWEEN   " + System.nanoTime());
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("INTERRUPT " + System.nanoTime());
		}
		System.out.println("END       " + System.nanoTime());
	}
}

public class TestFuture {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(new FutureCancellableTask());
		TimeUnit.MILLISECONDS.sleep(500);
		future.cancel(true);
		executor.shutdown();
	}
}