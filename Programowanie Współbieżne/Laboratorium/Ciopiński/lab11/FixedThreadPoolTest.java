package lab11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
public static void main(String[] args) {
	ExecutorService executor=Executors.newFixedThreadPool(2);
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.shutdown();
}
}
