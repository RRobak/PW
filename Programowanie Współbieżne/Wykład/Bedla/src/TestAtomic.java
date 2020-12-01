import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {
	final static int SIZE = 2;
	final static int LOOPS = 1000;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService e = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++)
			e.execute(new Counter());
		e.shutdown();
		e.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("Counter is : " + Counter.counter);
		System.out.println("Difference : " + (double) (SIZE * LOOPS - Counter.counter.get()) / (SIZE * LOOPS));
	}
}

class Counter implements Runnable {
	static AtomicInteger counter = new AtomicInteger(0);

	@Override
	public void run() {
		int i = 0;
		while (i < TestAtomic.LOOPS) {
			++i;
			counter.incrementAndGet();
		}
		System.out.println(Thread.currentThread() + " : " + i);
	}
}

/* lub */
/*
class Counter implements Runnable {
	volatile static int counter;

	@Override
	public void run() {
		int i = 0;
		while (i < TestAtomic.LOOPS) {
			++i;
			++counter;
		}
		System.out.println(Thread.currentThread() + " : " + i);
	}
}
*/