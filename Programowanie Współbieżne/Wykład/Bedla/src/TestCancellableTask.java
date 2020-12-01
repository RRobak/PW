import java.util.concurrent.TimeUnit;

class CancellableTask extends Thread {
	private volatile boolean canceled;

	public void cancel() {
		System.out.println("CANCEL    " + System.nanoTime());
		canceled = true;
	}

	@Override
	public void run() {
		System.out.println("BEGIN     " + System.nanoTime());
		try {
			while (!canceled) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("BETWEEN   " + System.nanoTime());
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			System.out.println("INTERRUPT " + System.nanoTime() + " " + isInterrupted());
		}
		System.out.println("END       " + System.nanoTime());
	}
}

public class TestCancellableTask {
	public static void main(String[] args) throws InterruptedException {
		CancellableTask thread = new CancellableTask();
		thread.start();
		TimeUnit.MILLISECONDS.sleep(500);
		thread.cancel();
	}
}