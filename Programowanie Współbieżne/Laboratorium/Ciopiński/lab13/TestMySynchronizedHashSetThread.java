package lab13;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMySynchronizedHashSetThread implements Runnable {

	private int nr;
	private MyHashSet myHashSet;

	public TestMySynchronizedHashSetThread(int nr, MyHashSet myHashSet) {
		this.nr = nr;
		this.myHashSet = myHashSet;
	}

	@Override
	public void run() {

		if (nr % 2 == 0) {

			Random random = new Random();
			myHashSet.add(random.nextInt(10));
		} else {
			System.out.println(myHashSet.getToString());
		}

	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		MyHashSet myHashSet = new MyHashSet();
		for (int i = 0; i < 10; i++)
			executorService.execute(new TestMySynchronizedHashSetThread(i,
					myHashSet));
		executorService.shutdown();
	}
}
