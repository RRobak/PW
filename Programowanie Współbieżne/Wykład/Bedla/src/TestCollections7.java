import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Producer implements Runnable {
	private boolean sleep;
	private int i;

	Producer(boolean sleep) {
		this.sleep = sleep;
	}

	@Override
	public void run() {
		try {
			while (true)
				produce();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	final void produce() throws InterruptedException {
		System.out.println("> " + ++i);
		TestCollections7.queue.put(i);
		System.out.println(">> " + i);
		if (sleep)
			TimeUnit.SECONDS.sleep(2);
	}
}

class Consumer implements Runnable {
	private boolean sleep;
	private int i;

	Consumer(boolean sleep) {
		this.sleep = sleep;
	}

	@Override
	public void run() {
		try {
			while (true)
				consume();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	final void consume() throws InterruptedException {
		System.out.println("< " + ++i);
		int tmp = TestCollections7.queue.take();
		System.out.println("<< " + i + ":" + tmp);
		if (sleep)
			TimeUnit.SECONDS.sleep(2);
	}
}

public class TestCollections7 {
	static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(4);

	public static void main(String[] args) throws Exception {
		new Thread(new Producer(false)).start();
		new Thread(new Consumer(true)).start();
	}
}