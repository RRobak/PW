package lab15.zad5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicTest {
	public static void main(String[] args) {
		ExecutorService executor=Executors.newCachedThreadPool();
		executor.execute(new ReaderThread());
		executor.execute(new ReaderThread());
		executor.execute(new ReaderThread());
		executor.execute(new WriterThread());
		executor.shutdown();
	}
}
