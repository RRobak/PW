package lab15.zad7;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderThread implements Runnable {
	final private ReentrantReadWriteLock.ReadLock readLock;
	
	
	
	public ReaderThread(ReentrantReadWriteLock.ReadLock lock) {
		this.readLock=lock;
	}
	@Override
	public void run() {
		while (true) {
			
			readLock.lock();
			try {
				System.out.println("Reader " + Data.data);
				
			} finally {
				readLock.unlock();
			}
			try {
				TimeUnit.MILLISECONDS.sleep(400);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
