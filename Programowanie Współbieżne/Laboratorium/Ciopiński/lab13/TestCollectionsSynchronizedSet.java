package lab13;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCollectionsSynchronizedSet implements Runnable {

	private int nr;
	private Set<Integer> hashSet;

	public TestCollectionsSynchronizedSet(int nr, Set<Integer> myHashSet) {
		this.nr = nr;
		this.hashSet = myHashSet;
	}

	@Override
	public void run() {

		if (nr % 2 == 0) {

			Random random = new Random();
			int newValue = random.nextInt(10);
			hashSet.add(newValue);
			System.out.println("Dodaje " +  newValue);
		} else {
			System.out.println(hashSet);
		}

	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
		for (int i = 0; i < 10; i++)
			executorService.execute(new TestCollectionsSynchronizedSet(i,
					set));
		executorService.shutdown();
	}
}
