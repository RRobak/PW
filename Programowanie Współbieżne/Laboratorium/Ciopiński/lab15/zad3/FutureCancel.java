package lab15.zad3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureCancel {
public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(new FutureCancelThread());
		TimeUnit.MILLISECONDS.sleep(500);
		future.cancel(true);
		executor.shutdown();
	
}
}
