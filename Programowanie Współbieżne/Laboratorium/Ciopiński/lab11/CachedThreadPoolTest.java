package lab11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {
public static void main(String[] args) {
	ExecutorService executor=Executors.newCachedThreadPool();
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.execute(new ClassRunnable());
	executor.shutdown();
}
}
