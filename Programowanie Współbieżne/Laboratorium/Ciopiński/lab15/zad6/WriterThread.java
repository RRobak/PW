package lab15.zad6;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class WriterThread implements Runnable {
	Lock lock;
	public WriterThread(Lock lock) {
		this.lock=lock;
	}
	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
			
			lock.lock();
			try {
				Integer newInt = rand.nextInt(100);
				Data.data = newInt;
				System.out.println("Writer " + newInt);
				
			} finally {
				lock.unlock();
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
