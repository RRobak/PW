package lab13;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCopyOnWriteArraySet implements Runnable {

	private int nr;
	CopyOnWriteArraySet<Integer> copyOnWriteArraySet;

	public TestCopyOnWriteArraySet(int nr, CopyOnWriteArraySet<Integer> copyOnWriteArraySet) {
		this.nr = nr;
		this.copyOnWriteArraySet = copyOnWriteArraySet;
	}

	@Override
	public void run() {

		if (nr % 2 == 0) {

			Random random = new Random();
			int newValue = random.nextInt(10);
			copyOnWriteArraySet.add(newValue);
			System.out.println("Dodaje " +  newValue);
		} else {
			System.out.println(copyOnWriteArraySet);
		}

	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CopyOnWriteArraySet<Integer> copyOnWriteArraySet= new CopyOnWriteArraySet<Integer>();
		for (int i = 0; i < 10; i++)
			executorService.execute(new TestCopyOnWriteArraySet(i,
					copyOnWriteArraySet));
		executorService.shutdown();
	}
}
