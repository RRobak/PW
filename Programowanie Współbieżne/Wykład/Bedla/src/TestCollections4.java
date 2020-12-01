import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestCollections4 {
	final private static int SIZE = 4;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService e = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++)
			e.execute(new Lotto());
		e.shutdown();
		e.awaitTermination(1, TimeUnit.DAYS);
		synchronized (Lotto.numbers) {
			System.out.println(Lotto.numbers);
		}
	}
}

class Lotto implements Runnable {
	private static List<Integer> privateNumbers = new ArrayList<Integer>();
	static List<Integer> numbers = Collections.synchronizedList(privateNumbers);

	@Override
	public void run() {
		int i = 0;
		while (i < 10) {
			++i;
			synchronized (numbers) {
				if (numbers.contains(i) == false)
					numbers.add(i);
			}
		}
	}
}