package lab13;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConservatorThread implements Runnable {

	private BlockingQueue<Message> blockingQueue;

	public ConservatorThread(BlockingQueue<Message> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while (true) {
			Random rand = new Random();
			try {
				Message message = blockingQueue.take();
				System.out.println("Otrzymalem zgloszenie awari - " + message);
				try {
					TimeUnit.SECONDS.sleep(rand.nextInt(Configuration
							.getMaxrepairtime()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Naprawilem awarie - " + message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
