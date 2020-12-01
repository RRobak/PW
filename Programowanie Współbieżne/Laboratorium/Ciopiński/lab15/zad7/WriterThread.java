package lab15.zad7;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriterThread implements Runnable {
final private ReentrantReadWriteLock.WriteLock writeLock;
	
	
	
	public WriterThread(ReentrantReadWriteLock.WriteLock lock) {
		this.writeLock=lock;
	}
	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
			
			writeLock.lock();
			try {
				Integer newInt = rand.nextInt(100);
				Data.data = newInt;
				System.out.println("Writer " + newInt);
				
			} finally {
				writeLock.unlock();
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
