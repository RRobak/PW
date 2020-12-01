package lab15.zad6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		
		ExecutorService executor=Executors.newCachedThreadPool();
		executor.execute(new ReaderThread(lock));
		executor.execute(new ReaderThread(lock));
		executor.execute(new ReaderThread(lock));
		executor.execute(new WriterThread(lock));
		executor.shutdown();
	}
}
