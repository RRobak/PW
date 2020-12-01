package lab13;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConcurrentSkipListSet implements Runnable {

	private int nr;
	ConcurrentSkipListSet<Integer> concurrentSkipListSet;

	public TestConcurrentSkipListSet(int nr, ConcurrentSkipListSet<Integer> concurrentSkipListSet) {
		this.nr = nr;
		this.concurrentSkipListSet = concurrentSkipListSet;
	}

	@Override
	public void run() {

		if (nr % 2 == 0) {

			Random random = new Random();
			int newValue = random.nextInt(10);
			concurrentSkipListSet.add(newValue);
			System.out.println("Dodaje " +  newValue);
		} else {
			System.out.println(concurrentSkipListSet);
		}

	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		ConcurrentSkipListSet<Integer> concurrentSkipListSet= new ConcurrentSkipListSet<Integer>();
		for (int i = 0; i < 10; i++)
			executorService.execute(new TestConcurrentSkipListSet(i,
					concurrentSkipListSet));
		executorService.shutdown();
	}
}
