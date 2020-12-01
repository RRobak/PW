import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Buffer {
	private int i = 0;
	final private ReentrantReadWriteLock.ReadLock readLock;
	final private ReentrantReadWriteLock.WriteLock writeLock;

	Buffer() {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		readLock = lock.readLock();
		writeLock = lock.writeLock();
	}

	int get(long tid) throws InterruptedException {
		System.out.println("BEFORE GET " + tid + " " + System.nanoTime());
		readLock.lock();
		System.out.println("AFTER  GET " + tid + " " + System.nanoTime());

		try {
			TimeUnit.SECONDS.sleep(1);
			return i;
		} finally {
			readLock.unlock();
			System.out.println("END GET    " + tid + " " + System.nanoTime());
		}
	}

	void set(long tid, int i) throws InterruptedException {
		System.out.println("BEFORE SET " + tid + " " + System.nanoTime());
		writeLock.lock();
		System.out.println("AFTER  SET " + tid + " " + System.nanoTime());
		try {
			TimeUnit.SECONDS.sleep(5);
			this.i = i;
		} finally {
			writeLock.unlock();
			System.out.println("END SET    " + tid + " " + System.nanoTime());
		}
	}
}

class Reader extends Thread {
	private Buffer b;

	Reader(Buffer b) {
		this.b = b;
	}

	public void run() {
		int i = 0;
		long tid = getId();
		try {
			while (i < 10)
				b.get(tid);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Writer extends Thread {
	private Buffer b;

	Writer(Buffer b) {
		this.b = b;
	}

	public void run() {
		int i = 0;
		long tid = getId();
		try {
			while (i < 5)
				b.set(tid, i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class TestLock {
	public static void main(String[] args) throws InterruptedException {
		final Buffer b = new Buffer();
		for (int i = 0; i < 2; i++) {
			TimeUnit.MILLISECONDS.sleep(500);
			new Reader(b).start();
		}
		TimeUnit.MILLISECONDS.sleep(500);
		new Writer(b).start();
	}
}