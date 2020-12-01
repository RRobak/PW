import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallableTaskDouble implements Callable<Double> {
	private Double a, b;

	MyCallableTaskDouble(Double a, Double b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public Double call() throws Exception {
		return a / b;
	}
}

public class TestCallable {

	private static <T extends Callable<U>, U> void test(List<T> tasks) {
		List<Future<U>> results = new ArrayList<Future<U>>();
		ExecutorService executor = Executors.newCachedThreadPool();
		for (T task : tasks)
			results.add(executor.submit(task));
		for (Future<U> result : results)
			try {
				System.out.println(result.get());
			} catch (InterruptedException e) {
				e.getCause().printStackTrace();
			} catch (ExecutionException e) {
				e.getCause().printStackTrace();
			}
		executor.shutdown();
	}

	public static void main(String[] args) {
		ArrayList<MyCallableTaskDouble> tasksDouble = new ArrayList<MyCallableTaskDouble>();
		tasksDouble.add(new MyCallableTaskDouble(1d, 0d));
		tasksDouble.add(new MyCallableTaskDouble(-1d, 0d));
		tasksDouble.add(new MyCallableTaskDouble(0d, 0d));
		test(tasksDouble);
	}
}