import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface SimpleExecutorService extends ExecutorService {

	public void execute(Runnable task);

	public void shutdown();

	public default List<Runnable> shutdownNow() {
		throw new UnsupportedOperationException();
	}

	public default boolean isShutdown() {
		throw new UnsupportedOperationException();
	}

	public default boolean isTerminated() {
		throw new UnsupportedOperationException();
	}

	public default boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		throw new UnsupportedOperationException();
	}

	public default <T> Future<T> submit(Callable<T> task) {
		throw new UnsupportedOperationException();
	}

	public default <T> Future<T> submit(Runnable task, T result) {
		throw new UnsupportedOperationException();
	}

	public default Future<?> submit(Runnable task) {
		throw new UnsupportedOperationException();
	}

	public default <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		throw new UnsupportedOperationException();
	}

	public default <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		throw new UnsupportedOperationException();
	}

	public default <T> T invokeAny(Collection<? extends Callable<T>> tasks)
			throws InterruptedException, ExecutionException {
		throw new UnsupportedOperationException();
	}

	public default <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		throw new UnsupportedOperationException();
	}
}
