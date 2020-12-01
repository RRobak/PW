package lab15.zad7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		ExecutorService executor=Executors.newCachedThreadPool();
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		executor.execute(new ReaderThread(lock.readLock()));
		executor.execute(new ReaderThread(lock.readLock()));
		executor.execute(new ReaderThread(lock.readLock()));
		executor.execute(new WriterThread(lock.writeLock()));
		executor.shutdown();
	}
}
