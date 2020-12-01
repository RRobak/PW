package lab15.zad6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ReaderThread implements Runnable {

	Lock lock;
	public ReaderThread(Lock lock) {
		this.lock=lock;
	}
	@Override
	public void run() {
		while (true) {
			
			lock.lock();
			try {
				System.out.println("Reader " + Data.data);
				
			} finally {
				lock.unlock();
			}
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
