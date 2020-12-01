import java.util.concurrent.TimeUnit;

class InterruptableTask extends Thread {
	public void cancel() {
		System.out.println("CANCEL    " + System.nanoTime());
		interrupt();
	}

	@Override
	public void run() {
		System.out.println("BEGIN     " + System.nanoTime());
		try {
			while (!isInterrupted()) {
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

public class TestInterruptableTask {
	public static void main(String[] args) throws InterruptedException {
		InterruptableTask thread = new InterruptableTask();
		thread.start();
		TimeUnit.MILLISECONDS.sleep(500);
		thread.cancel();
	}
}