package lab13;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class InhabitantThread implements Runnable {

	private BlockingQueue<Message> blockingQueue;
	private int nr;
	public InhabitantThread(int nr,BlockingQueue<Message> blockingQueue) {
		this.nr=nr;
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while (true) {
			float probability = Configuration.getProbabilityoffailure();
			int maxValue = 10 / Math.round(probability * 10);
			Random random = new Random();

			

			int loosedValue = random.nextInt(maxValue);
			if (loosedValue == 0)
				try {
					String description="Cos nie tak u mieszkanca "+nr;
					blockingQueue.put(new Message(description));
					System.out.println("Mieszkaniec "+nr+" zglaszam awarie");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			try {
				TimeUnit.SECONDS.sleep(Configuration.getInhibitanttimesleep());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
