import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

enum PriorityLevel {
	NO_PRIORITY(0), LOW_PRIORITY(1), MEDIUM_PRIORITY(2), HIGH_PRIORITY(3);

	private int value;

	PriorityLevel(int value) {
		this.value = value;
	}

	int getValue() {
		return value;
	}
}

class PriorityTask implements Runnable, Comparable<PriorityTask> {
	private PriorityLevel priorityLevel;
	private int taskNumber;
	private Runnable runnable;

	PriorityTask(int taskNumber, PriorityLevel priority, Runnable runnable) {
		this.taskNumber = taskNumber;
		this.priorityLevel = priority;
		this.runnable = runnable;
	}

	@Override
	public void run() {
		System.out.printf("%-30s %d%n", "START " + this, System.currentTimeMillis());
		runnable.run();
	}

	@Override
	public int compareTo(PriorityTask t) {
		return t.priorityLevel.getValue() - this.priorityLevel.getValue();
	}

	@Override
	public String toString() {
		return taskNumber + ": " + priorityLevel;
	}
}

class PriorityExecutor implements Executor {

	PriorityExecutor(final ExecutorService executor, final boolean sleep) {
		new Thread() {
			@Override
			public void run() {
				try {
					if (sleep)
						TimeUnit.SECONDS.sleep(1);
					while (true) {
						Runnable r = tasks.poll(10, TimeUnit.SECONDS);
						if (r != null)
							executor.execute(r);
						else
							break;
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} finally {
					executor.shutdown();
				}
			}
		}.start();
	}

	final PriorityBlockingQueue<Runnable> tasks = new PriorityBlockingQueue<Runnable>();

	@Override
	public void execute(Runnable r) {
		tasks.add(r);
	}

}

public class TestPriorityExecutor {
	public static void main(String args[]) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		PriorityExecutor executor = new PriorityExecutor(Executors.newSingleThreadExecutor(), false);
		executor.execute(new PriorityTask(1, PriorityLevel.NO_PRIORITY, r));
		executor.execute(new PriorityTask(2, PriorityLevel.LOW_PRIORITY, r));
		executor.execute(new PriorityTask(3, PriorityLevel.MEDIUM_PRIORITY, r));
		executor.execute(new PriorityTask(4, PriorityLevel.HIGH_PRIORITY, r));
	}
}